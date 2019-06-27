import exporter.Processor;
import model.*;
import model.Class;
import model.Package;
import model.enums.DirectionEnum;
import model.enums.TypeEnum;
import model.enums.VisibilityEnum;
import model.parameters.ClassParameter;
import model.parameters.PrimitiveParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcessorTest {

    @BeforeEach
    public void init() {
        Processor.ID_COUNTER = 0;
    }

    @Test
    void processEmptyPackages() {
        UML uml = new UML("uml1");

        Package package1 = new Package("package1");
        Package package2 = new Package("package2");
        Package subPackage = new Package("subPackage");

        package1.addSubPackage(subPackage);
        uml.addPackage(package1);
        uml.addPackage(package2);

        try {
            String result = Processor.processUML(uml);
            String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmi:XMI xmlns:uml=\"http://www.omg.org/spec/UML/20131001\"xmlns:StandardProfile=\"http://www.omg.org/spec/UML/20131001/StandardProfile\"xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\"><uml:Model xmi:type=\"uml:Model\" xmi:id=\"ID0\" name=\"uml1\"><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID1\" name=\"package1\"><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID3\" name=\"subPackage\"></packagedElement></packagedElement><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID2\" name=\"package2\"></packagedElement></uml:Model></xmi:XMI>";
            Assertions.assertEquals(expected,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void processNestedClass() {

        UML uml = new UML("uml");

        Class Class1 = new Class("Class1");
        Class Class2 = new Class("Class2");
        Class NestedClass = new Class("NestedClass");

        Class1.addNestedClass(NestedClass);

        uml.addClass(Class1);
        uml.addClass(Class2);

        try {
            String result = Processor.processUML(uml);
            String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmi:XMI xmlns:uml=\"http://www.omg.org/spec/UML/20131001\"xmlns:StandardProfile=\"http://www.omg.org/spec/UML/20131001/StandardProfile\"xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\"><uml:Model xmi:type=\"uml:Model\" xmi:id=\"ID0\" name=\"uml\"><packagedElement xmi:type=\"uml:Class\" xmi:id=\"ID1\" name=\"Class1\"><nestedClassifier xmi:type=\"uml:Class\" xmi:id=\"ID3\" name=\"NestedClass\"></nestedClassifier></packagedElement><packagedElement xmi:type=\"uml:Class\" xmi:id=\"ID2\" name=\"Class2\"></packagedElement></uml:Model></xmi:XMI>";
            Assertions.assertEquals(expected,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void processParameters() {

        UML uml = new UML("uml");
        Operation operation = new Operation("operation1", VisibilityEnum.Public);
        Class Class1 = new Class("Class1");

        ClassParameter classParameter = new ClassParameter("classParameter", DirectionEnum.In, Class1);
        PrimitiveParameter primitiveParameter = new PrimitiveParameter("primitiveParameter", DirectionEnum.In, TypeEnum.Boolean);
        PrimitiveParameter primitiveParameterWithValue = new PrimitiveParameter("primitiveParameter", DirectionEnum.In, TypeEnum.Integer, 99);

        Class1.addOperation(operation);

        operation.addParameter(classParameter);
        operation.addParameter(primitiveParameter);
        operation.addParameter(primitiveParameterWithValue);

        uml.addClass(Class1);

        try {
            String result = Processor.processUML(uml);
            String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmi:XMI xmlns:uml=\"http://www.omg.org/spec/UML/20131001\"xmlns:StandardProfile=\"http://www.omg.org/spec/UML/20131001/StandardProfile\"xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\"><uml:Model xmi:type=\"uml:Model\" xmi:id=\"ID0\" name=\"uml\"><packagedElement xmi:type=\"uml:Class\" xmi:id=\"ID2\" name=\"Class1\"><ownedOperation xmi:type=\"uml:Operation\" xmi:id=\"ID1\" name=\"operation1\" visibility=\"public\"><ownedParameter xmi:type=\"uml:Parameter\" xmi:id=\"ID3\" name=\"classParameter\" visibility=\"public\" type=\"ID2\"/><ownedParameter xmi:type=\"uml:Parameter\" xmi:id=\"ID4\" name=\"primitiveParameter\" visibility=\"public\"><type href=\"http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#Boolean\"/></ownedParameter><ownedParameter xmi:type=\"uml:Parameter\" xmi:id=\"ID5\" name=\"primitiveParameter\" visibility=\"public\"><type href=\"http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#Integer\"/><defaultValue xmi:type=\"uml:LiteralInteger\" xmi:id=\"ID6\" value=\"99\"/></ownedParameter></ownedOperation></packagedElement></uml:Model></xmi:XMI>";
            Assertions.assertEquals(expected,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}