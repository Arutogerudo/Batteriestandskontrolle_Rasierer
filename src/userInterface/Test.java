package userInterface;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Test().createAndShowGui();
        });
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("Icon Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel();

        // Lade das Icon vom Ressourcenpfad
        URL imageUrl = getClass().getResource("/resources/rasur_moeglich_icon.jpeg");
        if (imageUrl == null) {
            System.out.println("Bild konnte NICHT geladen werden!");
            label.setText("Bild nicht gefunden!");
        } else {
            System.out.println("Bild geladen von: " + imageUrl);

            ImageIcon icon = new ImageIcon(imageUrl);
            // Skalieren auf 50x50 px für gute Sichtbarkeit
            Image scaled = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
        }

        // Optional: Hintergrund sichtbar machen
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setPreferredSize(new Dimension(60, 60));
        label.setBorder(BorderFactory.createLineBorder(Color.RED));

        frame.add(label);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
