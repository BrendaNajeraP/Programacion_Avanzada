package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import com.management.util.Storage;

public class EntityTableModel extends AbstractTableModel {
    private List<Attribute> attributes;
    private List<Entity> entities;
      public EntityTableModel(List<Attribute> attributes) {
        this.attributes = attributes;
        this.entities = Storage.loadEntities();
        if (this.entities == null) {
            this.entities = new ArrayList<>();
        }
        
        // Sort columns so the identifier appears first
        if (!attributes.isEmpty()) {
            attributes.sort((a1, a2) -> {
                if (a1.getType().equals("String") && !a2.getType().equals("String")) {
                    return -1;
                }
                if (!a1.getType().equals("String") && a2.getType().equals("String")) {
                    return 1;
                }
                return a1.getName().compareTo(a2.getName());
            });
        }
    }

    @Override
    public int getRowCount() {
        return entities.size();
    }

    @Override
    public int getColumnCount() {
        return attributes.size();
    }

    @Override
    public String getColumnName(int column) {
        return attributes.get(column).getName();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entity entity = entities.get(rowIndex);
        String attributeName = attributes.get(columnIndex).getName();
        return entity.getAttribute(attributeName);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        fireTableRowsInserted(entities.size() - 1, entities.size() - 1);
    }

    public void removeEntity(int rowIndex) {
        entities.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getEntityAt(int rowIndex) {
        return entities.get(rowIndex);
    }

    public boolean isUniqueIdentifier(String attributeName, String value) {
        return entities.stream()
            .noneMatch(entity -> value.equals(String.valueOf(entity.getAttribute(attributeName))));
    }
}
