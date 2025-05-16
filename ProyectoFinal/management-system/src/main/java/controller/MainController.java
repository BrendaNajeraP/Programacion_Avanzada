package controller;

import view.AttributeDefinitionView;
import view.EntityManagementView;
import model.Attribute;
import model.Book;
import java.util.List;
import java.util.ArrayList;
import com.management.util.Storage;
import javax.swing.SwingUtilities;

public class MainController {
    private AttributeDefinitionView attributeView;
    private EntityManagementView managementView;
    private List<Attribute> entityAttributes;

    public MainController() {
        Storage.initialize();
        this.entityAttributes = Storage.loadAttributes();
        
        // Always show attribute definition view first if no attributes are saved
        if (this.entityAttributes == null || this.entityAttributes.isEmpty()) {
            this.attributeView = new AttributeDefinitionView();
            setupAttributeDefinitionListeners();
        } else {
            showEntityManagementView();
        }
    }

    private void setupAttributeDefinitionListeners() {
        attributeView.addSubmitListener(e -> {
            if (attributeView.validateFields()) {
                entityAttributes = attributeView.getDefinedAttributes();
                
                // Print the defined attributes for confirmation
                System.out.println("Atributos definidos:");
                for (Attribute attr : entityAttributes) {
                    System.out.println(attr.getName() + " - " + attr.getType());
                }
                
                // Save attributes before closing the window
                Storage.saveAttributes(entityAttributes);
                
                // Schedule the management view to open after this window closes
                SwingUtilities.invokeLater(() -> {
                    attributeView.dispose();
                    showEntityManagementView();
                });
            }
        });
    }

    private void showEntityManagementView() {
        try {
            // Create and show the management view immediately
            managementView = new EntityManagementView(entityAttributes);
            managementView.addCreateButtonListener(e -> managementView.showCreateEntityDialog());
            
            // Ensure the window appears in the right place and size
            SwingUtilities.invokeLater(() -> {
                managementView.pack();
                managementView.setLocationRelativeTo(null);
                managementView.setVisible(true);
                
                // Force a repaint
                managementView.revalidate();
                managementView.repaint();
                
                System.out.println("Management view should now be visible");
            });
        } catch (Exception ex) {
            System.err.println("Error showing management view: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void showMainView() {
        if (attributeView != null) {
            SwingUtilities.invokeLater(() -> {
                attributeView.setLocationRelativeTo(null); // Center on screen
                attributeView.setVisible(true);
            });
        } else if (entityAttributes != null && !entityAttributes.isEmpty()) {
            showEntityManagementView();
        } else {
            System.err.println("No attributes defined and no attribute view available");
        }
    }
}