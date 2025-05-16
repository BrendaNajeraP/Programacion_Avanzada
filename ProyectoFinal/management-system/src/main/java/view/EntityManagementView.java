package view;

import model.Attribute;
import model.Entity;
import model.EntityTableModel;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List; // Explicitly import java.util.List
import com.management.util.Storage;

public class EntityManagementView extends JFrame {    // Constantes de colores
    private static final Color COLOR_BACKGROUND = new Color(178, 140, 178); // Morado claro con brillo 20%
    private static final Color COLOR_TEXT = Color.WHITE; // Cambiado a blanco para mejor contraste
    private static final Color COLOR_BUTTON = new Color(128, 0, 128); // Morado
    private static final Color COLOR_BUTTON_TEXT = Color.WHITE;
    private static final Color COLOR_TABLE_HEADER = new Color(139, 0, 0); // Rojo oscuro
    private static final Color COLOR_TABLE_CELL = Color.BLACK;
    private static final Color COLOR_TABLE_SELECTION = new Color(128, 0, 128); // Morado
    private static final Color COLOR_TABLE_TEXT = Color.WHITE;

    private JTable entityTable;
    private EntityTableModel tableModel;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private JComboBox<String> identifierCombo;    
    private java.util.List<Attribute> attributes; // Fully qualified List
    private String selectedIdentifierField;
    private JDialog detailDialog;
    private Map<String, JTextField> detailFields;

