package model;

import exporter.Processor;

import java.util.ArrayList;
import java.util.List;

public class Class {

    private List<Property> propertyList;
    private List<Method> methodList;
    private List<Class> superClassesList;
    private String name;
    private String id;

    public Class(String name) {
        id = Processor.uuidGenerator();
        this.name = name;
        propertyList = new ArrayList<>();
        methodList = new ArrayList<>();
        superClassesList = new ArrayList<>();
    }

    public void addProperty(Property someProperty) {
        propertyList.add(someProperty);
    }

    public void addMethod(Method someMethod) {
        methodList.add(someMethod);
    }

    public void addSuperClass (Class someClass) {
        superClassesList.add(someClass);
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public List<Method> getMethodList() {
        return methodList;
    }

    public List<Class> getSuperClassesList() {
        return superClassesList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
