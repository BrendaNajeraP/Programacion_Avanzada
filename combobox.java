package a2203245140_tareas_unidad01;
import javax.swing.*;
import java.awt.event.*;

	public class combobox {
	    public static void main(String[] args) {
	        JFrame frame = new JFrame("ComboBox Najera ");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 200);

	        JPanel panel = new JPanel();

	        String[] options = {"opcion 1", "opcion 2", "opcion 3", "opcion 4"};
	        JComboBox<String> comboBox = new JComboBox<>(options);

	        JButton button = new JButton("Submit");
	        JLabel label = new JLabel("Selected: ");

	        button.addActionListener(e -> label.setText("Selected: " + comboBox.getSelectedItem()));

	        panel.add(comboBox);
	        panel.add(button);
	        panel.add(label);

	        frame.add(panel);
	        frame.setVisible(true);
	    }
}
