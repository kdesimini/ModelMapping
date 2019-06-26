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

    void addAttribute(Attribute someAttribute) {
        attributeList.add(someAttribute);
    }

    void addMethod(Method someMethod) {
        methodList.add(someMethod);
    }

    public void addSuperClass (Class someClass) {
        superClassesList.add(someClass);
    }

    List<Attribute> getAttributeList() {
        return attributeList;
    }

    List<Method> getMethodList() {
        return methodList;
    }

    public List<Class> getSuperClassesList() {
        return superClassesList;
    }

    String getName() {
        return name;
    }
}
