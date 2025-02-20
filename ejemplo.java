package a2203245140_tareas_unidad01;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ejemplo extends JFrame implements
ActionListener {
static JLabel La,Lb,Lc;
static JButton Bcalcular, Bsalir;
static JTextField Ta,Th, Tb;
public ejemplo()

{
this.setLayout (new FlowLayout (FlowLayout.CENTER,3,3) ) ;
//Instancia un objeto FlowLayout object alineado al centro y con una separacion de 3px en horizonal y vertical
this.setTitle("Layout FlowLayout Jpanel");
this.setBounds(10, 10, 550, 100);
JPanel Panel = new JPanel();

	La = new JLabel ("Altura-->");
	Lb = new JLabel ("Base -->");
	Lc = new JLabel("El area");
	Th = new JTextField("") ;
	Tb = new JTextField("") ;
	Ta = new JTextField("") ;
	Ta.setEditable(false);
	Bcalcular = new JButton();
	Bcalcular.setText("Calcular");
	Bcalcular.addActionListener(this);
	Bsalir = new JButton();
	Bsalir.setText("Salir");
	Bsalir.addActionListener(this);
	Panel.add (La) ;
	Panel.add (Th) ;
	Panel.add (Lb);
	Panel.add (Tb) ;
	Panel.add (Bcalcular);
	Panel.add (Lc) ;
	Panel.add (Ta) ;
	Panel.add (Bsalir);
	add (Panel);
	}

@Override
public void actionPerformed (ActionEvent e) {
if (e.getSource()==Bcalcular) {
	
double h,b,a;
String Sh, Sb;

Sh=Th.getText();
Sb= Tb.getText();

if (!Sh.isEmpty()&&!Sb.isEmpty() )
{
h=Double.parseDouble(Sh);
b=Double.parseDouble(Sb);
a = (h*b)/2;
Ta.setText (String.valueOf(a));
}

else JOptionPane.showMessageDialog(this, "Lo siento un o dos textbos estan vacios");
}
else if (e.getSource()==Bsalir)
System.exit(0);
}

public static void main(String parametro[])
{
  ejemplo ventana = new ejemplo();
ventana.setVisible(true);
}


}
