import java.util.ArrayList;
import java.util.List;

public class Class {

    private List<Attribute> attributeList;
    private List<Method> methodList;
    private List<Class> superClassesList;
    private String name;

    public Class(String name) {
        this.name = name;
        attributeList = new ArrayList<>();
        methodList = new ArrayList<>();
        superClassesList = new ArrayList<>();
    }

    public void addAttribute (Attribute someAttribute) {
        attributeList.add(someAttribute);
    }

    public void addMethod (Method someMethod) {
        methodList.add(someMethod);
    }

    public void addSuperClass (Class someClass) {
        superClassesList.add(someClass);
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
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
}
