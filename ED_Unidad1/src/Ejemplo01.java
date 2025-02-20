import java.awt. EventQueue;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
public class Ejemplo01  extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel PanelPrincipal;
	private JTextField T1, T2;
	private JButton Bsalir,Bagregar;
	private JTextArea Tr;
	
	
	public Ejemplo01() {
		
	
	setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	
	setBounds (100, 100, 450, 300);
	
	PanelPrincipal = new JPanel();
	PanelPrincipal. setBorder(new EmptyBorder (5, 5, 5, 5));
	
	setContentPane(PanelPrincipal);
	PanelPrincipal.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Nombre Profesor");
	lblNewLabel. setBounds (26, 43, 111, 14);
	PanelPrincipal. add (lblNewLabel);
	
	T1 = new JTextField();
	
	T1. setBounds (161, 40, 96, 20);
	PanelPrincipal.add (T1) ;
	T1.setColumns(10);
	
	JLabel LblApellido = new JLabel ("Apellido");
	LblApellido.setBounds(26, 80, 111, 14);
	PanelPrincipal.add(LblApellido);
	
	
	T2 = new JTextField();
	T2.setColumns (10);
	T2.setBounds (161, 77, 96, 20);
	PanelPrincipal.add(T2);
	
	Bagregar = new JButton ("Agregar");
	Bagregar.setBounds (302, 39, 89, 23);
	PanelPrincipal.add (Bagregar);
	
	Bsalir = new JButton ("Salir");
	Bsalir.setBounds (302, 76, 89, 23);
	PanelPrincipal.add (Bsalir);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	scrollPane.setBounds (26, 123, 391, 131);
	PanelPrincipal.add (scrollPane);
	
	Tr = new JTextArea ();
	scrollPane.setViewportView (Tr);
	}
	
	
	public String getT1() {
	return this.T1.getText();
	}
	
	public String getT2() {
	return this.T2.getText();
	}
	
	public void setT1(String texto)
	{
		
	 this.T1.setText(texto);
	 }
	public void setT2(String texto){
			this. T2. setText(texto);
			}
			
			public void setTr(String texto) {
				this. Tr. setText(texto);
				}

			public JButton bsalir()
			{
			return this.Bsalir;
			}
			public JButton bagregar(){
			return this. Bagregar;
			}
	

		public void limparText()
		{
		this. T1. setText("");
		this. T2.setText("");
		}
}
