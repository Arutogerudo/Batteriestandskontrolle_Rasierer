package userInterface;

import javax.swing.*;

import batteryLogic.BatteryStateController;
import batteryLogic.DisplayStates;
import batteryLogic.InteractionHandler;
import batteryLogic.OperationController;
import hardwareAbstraction.ButtonInput;
import hardwareAbstraction.VoltageSimulator;

import java.awt.*;

public class SimpleGUI {
    private final VisualOutputController visualOutputController;
    private final OperationController operationController;
    private static JButton button;
    private final VoltageSimulator simulator;
    private final InteractionHandler handler;
    private final BatteryStateController batteryController;

    public SimpleGUI(VoltageSimulator simulator){
        this.simulator = simulator;

        button = new JButton();

        handler = new InteractionHandler();
        new ButtonInput(button, handler);

        batteryController = new BatteryStateController(simulator);
        visualOutputController = new VisualOutputController(simulator);
        operationController = new OperationController();
        setupPanel(visualOutputController.getLedController().getLedPanel(), visualOutputController.getDisplayed());
    }

    public void update(){
        boolean showPercentage;
        showPercentage = handler.getDisplayState() == DisplayStates.STATE_OF_CHARGE;
        operationController.updateOperationState(simulator, handler);
        visualOutputController.updateDisplay(batteryController.calculateStateOfCharge(), showPercentage);
    }

    private void setupPanel(LEDPanel led, JLabel statusLabel){
        JFrame display = new JFrame("Display Rasierapparat");
        display.setSize(200, 350);
        display.getContentPane().setBackground(Color.BLACK);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLayout(new BorderLayout());

        ImageIcon originalImage = new ImageIcon("C:\\Users\\Lara\\OneDrive - Technische Hochschule Deggendorf\\Studium\\Semester 4\\Software Engineering\\Projekt - Batteriestandkontrolle Rasierer\\Batteriestandskontrolle_Rasierer\\src\\resources\\powerIcon.jpg");
        ImageIcon scaledImage = new ImageIcon(originalImage.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH));
        button.setIcon(scaledImage);
        button.setPreferredSize(new Dimension(140, 140));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(button);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 80));
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
