package view;

import model.Attribute;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AttributeDefinitionView extends JFrame {    // Constantes de colores
    private static final Color COLOR_BACKGROUND = new Color(178, 140, 178); // Morado claro con brillo 20%
    private static final Color COLOR_TEXT = Color.BLACK; // Cambiado a negro para mejor contraste
    private static final Color COLOR_BUTTON = new Color(128, 0, 128); // Morado
    private static final Color COLOR_BUTTON_TEXT = Color.WHITE;
    private static final Color COLOR_INPUT_BG = Color.BLACK;
    private static final Color COLOR_INPUT_TEXT = Color.WHITE;

    private List<JTextField> nameFields;
    private List<JComboBox<String>> typeComboBoxes;
    private JButton submitButton;
    private JButton addAttributeButton;
    private JPanel attributesPanel;
    private JScrollPane scrollPane;
    private static final String[] TYPES = {"String", "Integer"};
    private static final int MIN_ATTRIBUTES = 3;

    public AttributeDefinitionView() {
        initComponents();
    }    private void initComponents() {
        setTitle("Definir Atributos de Entidad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        getContentPane().setBackground(COLOR_BACKGROUND);        // Main container with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Help text at the top
        JLabel helpLabel = new JLabel("<html>Defina al menos " + MIN_ATTRIBUTES + " atributos para su entidad.<br>" +
                "Debe incluir al menos un atributo tipo String para usar como identificador único.</html>");
        helpLabel.setForeground(COLOR_TEXT);
        helpLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(helpLabel, BorderLayout.NORTH);        // Panel for attributes with scroll
        attributesPanel = new JPanel();
        attributesPanel.setBackground(COLOR_BACKGROUND);
        attributesPanel.setLayout(new BoxLayout(attributesPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(attributesPanel);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128))); // Borde morado
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        addAttributeButton = new JButton("Agregar Atributo");
        submitButton = new JButton("Confirmar");
        
        // Estilo de botones
        for (JButton button : new JButton[]{addAttributeButton, submitButton}) {
            button.setBackground(COLOR_BUTTON);
            button.setForeground(COLOR_BUTTON_TEXT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }
        
        buttonPanel.add(addAttributeButton);
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Initialize lists
        nameFields = new ArrayList<>();
        typeComboBoxes = new ArrayList<>();

        // Add initial attribute fields
        for (int i = 0; i < MIN_ATTRIBUTES; i++) {
            addAttributeField();
        }

        // Add attribute button listener
        addAttributeButton.addActionListener(e -> addAttributeField());

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }    private void addAttributeField() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.setBackground(COLOR_BACKGROUND);
        
        JTextField nameField = new JTextField(15);
        nameField.setBackground(COLOR_INPUT_BG);
        nameField.setForeground(COLOR_INPUT_TEXT);
        nameField.setCaretColor(COLOR_INPUT_TEXT);
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(128, 0, 128)),
            BorderFactory.createEmptyBorder(2, 2, 2, 2)
        ));
        
        JComboBox<String> typeCombo = new JComboBox<>(TYPES);
        typeCombo.setBackground(COLOR_BUTTON);
        typeCombo.setForeground(COLOR_BUTTON_TEXT);
        
        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setForeground(COLOR_TEXT);
        JLabel typeLabel = new JLabel("Tipo:");
        typeLabel.setForeground(COLOR_TEXT);
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(typeLabel);
        panel.add(typeCombo);

        if (nameFields.size() >= MIN_ATTRIBUTES) {            JButton removeButton = new JButton("X");
            removeButton.setBackground(new Color(139, 0, 0)); // Rojo oscuro
            removeButton.setForeground(COLOR_BUTTON_TEXT);
            removeButton.setBorderPainted(false);
            removeButton.setFocusPainted(false);
            removeButton.setMargin(new Insets(0, 5, 0, 5));
            removeButton.addActionListener(e -> {
                if (nameFields.size() > MIN_ATTRIBUTES) {
                    attributesPanel.remove(panel);
                    nameFields.remove(nameField);
                    typeComboBoxes.remove(typeCombo);
                    attributesPanel.revalidate();
                    attributesPanel.repaint();
                    pack();
                }
            });
            panel.add(removeButton);
        }

        nameFields.add(nameField);
        typeComboBoxes.add(typeCombo);
        attributesPanel.add(panel);
        attributesPanel.revalidate();
        attributesPanel.repaint();
        pack();
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public List<Attribute> getDefinedAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < nameFields.size(); i++) {
            String name = nameFields.get(i).getText().trim();
            String type = (String) typeComboBoxes.get(i).getSelectedItem();
            if (!name.isEmpty()) {
                attributes.add(new Attribute(name, type));
            }
        }
        return attributes;
    }

    public boolean validateFields() {
        boolean hasStringAttribute = false;
        List<String> names = new ArrayList<>();

        for (int i = 0; i < nameFields.size(); i++) {
            String name = nameFields.get(i).getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los nombres de atributos",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (names.contains(name)) {
                JOptionPane.showMessageDialog(this,
                    "Los nombres de atributos deben ser únicos",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

            names.add(name);

            if (typeComboBoxes.get(i).getSelectedItem().equals("String")) {
                hasStringAttribute = true;
            }
        }

        if (!hasStringAttribute) {
            JOptionPane.showMessageDialog(this,
                "Debe definir al menos un atributo tipo String para usar como identificador único",
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (names.size() < MIN_ATTRIBUTES) {
            JOptionPane.showMessageDialog(this,
                "Debe definir al menos " + MIN_ATTRIBUTES + " atributos",
                "Error de Validación",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