    public EntityManagementView(java.util.List<Attribute> attributes) { // Fully qualified List
        this.attributes = attributes;
        
        // Ensure proper initialization order
        createComponents();
        layoutComponents();
        initializeDetailDialog();
        
        // Add window listener for saving data on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Storage.saveAttributes(attributes);
                Storage.saveEntities(tableModel.getEntities());
                dispose();
                System.exit(0);
            }
        });
        
        // Final window setup
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        // Setup window properties
        setTitle("Sistema de Gestión de Entidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(COLOR_BACKGROUND);

        // Initialize table model and table
        tableModel = new EntityTableModel(attributes);
        entityTable = new JTable(tableModel);
        entityTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        entityTable.setRowHeight(25);
        entityTable.setBackground(COLOR_TABLE_CELL);
        entityTable.setForeground(COLOR_TABLE_TEXT);
        entityTable.setSelectionBackground(COLOR_TABLE_SELECTION);
        entityTable.setSelectionForeground(COLOR_TABLE_TEXT);
        entityTable.getTableHeader().setBackground(COLOR_TABLE_HEADER);
        entityTable.getTableHeader().setForeground(COLOR_TEXT);
        entityTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        entityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        entityTable.setRowSelectionAllowed(true);
        
        // Grid color
        entityTable.setGridColor(new Color(64, 0, 64)); // Morado oscuro para la grilla

        // Add selection listener to update button states
        entityTable.getSelectionModel().addListSelectionListener(e -> {
            boolean hasSelection = entityTable.getSelectedRow() != -1;
            editButton.setEnabled(hasSelection);
            deleteButton.setEnabled(hasSelection);
        });

        // Create buttons with styling
        createButton = new JButton("Agregar Nueva Entidad");
        editButton = new JButton("Editar");
        deleteButton = new JButton("Eliminar");
        
        // Style all buttons
        for (JButton button : new JButton[]{createButton, editButton, deleteButton}) {
            button.setFont(new Font("Arial", Font.BOLD, 12));
            button.setBackground(COLOR_BUTTON);
            button.setForeground(COLOR_BUTTON_TEXT);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
        }
        
        // Initial button states
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        // Setup delete action
        deleteButton.addActionListener(e -> deleteSelectedEntity());
        
        // Setup edit action
        editButton.addActionListener(e -> editSelectedEntity());

        // Initialize identifier combo
        List<String> stringAttributes = attributes.stream()
            .filter(attr -> attr.getType().equals("String"))
            .map(Attribute::getName)
            .toList();
        identifierCombo = new JComboBox<>(stringAttributes.toArray(new String[0]));
        identifierCombo.setBackground(COLOR_BUTTON);
        identifierCombo.setForeground(COLOR_BUTTON_TEXT);
        selectedIdentifierField = stringAttributes.isEmpty() ? "" : stringAttributes.get(0);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Top panel for controls
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlPanel.setBackground(COLOR_BACKGROUND);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JLabel identifierLabel = new JLabel("Identificador Único:");
        identifierLabel.setForeground(COLOR_TEXT);
        controlPanel.add(identifierLabel);
        controlPanel.add(identifierCombo);
        
        // Button panel with some spacing
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        controlPanel.add(buttonPanel);
        
        add(controlPanel, BorderLayout.NORTH);

        // Center panel for table
        JScrollPane scrollPane = new JScrollPane(entityTable);
        scrollPane.getViewport().setBackground(COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createLineBorder(new Color(128, 0, 128)) // Borde morado
        ));
        
        add(scrollPane, BorderLayout.CENTER);

        // Status panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusPanel.setBackground(COLOR_BACKGROUND);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel statusLabel = new JLabel("Total de registros: " + tableModel.getRowCount());
        statusLabel.setForeground(COLOR_TEXT);
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        // Update row count when table model changes
        tableModel.addTableModelListener(e -> {
            statusLabel.setText("Total de registros: " + tableModel.getRowCount());
        });

        pack();
    }

    private void initializeDetailDialog() {
        detailDialog = new JDialog(this, "Detalles de la Entidad", true);
        detailDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        detailFields = new HashMap<>();
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create fields panel
        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setBackground(COLOR_BACKGROUND);
        fieldsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(128, 0, 128)), // Borde morado
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        for (Attribute attr : attributes) {
            // Label
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(attr.getName() + ":");
            label.setForeground(COLOR_TEXT);
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            fieldsPanel.add(label, gbc);

            // Text field
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            JTextField field = new JTextField(20);
            field.setEditable(false);
            field.setBackground(COLOR_TABLE_CELL);
            field.setForeground(COLOR_TEXT);
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(128, 0, 128)), // Borde morado
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            ));
            fieldsPanel.add(field, gbc);
            detailFields.put(attr.getName(), field);

            row++;
        }

        // Add components to main panel
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);

        // Add buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(COLOR_BACKGROUND);
        JButton closeButton = new JButton("Cerrar");
        closeButton.setBackground(COLOR_BUTTON);
        closeButton.setForeground(COLOR_BUTTON_TEXT);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> detailDialog.dispose());
        buttonsPanel.add(closeButton);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Configure dialog
        detailDialog.add(mainPanel);
        detailDialog.pack();
        detailDialog.setMinimumSize(new Dimension(400, 300));
        detailDialog.setLocationRelativeTo(this);
    }

    private void showEntityDetails(int rowIndex) {
        Entity entity = tableModel.getEntityAt(rowIndex);
        
        // Update fields with entity values
        for (Attribute attr : attributes) {
            JTextField field = detailFields.get(attr.getName());
            if (field != null) {
                Object value = entity.getAttribute(attr.getName());
                field.setText(value != null ? value.toString() : "");
            }
        }

        // Update dialog title with identifier
        Object idValue = entity.getAttribute(selectedIdentifierField);
        String identifierValue = idValue != null ? idValue.toString() : "";
        detailDialog.setTitle(String.format("Detalles de la Entidad (ID: %s)", identifierValue));

        // Show dialog
        detailDialog.setVisible(true);
    }

    public void addCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void showCreateEntityDialog() {
        // Create a panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(COLOR_BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel for input fields with grid layout
        JPanel inputPanel = new JPanel(new GridLayout(attributes.size(), 2, 5, 5));
        inputPanel.setBackground(COLOR_BACKGROUND);
        java.util.List<JTextField> inputFields = new ArrayList<>();
        
        for (Attribute attr : attributes) {
            // Create label with proper styling
            String labelText = attr.getName();
            if (attr.getName().equals(selectedIdentifierField)) {
                labelText += " (Identificador Único)";
            }
            
            JLabel label = new JLabel(labelText + ":");
            label.setForeground(COLOR_TEXT);
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            inputPanel.add(label);
            
            // Create and style text field
            JTextField field = new JTextField(20);
            field.setBackground(COLOR_TABLE_CELL);
            field.setForeground(COLOR_TEXT);
            field.setCaretColor(COLOR_TEXT);
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(128, 0, 128)), // Borde morado
                BorderFactory.createEmptyBorder(2, 2, 2, 2)
            ));
            inputFields.add(field);
            inputPanel.add(field);
        }

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Add help text at the top
        JLabel helpText = new JLabel("<html>Complete todos los campos. El campo marcado como Identificador Único debe ser único en el sistema.</html>");
        helpText.setForeground(COLOR_TEXT);
        helpText.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(helpText, BorderLayout.NORTH);

        // Create a custom dialog
        JDialog dialog = new JDialog(this, "Crear Nueva Entidad", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(COLOR_BACKGROUND);
        
        JButton saveButton = new JButton("Guardar");
        JButton cancelButton = new JButton("Cancelar");
        
        // Style buttons
        for (JButton button : new JButton[]{saveButton, cancelButton}) {
            button.setBackground(COLOR_BUTTON);
            button.setForeground(COLOR_BUTTON_TEXT);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            buttonPanel.add(button);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(mainPanel);
        
        // Button actions
        saveButton.addActionListener(e -> {
            boolean isValid = validateAndSaveEntity(inputFields);
            if (isValid) {
                dialog.dispose();
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private boolean validateAndSaveEntity(List<JTextField> inputFields) {
        // Validate all fields first
        for (int i = 0; i < attributes.size(); i++) {
            String value = inputFields.get(i).getText().trim();
            
            if (value.isEmpty()) {
                showError("Por favor complete todos los campos");
                return false;
            }

            if (attributes.get(i).getType().equals("Integer")) {
                try {
                    Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    showError(attributes.get(i).getName() + " debe ser un número entero válido");
                    return false;
                }
            }
        }

        // Check identifier uniqueness
        String identifierValue = inputFields.get(
            attributes.indexOf(
                attributes.stream()
                    .filter(a -> a.getName().equals(selectedIdentifierField))
                    .findFirst()
                    .get()
            )
        ).getText().trim();

        if (!tableModel.isUniqueIdentifier(selectedIdentifierField, identifierValue)) {
            showError("El valor del identificador '" + identifierValue + "' ya existe en el sistema");
            return false;
        }

        // Create and save entity
        Entity newEntity = new Entity(tableModel.getRowCount() + 1);
        for (int i = 0; i < attributes.size(); i++) {
            newEntity.setAttribute(
                attributes.get(i).getName(),
                inputFields.get(i).getText().trim()
            );
        }

        tableModel.addEntity(newEntity);
        Storage.saveEntities(tableModel.getEntities());
        showSuccess("Entidad creada exitosamente");
        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Error de Validación",
            JOptionPane.ERROR_MESSAGE
        );
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(
            this,
            message,
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void deleteSelectedEntity() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            Entity entity = tableModel.getEntityAt(selectedRow);
            String idValue = String.valueOf(entity.getAttribute(selectedIdentifierField));
            
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar la entidad con identificador '" + idValue + "'?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeEntity(selectedRow);
                Storage.saveEntities(tableModel.getEntities());
                JOptionPane.showMessageDialog(
                    this,
                    "Entidad eliminada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void editSelectedEntity() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            Entity entity = tableModel.getEntityAt(selectedRow);
            showEditDialog(entity, selectedRow);
        }
    }

    private void showEditDialog(Entity entity, int rowIndex) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel inputPanel = new JPanel(new GridLayout(attributes.size(), 2, 5, 5));
        java.util.List<JTextField> inputFields = new ArrayList<>();
        
        String originalIdentifier = String.valueOf(entity.getAttribute(selectedIdentifierField));
        
        for (Attribute attr : attributes) {
            String labelText = attr.getName();
            if (attr.getName().equals(selectedIdentifierField)) {
                labelText += " (Identificador Único)";
            }
            
            JLabel label = new JLabel(labelText + ":");
            label.setFont(label.getFont().deriveFont(Font.BOLD));
            inputPanel.add(label);
            
            JTextField field = new JTextField(
                String.valueOf(entity.getAttribute(attr.getName())),
                20
            );
            inputFields.add(field);
            inputPanel.add(field);
        }

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        
        JLabel helpText = new JLabel("<html>Modifique los campos necesarios. Recuerde que el Identificador Único debe ser único en el sistema.</html>");
        helpText.setForeground(new Color(70, 130, 180));
        helpText.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(helpText, BorderLayout.NORTH);

        int result = JOptionPane.showConfirmDialog(
            this,
            mainPanel,
            "Editar Entidad",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            // Validate all fields first
            for (int i = 0; i < attributes.size(); i++) {
                Attribute attr = attributes.get(i);
                String value = inputFields.get(i).getText().trim();
                
                if (value.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Por favor complete todos los campos",
                        "Error de Validación",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Validate integer fields
                if (attr.getType().equals("Integer")) {
                    try {
                        Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(
                            this,
                            attr.getName() + " debe ser un número entero válido",
                            "Error de Validación",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                }
            }

            // Check identifier uniqueness if changed
            String newIdentifier = inputFields.get(attributes.indexOf(
                attributes.stream()
                    .filter(a -> a.getName().equals(selectedIdentifierField))
                    .findFirst()
                    .get()
            )).getText().trim();

            if (!newIdentifier.equals(originalIdentifier) && 
                !tableModel.isUniqueIdentifier(selectedIdentifierField, newIdentifier)) {
                JOptionPane.showMessageDialog(
                    this,
                    "El valor del identificador '" + newIdentifier + "' ya existe en el sistema",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Update all fields
            for (int i = 0; i < attributes.size(); i++) {
                entity.setAttribute(
                    attributes.get(i).getName(),
                    inputFields.get(i).getText().trim()
                );
            }

            // Update table and save
            tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
            Storage.saveEntities(tableModel.getEntities());
            
            JOptionPane.showMessageDialog(
                this,
                "Entidad actualizada exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // Called when closing the window
    private void saveData() {
        if (tableModel != null) {
            Storage.saveEntities(tableModel.getEntities());
        }
    }
}
