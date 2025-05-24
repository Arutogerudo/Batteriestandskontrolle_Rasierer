package userInterface;

import javax.swing.*;

import batteryLogic.BatteryStateController;
import batteryLogic.DisplayStates;
import batteryLogic.InteractionHandler;
import batteryLogic.OperationController;
import hardwareAbstraction.ButtonInput;
import hardwareAbstraction.ChargingDetection;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;

import java.awt.*;
import java.util.Objects;

/**
 * This class represents a simple GUI for the battery status check of a shaver.
 */
public class SimpleGUI {
    private static final int WIDTH_SCREEN = 200;
    private static final int HEIGHT_SCREEN = 350;
    private static final int WIDTH_HEIGHT_BUTTON = 140;
    private static final int VGAP = 20;
    private static final int TEXT_SIZE = 80;
    private final VisualOutputController visualOutputController;
    private final OperationController operationController;
    private static JButton button;
    private final VoltageSimulator simulator;
    private final VoltageSensor sensor;
    private final InteractionHandler handler;
    private final BatteryStateController batteryController;
    private final ChargingDetection chargingDetecter;

    /**
     * Constructor for the SimpleGUI class.
     * @param simulator The voltage simulator used to simulate the battery voltage.
     */
    public SimpleGUI(VoltageSimulator simulator){
        this.simulator = simulator;
        this.sensor = new VoltageSensor(simulator);
        this.chargingDetecter = new ChargingDetection(simulator);

        button = new JButton();

        handler = new InteractionHandler();
        new ButtonInput(button, handler);

        batteryController = new BatteryStateController(simulator);
        visualOutputController = new VisualOutputController(simulator, handler);
        operationController = new OperationController();
        setupPanel(visualOutputController.getLedController().getLedPanel(), visualOutputController.getDisplayed());
    }

    /**
     * Updates the GUI based on the current state of the battery and user interactions.
     */
    public void update(){
        boolean showPercentage;
        showPercentage = handler.getDisplayState() == DisplayStates.STATE_OF_CHARGE;
        operationController.updateOperationState(simulator, handler, chargingDetecter);
        visualOutputController.updateDisplay(batteryController.calculateStateOfCharge(sensor.readVoltage()), showPercentage);
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

        display.add(bottomPanel, BorderLayout.SOUTH);
        display.add(topPanel, BorderLayout.NORTH);

        display.setVisible(true);
    }
}
