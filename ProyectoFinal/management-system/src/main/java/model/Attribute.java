package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Attribute implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String type; // "String" or "Integer"

    @JsonCreator
    public Attribute(
        @JsonProperty("name") String name,
        @JsonProperty("type") String type
    ) {
        this.name = name;
        this.type = type;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
