import exporter.Processor;
import model.UML;
import model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProcessorTest {

    @Test
    void processPackage() {
        UML uml = new UML("uml1");

        Package package1 = new Package("package1");
        Package package2 = new Package("package2");
        Package subPackage = new Package("subPackage");

        package1.addSubPackage(subPackage);
        uml.addPackage(package1);
        uml.addPackage(package2);

        try {
            String result = Processor.processUML(uml);
            String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmi:XMI xmlns:uml=\"http://www.omg.org/spec/model.UML/20131001\"xmlns:StandardProfile=\"http://www.omg.org/spec/model.UML/20131001/StandardProfile\"xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\"><uml:Model xmi:type=\"uml:Model\" xmi:id=\"ID0\" name=\"uml1\"><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID1\" name=\"package1\"><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID3\" name=\"subPackage\"></packagedElement></packagedElement><packagedElement xmi:type=\"uml:Package\" xmi:id=\"ID2\" name=\"package2\"></packagedElement></uml:Model></xmi:XMI>";
            Assertions.assertEquals(expected,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void processClass() {
    }
}