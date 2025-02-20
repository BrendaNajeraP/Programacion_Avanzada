package ejercicio_unidad1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JTextArea;

public class Mainframe extends JFrame {
	
	private textpanel textpanel;
    private toolbar tool;
    private form formp;
    private JFileChooser fileChooser;

    public Mainframe() {
        super("a2203245140 Brenda");

        setLayout(new BorderLayout());
        tool = new toolbar();
        textpanel = new textpanel();
        formp = new form();
        
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());
        
        setJMenuBar(createMenuBar());

        tool.setlistener(new listener() {
            public void textEmitted(String text) {
                textpanel.appendText(text);
            }
        });

        formp.setFormListener(new FormListener() {
            public void formEventOcurred(formevent e) {
                String name = e.getName();
                String ocu = e.getOcu();
                int ageCat = e.getAgeCategory();
                String empCat = e.getEmploymentCategory();
                
                
                textpanel.appendText(name + " : " + ocu + " , " + ageCat+ " , "+empCat);
                
                System.out.println(e.getGender());
            }
        });

        add(formp, BorderLayout.WEST);
        add(tool, BorderLayout.NORTH);
        add(textpanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension (500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private JMenuBar createMenuBar () {
    	JMenuBar menuBar = new JMenuBar ();
    	
    	
    	JMenu fileMenu = new JMenu ("file ");
    	
    	JMenuItem exportDataItem = new JMenuItem ("Export Data...");
    	JMenuItem importDataItem = new JMenuItem ("Import Data...");
    	JMenuItem exitItem = new JMenuItem ("exit");
    	
    	fileMenu.add(exportDataItem);
    	fileMenu.add(importDataItem);
    	fileMenu.addSeparator();
    	fileMenu.add(exitItem);
    	
    	JMenu windowMenu = new JMenu ("Window");
    	JMenu showMenu = new JMenu ("show");
    	JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem ("person form");
    	showFormItem.setSelected(true);
    	
    	
    	showMenu.add(showFormItem);
    	windowMenu.add(showMenu);
    	
    	menuBar.add(fileMenu);
    	menuBar.add(windowMenu);
    	
    	showFormItem.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent ev) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)ev.getSource();
				
				formp.setVisible(menuItem.isSelected());
			}
    		
    	});
    	
    	fileMenu.setMnemonic(KeyEvent.VK_F);
    	exitItem.setMnemonic(KeyEvent.VK_X);
    	
    	exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
    	
    	
    	importDataItem.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fileChooser.showOpenDialog(Mainframe.this)== JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
    		
    	});
    	
    	exportDataItem.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (fileChooser.showSaveDialog(Mainframe.this)== JFileChooser.APPROVE_OPTION) {
					System.out.println(fileChooser.getSelectedFile());
				}
			}
    		
    	});
    	exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				
			int action= 	JOptionPane.showConfirmDialog(Mainframe.this, "exit??", "Confirm",
					JOptionPane.OK_CANCEL_OPTION);
				
			if(action == JOptionPane.OK_OPTION) {
			System.exit(0);
			 }
			}
    		
    	});
    	
    	return menuBar;
    }
}
