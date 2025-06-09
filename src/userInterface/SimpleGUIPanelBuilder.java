package userInterface;

import batteryLogic.BatteryStateController;
import persistenceManager.SettingsStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

class SimpleGUIPanelBuilder implements UIConstants {

    private final JLabel shaveReadyIcon = new JLabel();
    private static JButton button;
    private final SettingsStorage settingsStorage;
    private JToggleButton thresholdToggle;

    SimpleGUIPanelBuilder(){
        settingsStorage = SettingsStorage.getInstance();

        button = new JButton();
    }

    void setupPanel(LEDPanel led, JLabel statusLabel) {
        JFrame display = createMainFrame();
        JPanel topPanel = createTopPanel(statusLabel);
        JPanel middlePanel = createMiddlePanel();
        JPanel bottomPanel = createBottomPanel(led);

        display.add(topPanel, BorderLayout.NORTH);
        display.add(middlePanel, BorderLayout.CENTER);
        display.add(bottomPanel, BorderLayout.SOUTH);
        display.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("Display Rasierapparat");
        frame.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    private JPanel createTopPanel(JLabel statusLabel) {
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, TEXT_SIZE));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(Color.BLACK);
        panel.add(statusLabel);
        return panel;
    }

    private JPanel createMiddlePanel() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/rasur_moeglich_icon.jpeg")));
        Image scaled = icon.getImage().getScaledInstance(SHAVER_ICON_SIZE, SHAVER_ICON_SIZE, Image.SCALE_SMOOTH);
        shaveReadyIcon.setIcon(new ImageIcon(scaled));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(new EmptyBorder(0, SIDE_BORDER, 0, SIDE_BORDER));
        panel.add(setupThresholdToggle(), BorderLayout.EAST);
        panel.add(shaveReadyIcon, BorderLayout.WEST);
        return panel;
    }

    private JPanel createBottomPanel(LEDPanel led) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/powerIcon.jpg")));
        Image scaled = icon.getImage().getScaledInstance(WIDTH_HEIGHT_BUTTON, WIDTH_HEIGHT_BUTTON, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaled));
        button.setPreferredSize(new Dimension(WIDTH_HEIGHT_BUTTON, WIDTH_HEIGHT_BUTTON));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, VGAP));
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(button);

        JPanel ledPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        ledPanel.setBackground(Color.BLACK);
        ledPanel.add(led);

        buttonPanel.add(ledPanel);
        return buttonPanel;
    }

    private JToggleButton setupThresholdToggle() {
        thresholdToggle = new JToggleButton();
        thresholdToggle.setBackground(Color.BLACK);
        thresholdToggle.setForeground(Color.WHITE);
        int currentThreshold = settingsStorage.readLowBatteryThresholdFromDisc();
        boolean is30 = currentThreshold == HEIGHT_TOGGLE;
        thresholdToggle.setSelected(is30);
        thresholdToggle.setText(is30 ? "30%" : "10%");
        thresholdToggle.addActionListener(e -> {
            boolean selected = thresholdToggle.isSelected();
            int newThreshold = selected ? HEIGHT_TOGGLE : 10;
            thresholdToggle.setText(selected ? "30%" : "10%");
            BatteryStateController.getInstance().updateLowBatteryThreshold(newThreshold);
        });
        thresholdToggle.setPreferredSize(new Dimension(2 * HEIGHT_TOGGLE, HEIGHT_TOGGLE));
        return thresholdToggle;
    }

    JButton getButton(){
        return button;
    }

    JLabel getShaveReadyIcon(){
        return shaveReadyIcon;
    }
}
