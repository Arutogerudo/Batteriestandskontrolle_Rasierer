package userInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import batteryLogic.*;
import hardwareAbstraction.ButtonInput;
import hardwareAbstraction.ChargingDetection;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;
import persistenceManager.SettingsStorage;

import java.awt.*;
import java.util.Objects;

/**
 * This class represents a simple GUI for the battery status check of a shaver.
 */
public class SimpleGUI implements UIConstants {
    private final VisualOutputController visualOutputController;
    private final OperationController operationController;
    private static JButton button;
    private final VoltageSensor sensor;
    private final InteractionHandler handler;
    private final BatteryStateController batteryController;
    private final SettingsStorage settingsStorage;
    private JToggleButton thresholdToggle;
    private final JLabel shaveReadyIcon = new JLabel();
    private final CalibrationManager calibrationManager;


    /**
     * Constructor for the SimpleGUI class.
     * @param simulator The voltage simulator used to simulate the battery voltage.
     * @param tempSim Temperature simulator of battery
     */
    public SimpleGUI(VoltageSimulator simulator, TemperatureSimulator tempSim){
        this.sensor = new VoltageSensor(simulator);
        ChargingDetection chargingDetector = new ChargingDetection(simulator);
        settingsStorage = SettingsStorage.getInstance();

        button = new JButton();

        handler = new InteractionHandler();
        new ButtonInput(button, handler);

        batteryController = BatteryStateController.getInstance();
        visualOutputController = new VisualOutputController(simulator, handler, batteryController);
        operationController = new OperationController(simulator, tempSim, handler, chargingDetector);
        calibrationManager = new CalibrationManager(batteryController);
        setupPanel(visualOutputController.getLedController().getLedPanel(), visualOutputController.getDisplayed());
    }

    /**
     * Updates the GUI based on the current state of the battery and user interactions.
     */
    public void update(){
        boolean showPercentage = handler.getDisplayState() == DisplayStates.STATE_OF_CHARGE;
        boolean showRemainingRuntime = handler.getDisplayState() == DisplayStates.REMAINING_TIME;
        operationController.updateOperationState();
        visualOutputController.updateVisuals(batteryController.calculateStateOfCharge(sensor.readVoltage()), showPercentage, batteryController.calculateRemainingRuntime(sensor.readVoltage()), showRemainingRuntime);
        batteryController.monitorChargeCycle();
        calibrationManager.recalibrateIfNeeded();
        updateShaveReadyIcon();
    }

    private void setupPanel(LEDPanel led, JLabel statusLabel) {
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
        updateShaveReadyIcon();

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
            batteryController.updateLowBatteryThreshold(newThreshold);
        });
        thresholdToggle.setPreferredSize(new Dimension(2 * HEIGHT_TOGGLE, HEIGHT_TOGGLE));
        return thresholdToggle;
    }

    private void updateShaveReadyIcon() {
        shaveReadyIcon.setVisible(batteryController.calculateRemainingRuntime(sensor.readVoltage()) >= 5);
    }

}
