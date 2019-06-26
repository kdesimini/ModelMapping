package exporter;

import model.*;
import model.Class;
import model.Package;

import java.util.UUID;

public class Processor {

    public static String processUML(UML inputUML) throws Exception {

        StringBuilder string = new StringBuilder();
        string.append("<?xml version='1.0' encoding='UTF-8'?>");
        string.append("<xmi:XMI xmlns:uml='http://www.omg.org/spec/model.UML/20131001'");
        string.append("xmlns:StandardProfile='http://www.omg.org/spec/model.UML/20131001/StandardProfile'");
        string.append("xmlns:xmi='http://www.omg.org/spec/XMI/20131001'>");
        string.append("<uml:Model xmi:type='uml:Model' xmi:id='" + inputUML.getId() + "' name='" + inputUML.getName() + "'>");

        for (Package p : inputUML.getPackageList()) {
            string.append(processPackage(p));
        }

        for (Class c : inputUML.getClassList()) {
            string.append(processClass(c));
        }

        for (Association a : inputUML.getAssociations()) {
            string.append(processAssociation(a));
        }

        string.append("</uml:Model>");
        string.append("</xmi:XMI>");

        return string.toString().replace("'","\"");
    }

    private static String processAssociation(Association a) {
        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Association' xmi:id='"+a.getId()+"'>");
        string.append("<memberEnd xmi:idref='"+a.getSource().getId()+"'/>");
        string.append("<memberEnd xmi:idref='"+a.getTarget().getId()+"'/>");
        string.append("</packagedElement>");

        return string.toString();
    }

    private static String processPackage(Package p) throws Exception {
        // TODO
        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:model.Package' xmi:id='").append(p.getId()).append("' name='").append(p.getName()).append("'>");

        for (Class c : p.getClassList()
                ) {
            string.append(processClass(c));

            for (Property a : c.getPropertyList()
                    ) {
                string.append(processProperty(a));
            }

            for (Method m : c.getMethodList()
                    ) {
//                    string.append(processMethod(m));
            }
        }

        if (!p.getSubPackageList().isEmpty()) {
            for (Package sub : p.getSubPackageList()
                    ) {
                string.append(processPackage(sub));
            }
        }

        return string.toString();
    }

    private static String processClass(Class c) throws Exception {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Class' xmi:id='"+c.getId()+"' name='"+c.getName()+"'>");

        for (Property p :
                c.getPropertyList()) {
            string.append(processProperty(p));
        }

        // TODO other class elements

        string.append("</packagedElement>");

        return string.toString();
    }

    private static String processProperty(Property p) throws Exception {
        StringBuilder string = new StringBuilder();

        if(p instanceof PrimitiveProperty) {
            PrimitiveProperty pp = (PrimitiveProperty)p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='"+p.getId()+"' name='"+p.getName()+"' visibility='"+p.getVisibility().toString().toLowerCase()+"'>");
            string.append("<type href='http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#"+pp.getType().toString()+"'/>");
            if(pp.getDefaultValue() != null) {
                string.append("<defaultValue xmi:type='uml:Literal"+pp.getType().toString()+"' xmi:id='"+Processor.uuidGenerator()+"' value='"+pp.getDefaultValue().toString()+"'/>");
            }
            string.append("</ownedAttribute>");
        } else if (p instanceof AssociationProperty) {
            AssociationProperty ap = (AssociationProperty)p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='"+p.getId()+"' name='"+p.getName()+"' visibility='"+p.getVisibility().toString().toLowerCase()+"' type='"+ap.getAssociation().getTarget().getId()+"' association='"+ap.getAssociation().getId()+"'/>");
        } else if (p instanceof ClassProperty) {
            ClassProperty cp = (ClassProperty)p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='"+p.getId()+"' name='"+p.getName()+"' visibility='"+p.getVisibility().toString().toLowerCase()+"' type='"+cp.getType().getId()+"'/>");
        } else {
            throw new Exception("A new type of property that you need to handle in processProperty!");
        }

        return string.toString();
    }

    public static int ID_COUNTER = 0;

    public static String uuidGenerator() {
        //UUID uuid = UUID.randomUUID();
        //return uuid.toString();
        return "ID"+ID_COUNTER++;
    }
}
