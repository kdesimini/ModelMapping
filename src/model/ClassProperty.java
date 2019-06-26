package model;

import model.enums.VisibilityEnum;

public class ClassProperty extends Property {

    Class type;

    public ClassProperty(String name, VisibilityEnum visibility, Class type) {
        super(name, visibility);
        this.type = type;
    }

    public Class getType() {
        return type;
    }

}
