package a2203245140_tareas_unidad01;

	import javax.swing.*;
	import java.awt.*;

	public class buttonbox extends JFrame {

	    public buttonbox() {
	        setTitle("RadioButton");
	        setSize(250, 150);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	        JLabel label = new JLabel("listo");
	        label.setFont(new Font("Arial", Font.BOLD, 14));
	        panel.add(label);

	        ButtonGroup buttonGroup = new ButtonGroup();
	        JRadioButton yesRadioButton = new JRadioButton("Yes");
	        JRadioButton noRadioButton = new JRadioButton("No");
	        JRadioButton maybeRadioButton = new JRadioButton("Maybe");
	        
	        
	        maybeRadioButton.setSelected(true);

	      
	        buttonGroup.add(yesRadioButton);
	        buttonGroup.add(noRadioButton);
	        buttonGroup.add(maybeRadioButton);

	        panel.add(yesRadioButton);
	        panel.add(noRadioButton);
	        panel.add(maybeRadioButton);

	        add(panel);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	buttonbox frame = new buttonbox();
	            frame.setVisible(true);
	        });
	    }
	}


