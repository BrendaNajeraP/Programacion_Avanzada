package a2203245140_tareas_unidad01;

import javax.swing.*;
import java.awt.*;

public class buttonbox2 extends JFrame {

    public buttonbox2() {
        setTitle("RadioButton");
        setSize(250, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addLabel(panel, "Are you ready?");
        addRadioButton(panel, "Yes", "ready", false);
        addRadioButton(panel, "No", "ready", false);
        addRadioButton(panel, "Maybe", "ready", true);

        addLabel(panel, "Male or female?");
        addRadioButton(panel, "Male", "sex", false);
        addRadioButton(panel, "Female", "sex", false);
        addRadioButton(panel, "Not sure", "sex", true);

        add(panel);
    }

    private void addLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);
    }

    private void addRadioButton(JPanel panel, String text, String groupName, boolean isSelected) {
        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButton = new JRadioButton(text, isSelected);
        radioButton.setActionCommand(text);
        group.add(radioButton);
        panel.add(radioButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	buttonbox2 frame = new buttonbox2();
            frame.setVisible(true);
        });
    }
}

