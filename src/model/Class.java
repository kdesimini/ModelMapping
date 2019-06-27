package model;

import exporter.Processor;

import java.util.ArrayList;
import java.util.List;

public class Class {

    private List<Property> properties;
    private List<Method> methods;
    private List<Class> superClasses;
    private String name;
    private String id;

    public Class(String name) {
        id = Processor.uuidGenerator();
        this.name = name;
        properties = new ArrayList<>();
        methods = new ArrayList<>();
        superClasses = new ArrayList<>();
    }

    public void addProperty(Property someProperty) {
        properties.add(someProperty);
    }

    public void addMethod(Method someMethod) {
        methods.add(someMethod);
    }

    public void addSuperClass (Class someClass) {
        superClasses.add(someClass);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public List<Class> getSuperClasses() {
        return superClasses;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
