import javax.swing.*;

import BatteryLogic.BatteryStateController;
import BatteryLogic.DisplayStates;
import BatteryLogic.InteractionHandler;
import BatteryLogic.OperationController;
import HardwareAbstraction.ButtonInput;
import HardwareAbstraction.VoltageSimulator;

import java.awt.*;

public class SimpleGUI {
    private VisualOutputController visualOutputController;
    private OperationController operationController;
    private static JButton button;
    private boolean showPercentage;
    private VoltageSimulator simulator;
    private InteractionHandler handler;
    private BatteryStateController batteryController;

    SimpleGUI(VoltageSimulator simulator){
        this.simulator = simulator;

        button = new JButton();

        handler = new InteractionHandler();
        new ButtonInput(button, handler);

        batteryController = new BatteryStateController(simulator);
        visualOutputController = new VisualOutputController(simulator);
        operationController = new OperationController();
        setupPanel(visualOutputController.getLedController().getLedPanel(), visualOutputController.getDisplayed());
    }

    void update(){
        if (handler.getDisplayState() == DisplayStates.STATE_OF_CHARGE){
            showPercentage = true;
        } else {
            showPercentage = false;
        }
        operationController.updateOperationState(simulator, handler);
        visualOutputController.updateDisplay(batteryController.calculateStateOfCharge(), showPercentage);
    }

    private void setupPanel(LEDPanel led, JLabel statusLabel){
        JFrame display = new JFrame("Display Rasierapparat");
        display.setSize(200, 350);
        display.getContentPane().setBackground(Color.BLACK);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLayout(new BorderLayout());

        ImageIcon originalImage = new ImageIcon("Batteriestandskontrolle_Rasierer\\src\\resources\\powerIcon.jpg");
        ImageIcon scaledImage = new ImageIcon(originalImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        button.setIcon(scaledImage);
        button.setPreferredSize(new Dimension(150, 150));

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
