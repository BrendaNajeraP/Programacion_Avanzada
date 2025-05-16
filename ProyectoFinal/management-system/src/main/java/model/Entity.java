package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Map<String, Object> attributes;

    @JsonCreator
    public Entity(@JsonProperty("id") int id) {
        this.id = id;
        this.attributes = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @JsonProperty("attributes")
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
