package ejercicio_unidad1;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class form extends JPanel {
	
	private JLabel name;
    private JLabel ocu;
    private JTextField namef;
    private JTextField ocuf;
    private JButton ok;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public form() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        name = new JLabel("name: ");
        ocu = new JLabel("ocupacion: ");
        namef = new JTextField(10);
        ocuf = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        ok = new JButton("OK");
        
        //set meno
        ok.setMnemonic(KeyEvent.VK_O);
        name.setDisplayedMnemonic(KeyEvent.VK_N);
        name.setLabelFor(namef);
        
        
        maleRadio = new JRadioButton ("male");
        femaleRadio = new JRadioButton ("Female");
        
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");
        
        
        genderGroup = new ButtonGroup();
        
        maleRadio.setSelected(true);
        
        /// gender radio
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        //tax id
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);
        
        citizenCheck.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean isTicked = citizenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
        	
        });
        
        
        //list box

        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 68));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);
        
        // combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("umemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);
        
        
        
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namef.getText();
                String ocup = ocuf.getText();
                AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
                String empCat = (String)empCombo.getSelectedItem();
                String taxId = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();
                
                String gender = genderGroup.getSelection().getActionCommand();
                		
               System.out.println(empCat);
                
                formevent ev = new formevent(this, name, ocup, ageCat.getId(), 
                		empCat, taxId, usCitizen, gender);

                if (formListener != null) {
                    formListener.formEventOcurred(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("add person");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outer, innerBorder));
        layoutComponents();
    }
    
    public void layoutComponents () {
    	
    
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
// first row
        gc.gridy = 0;
        
        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(name, gc);

        gc.gridx = 1;
       gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(namef, gc);

        ///second row 
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.1;
      
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(ocu, gc);

       
        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(ocuf, gc);
        
        //// third
        
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;
        
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel ("Age: "), gc);
      
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);
///7cuatro 
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 2.0;
        
      
        gc.gridx = 0;
        gc.insets = new Insets (0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        
        add(new JLabel ("Employ: "), gc);
        
        gc.gridx = 1;
       
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(empCombo, gc);
        
        ////check box
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
      
        gc.gridx = 0;
        gc.insets = new Insets (0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        
        add(new JLabel ("CIty: "), gc);
        
        gc.gridx = 1;
       
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(citizenCheck, gc);
        
        ///// textfield
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
        
      
        gc.gridx = 0;
        gc.insets = new Insets (0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        
        add(taxLabel, gc);
        
        gc.gridx = 1;
       
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(taxField, gc);
        
        ///
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.05;
        
      
        gc.gridx = 0;
        gc.insets = new Insets (0,0,0,5);
        gc.anchor = GridBagConstraints.LINE_END;
        
        add(new JLabel ("Gender: "), gc);
        
        gc.gridx = 1;
       
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(maleRadio, gc);
        
        ////
        
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty = 0.2;
  
        gc.gridx = 1;
       
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(femaleRadio, gc);
    ///////////
        
        gc.gridy++;
        
        gc.weightx = 1;
        gc.weighty=2.0;
        
        gc.gridx = 1;
        
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets (0,0,0,0);
        add(ok, gc);
        
        

    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}

class AgeCategory {
    private int id;
    private String text;

    public AgeCategory(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public int getId() {
        return id;
    
    }
}
