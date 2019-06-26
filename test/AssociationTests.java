import model.*;
import model.Class;
import model.enums.TypeEnum;
import model.enums.VisibilityEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        AssociationProperty ap = new AssociationProperty("someAssociation",VisibilityEnum.Public,assocBetweenC1andC2);
        c1.addProperty(ap);

        AssociationProperty ap2 = new AssociationProperty("someAssociation",VisibilityEnum.Public,assocBetweenC1andC2);
        c2.addProperty(ap2);


    }

}
