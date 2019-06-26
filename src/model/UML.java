package model;

import exporter.Processor;

import java.util.ArrayList;
import java.util.List;

public class UML {

    private String id;
    private List<Class> classList;
    private List<Package> packageList;
    private List<Association> associations;
    private String name;

    public UML(String name) {
        id = Processor.uuidGenerator();
        this.name = name;
        classList = new ArrayList<>();
        packageList = new ArrayList<>();
        associations = new ArrayList<>();
    }

    public void addClass(Class inputClass) {
        classList.add(inputClass);
    }

    public void addAssociation(Association association) {
        associations.add(association);
    }

    public void addPackage(Package inputPackage) {
        packageList.add(inputPackage);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public List<Package> getPackageList() {
        return packageList;
    }

    public List<Association> getAssociations() {
        return associations;
    }
}
