package model;

import exporter.Processor;
import model.enums.TypeEnum;
import model.enums.VisibilityEnum;

public class Property {

    private Object defaultValue;
    private String name;
    private TypeEnum type;
    private VisibilityEnum visibility;
    private String id;

    public Property(String name, TypeEnum type) {
        id = Processor.uuidGenerator();
        this.name = name;
        this.defaultValue = null;
        this.type = type;
        this.visibility = VisibilityEnum.Public;
    }

    public Property(String name, TypeEnum type, VisibilityEnum visibility) {
        id = Processor.uuidGenerator();
        this.name = name;
        this.defaultValue = null;
        this.type = type;
        this.visibility = visibility;
    }

    public Property(String name, Object defaultValue, TypeEnum type) {
        id = Processor.uuidGenerator();
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
        this.visibility = VisibilityEnum.Public;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    public TypeEnum getType() {
        return type;
    }

    public VisibilityEnum getVisibility() {
        return visibility;
    }

    public String getId() {
        return id;
    }
}
