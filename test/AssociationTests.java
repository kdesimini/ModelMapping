import exporter.Processor;
import model.*;
import model.Class;
import model.enums.TypeEnum;
import model.enums.VisibilityEnum;
import model.properties.AssociationProperty;
import model.properties.ClassProperty;
import model.properties.PrimitiveProperty;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AssociationTests {

    @Test
    public void TestClassPropertyAssociation() {

        UML uml = new UML("c1c2assoc");

        Class c1 = new Class("C1");
        uml.addClass(c1);
        Class c2 = new Class("C2");
        uml.addClass(c2);

        PrimitiveProperty pp = new PrimitiveProperty("somePrimitiveProperty", VisibilityEnum.Public, TypeEnum.Integer,5);
        c1.addProperty(pp);

        ClassProperty cp = new ClassProperty("someClassProperty",VisibilityEnum.Public,c2);
        c1.addProperty(cp);

        Association assocBetweenC1andC2 = new Association("someAssociation",c1,c2);
        uml.addAssociation(assocBetweenC1andC2);
        AssociationProperty ap = new AssociationProperty("someAssociation",VisibilityEnum.Public,assocBetweenC1andC2);
        c1.addProperty(ap);

        AssociationProperty ap2 = new AssociationProperty("someAssociation",VisibilityEnum.Public,assocBetweenC1andC2);
        c2.addProperty(ap2);

        try {
            String result = Processor.processUML(uml);
            String expected= "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xmi:XMI xmlns:uml=\"http://www.omg.org/spec/UML/20131001\"xmlns:StandardProfile=\"http://www.omg.org/spec/UML/20131001/StandardProfile\"xmlns:xmi=\"http://www.omg.org/spec/XMI/20131001\"><uml:Model xmi:type=\"uml:Model\" xmi:id=\"ID0\" name=\"c1c2assoc\"><packagedElement xmi:type=\"uml:Class\" xmi:id=\"ID1\" name=\"C1\"><ownedAttribute xmi:type=\"uml:Property\" xmi:id=\"ID3\" name=\"somePrimitiveProperty\" visibility=\"public\"><type href=\"http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#Integer\"/><defaultValue xmi:type=\"uml:LiteralInteger\" xmi:id=\"ID8\" value=\"5\"/></ownedAttribute><ownedAttribute xmi:type=\"uml:Property\" xmi:id=\"ID4\" name=\"someClassProperty\" visibility=\"public\" type=\"ID2\"/><ownedAttribute xmi:type=\"uml:Property\" xmi:id=\"ID6\" name=\"someAssociation\" visibility=\"public\" type=\"ID2\" association=\"ID5\"/></packagedElement><packagedElement xmi:type=\"uml:Class\" xmi:id=\"ID2\" name=\"C2\"><ownedAttribute xmi:type=\"uml:Property\" xmi:id=\"ID7\" name=\"someAssociation\" visibility=\"public\" type=\"ID2\" association=\"ID5\"/></packagedElement><packagedElement xmi:type=\"uml:Association\" xmi:id=\"ID5\"><memberEnd xmi:idref=\"ID1\"/><memberEnd xmi:idref=\"ID2\"/></packagedElement></uml:Model></xmi:XMI>";
            Assertions.assertEquals(expected,result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
