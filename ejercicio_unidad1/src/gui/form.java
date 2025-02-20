package gui;

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
    private JLabel name, ocu, taxLabel;
    private JTextField namef, ocuf, taxField;
    private JButton ok;
    private FormListener formListener;
    private JList<AgeCategory> ageList;
    private JComboBox<String> empCombo;
    private JCheckBox citizenCheck;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;

    public form() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        name = new JLabel("Name: ");
        ocu = new JLabel("Ocupación: ");
        namef = new JTextField(10);
        ocuf = new JTextField(10);
        ageList = new JList<>();
        empCombo = new JComboBox<>();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        ok = new JButton("OK");

        // Configuración de atajos de teclado
        ok.setMnemonic(KeyEvent.VK_O);
        name.setDisplayedMnemonic(KeyEvent.VK_N);
        name.setLabelFor(namef);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");
        genderGroup = new ButtonGroup();
        maleRadio.setSelected(true);
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Configuración de Tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);
        citizenCheck.addActionListener(e -> {
            boolean isTicked = citizenCheck.isSelected();
            taxLabel.setEnabled(isTicked);
            taxField.setEnabled(isTicked);
        });

        // Lista de edades
        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);
        ageList.setPreferredSize(new Dimension(110, 68));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Combo box de empleo
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement("Employed");
        empModel.addElement("Self-Employed");
        empModel.addElement("Unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        // Botón OK
        ok.addActionListener(e -> {
            String name = namef.getText();
            String ocup = ocuf.getText();
            AgeCategory ageCat = ageList.getSelectedValue();
            String empCat = (String) empCombo.getSelectedItem();
            String taxId = taxField.getText();
            boolean usCitizen = citizenCheck.isSelected();
            String gender = genderGroup.getSelection().getActionCommand();

            formevent ev = new formevent(this.name);
            if (formListener != null) {
                formListener.formEventOcurred(ev);
            }
        });

        // Bordes
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Layout
        layoutComponents();
    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Primera fila
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(name, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(namef, gc);

        // Segunda fila
        gc.gridy++;
        add(ocu, gc);

        gc.gridx = 1;
        add(ocuf, gc);

        // Edad
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        add(ageList, gc);

        // Empleo
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        add(empCombo, gc);

        // Ciudadanía
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Citizen: "), gc);

        gc.gridx = 1;
        add(citizenCheck, gc);

        // Tax ID
        gc.gridy++;
        gc.gridx = 0;
        add(taxLabel, gc);

        gc.gridx = 1;
        add(taxField, gc);

        // Género
        gc.gridy++;
        gc.gridx = 0;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        add(maleRadio, gc);

        gc.gridy++;
        add(femaleRadio, gc);

        // Botón OK
        gc.gridy++;
        gc.weighty = 2.0;
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