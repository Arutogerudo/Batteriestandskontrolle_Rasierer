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
public class SimpleGUI {
    private static final int WIDTH_SCREEN = 250;
    private static final int HEIGHT_SCREEN = 350;
    private static final int WIDTH_HEIGHT_BUTTON = 125;
    private static final int VGAP = 20;
    private static final int TEXT_SIZE = 80;
    private static final int HEIGHT_TOGGLE = 30;
    private static final int THRESHOLD_30 = 30;
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

        batteryController = BatteryStateController.getInstance(simulator);
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

    private void setupPanel(LEDPanel led, JLabel statusLabel){
        JFrame display = new JFrame("Display Rasierapparat");
        display.setSize(WIDTH_SCREEN, HEIGHT_SCREEN);
        display.getContentPane().setBackground(Color.BLACK);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLayout(new BorderLayout());

        ImageIcon originalImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/powerIcon.jpg")));
        ImageIcon scaledImage = new ImageIcon(originalImage.getImage().getScaledInstance(WIDTH_HEIGHT_BUTTON, WIDTH_HEIGHT_BUTTON, Image.SCALE_SMOOTH));
        button.setIcon(scaledImage);
        button.setPreferredSize(new Dimension(WIDTH_HEIGHT_BUTTON, WIDTH_HEIGHT_BUTTON));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, VGAP));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(button);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, TEXT_SIZE));
        topPanel.add(statusLabel);

        JPanel ledContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        ledContainer.setBackground(Color.BLACK);
        ledContainer.add(led);
        bottomPanel.add(ledContainer, BorderLayout.EAST);

        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.BLACK);
        middlePanel.setLayout(new BorderLayout());
        middlePanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/rasur_moeglich_icon.jpeg")));
        Image scaledImageShavingReady = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        shaveReadyIcon.setIcon(new ImageIcon(scaledImageShavingReady));
        updateShaveReadyIcon();
        middlePanel.add(setupThresholdToggle(), BorderLayout.EAST);
        middlePanel.add(shaveReadyIcon, BorderLayout.WEST);

        display.add(bottomPanel, BorderLayout.SOUTH);
        display.add(middlePanel, BorderLayout.CENTER);
        display.add(topPanel, BorderLayout.NORTH);

        display.setVisible(true);
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
