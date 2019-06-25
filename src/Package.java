import java.util.ArrayList;
import java.util.List;

public class Package {

    private List<Class> classList;
    private List<Package> subPackageList;
    private String name;

    public Package(String name) {
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
}
