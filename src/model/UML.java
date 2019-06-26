package model;

import java.util.ArrayList;
import java.util.List;

public class UML {

    private List<Class> classList;
    private List<Package> packageList;
    private String name;

    public UML(String name) {
        this.name = name;
        classList = new ArrayList<>();
        packageList = new ArrayList<>();
    }

    public void addClass(Class inputClass) {
        classList.add(inputClass);
    }

    public void addPackage(Package inputPackage) {
        packageList.add(inputPackage);
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
}
