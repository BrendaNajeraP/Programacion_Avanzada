package a2203245140_tareas_unidad01;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class ejemplo4 {

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	Marco_Layout marco=new Marco_Layout() ;
	marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	marco.setVisible(true);
	}
	}


	class Marco_Layout extends JFrame{
			
	public Marco_Layout (){
	setTitle("Prueba Acciones");
	setBounds (600,350,600,300);
	
	Panel_Layout lamina=new Panel_Layout();
	add(lamina);
	}
	
}
