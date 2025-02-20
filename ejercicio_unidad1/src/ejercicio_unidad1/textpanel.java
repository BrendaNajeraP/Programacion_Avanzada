package ejercicio_unidad1;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class textpanel extends JPanel {
	
	    private JTextArea textarea;

	    public textpanel() {
	        textarea = new JTextArea();
	        setLayout(new BorderLayout());
	        add(new JScrollPane(textarea), BorderLayout.CENTER);
	    }

	    public void appendText(String text) {
	        textarea.append(text);
	    }

}
