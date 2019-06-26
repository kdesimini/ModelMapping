import java.util.ArrayList;
import java.util.List;

public class UML {

    private List<Class> classList;
    private List<Package> packageList;
    private String name;

    UML(String name) {
        this.name = name;
        classList = new ArrayList<>();
        packageList = new ArrayList<>();
    }

    void addClass(Class inputClass) {
        classList.add(inputClass);
    }

    void addPackage(Package inputPackage) {
        packageList.add(inputPackage);
    }

    public boolean hasClasses() {
        return !classList.isEmpty();
    }

    public boolean hasPackages() {
        return !packageList.isEmpty();
    }

    public String getName() {
        return this.name;
    }

    List<Class> getClassList() {
        return classList;
    }

    List<Package> getPackageList() {
        return packageList;
    }
}
