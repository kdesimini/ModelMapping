package model;

import model.enums.VisibilityEnum;

public class AssociationProperty extends Property {

    Association association;

    public AssociationProperty(String name, VisibilityEnum visibility, Association association) {
        super(name, visibility);
        this.association = association;
    }

    public Association getAssociation() {
        return association;
    }
}
