package a2203245140_tareas_unidad01;

import javax.swing.*;
import java.awt.*;

public class Checkbox extends JFrame {

	 public Checkbox() {
	        setTitle("CheckBox");
	        setSize(250, 140);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        JLabel label = new JLabel("Opciones");
	        label.setFont(new Font("Arial", Font.BOLD, 14));
	        panel.add(label);

	        JCheckBox checkBox1 = new JCheckBox("Enable feature ABC");
	        JCheckBox checkBox2 = new JCheckBox("Enable feature XYZ");
	        JCheckBox checkBox3 = new JCheckBox("Enable feature WWW");
	        
	        
	        checkBox2.setSelected(true);

	        panel.add(checkBox1);
	        panel.add(checkBox2);
	        panel.add(checkBox3);

	        add(panel);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	Checkbox frame = new Checkbox();
	            frame.setVisible(true);
	        });
	    }
	}

