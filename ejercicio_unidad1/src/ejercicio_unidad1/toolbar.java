package ejercicio_unidad1;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.WebSocket.Listener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JPanel;

public class toolbar extends JPanel  implements ActionListener{
	private JButton but;
    private JButton bye;
    private listener textListener;

    public toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        but = new JButton("hello");
        bye = new JButton("bye");

        but.addActionListener(this);
        bye.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(but);
        add(bye);
    }

    public void setlistener(listener list) {
        this.textListener = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == but) {
            if (textListener != null) {
                textListener.textEmitted("hello");
            }
        } else if (clicked == bye) {
            if (textListener != null) {
                textListener.textEmitted("bye");
            }
        }
    }
}
