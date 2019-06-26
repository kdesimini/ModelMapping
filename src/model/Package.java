package model;

import exporter.Processor;

import java.util.ArrayList;
import java.util.List;

public class Package {

    private List<Class> classList;
    private List<Package> subPackageList;
    private String name;
    private String id;

    public Package(String name) {
        id = Processor.uuidGenerator();
        this.name = name;
        classList = new ArrayList<>();
        subPackageList = new ArrayList<>();
    }

    public void addClass(Class inputClass) {
        classList.add(inputClass);
    }

    public void addSubPackage(Package inputPackage) {
        subPackageList.add(inputPackage);

    }
    
    public List<Class> getClassList() {
        return classList;
    }

    public List<Package> getSubPackageList() {
        return subPackageList;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
