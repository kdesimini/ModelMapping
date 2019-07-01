package exporter;

import model.*;
import model.Class;
import model.Package;
import model.parameters.ClassParameter;
import model.parameters.PrimitiveParameter;
import model.properties.AssociationProperty;
import model.properties.ClassProperty;
import model.properties.PrimitiveProperty;

public class Processor {

    public static String processUML(UML inputUML) throws Exception {

        StringBuilder string = new StringBuilder();
        string.append("<?xml version='1.0' encoding='UTF-8'?>");
        string.append("<xmi:XMI xmlns:uml='http://www.omg.org/spec/UML/20131001'");
        string.append(" xmlns:StandardProfile='http://www.omg.org/spec/UML/20131001/StandardProfile'");
        string.append(" xmlns:xmi='http://www.omg.org/spec/XMI/20131001'>");
        string.append("<uml:Model xmi:type='uml:Model' xmi:id='" + inputUML.getId() + "' name='" + inputUML.getName() + "'>");

        for (Package p : inputUML.getPackages()) {
            string.append(processPackage(p));
        }
        for (Class c : inputUML.getClasses()) {
            string.append(processClass(c));
        }
        for (Association a : inputUML.getAssociations()) {
            string.append(processAssociation(a));
        }

        string.append("</uml:Model>");
        string.append("</xmi:XMI>");

        return string.toString().replace("'", "\"");
    }

    private static String processAssociation(Association a) {
        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Association' xmi:id='" + a.getId() + "'>");
        string.append("<memberEnd xmi:idref='" + a.getSource().getId() + "'/>");
        string.append("<memberEnd xmi:idref='" + a.getTarget().getId() + "'/>");
        string.append("</packagedElement>");

        return string.toString();
    }

    private static String processPackage(Package p) throws Exception {
        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Package' xmi:id='").append(p.getId()).append("' name='").append(p.getName()).append("'>");

        for (Class c : p.getClasses()) {
            string.append(processClass(c));
        }
        for (Package sub : p.getSubPackages()) {
            string.append(processPackage(sub));
        }
        string.append("</packagedElement>");
        return string.toString();
    }

    private static String processClass(Class c) throws Exception {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Class' xmi:id='" + c.getId() + "' name='" + c.getName() + "'>");

        for (Class superClass : c.getSuperClasses()) {
            string.append(processesSuperClass(superClass));
        }
        processClassContents(c, string);

        string.append("</packagedElement>");

        return string.toString();
    }


    private static String processesSuperClass(Class superClass) {
        StringBuilder string = new StringBuilder();
        string.append("<generalization xmi:type='uml:Generalization' xmi:id='" + Processor.uuidGenerator() + "' general='" + superClass.getId() + "'/>");
        return string.toString();
    }

    private static String processesNestedClass(Class c) throws Exception {
        StringBuilder string = new StringBuilder();

        string.append("<nestedClassifier xmi:type='uml:Class' xmi:id='" + c.getId() + "' name='" + c.getName() + "'>");

        processClassContents(c, string);

        string.append("</nestedClassifier>");

        return string.toString();
    }

    private static void processClassContents(Class c, StringBuilder string) throws Exception {
        for (Property p : c.getProperties()) {
            string.append(processProperty(p));
        }
        for (Operation o : c.getOperations()) {
            string.append(processOperation(o));
        }
        for (Class nestedClass : c.getNestedClasses()) {
            string.append(processesNestedClass(nestedClass));
        }
    }

    private static String processOperation(Operation o) throws Exception {
        StringBuilder string = new StringBuilder();

        string.append("<ownedOperation xmi:type='uml:Operation' xmi:id='" + o.getId() + "' name='" + o.getName() + "' visibility='" + o.getVisibility().toString().toLowerCase() + "'>");

        for (Parameter p : o.getParameters()) {
            string.append(processParameter(p));
        }

        string.append("</ownedOperation>");

        return string.toString();
    }

    private static String processParameter(Parameter param) throws Exception {
        StringBuilder string = new StringBuilder();

        if (param instanceof PrimitiveParameter) {
            PrimitiveParameter pParam = (PrimitiveParameter) param;
            string.append("<ownedParameter xmi:type='uml:Parameter' xmi:id='" + param.getId() + "' name='" + param.getName() + "' visibility='public' direction='" + param.getDirection().toString().toLowerCase() + "'>"); // Todo Check if visibility matters here
            string.append("<type href='http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#" + pParam.getType().toString() + "'/>");
            if (pParam.getDefaultValue() != null) {
                string.append("<defaultValue xmi:type='uml:Literal" + pParam.getType().toString() + "' xmi:id='" + Processor.uuidGenerator() + "' value='" + pParam.getDefaultValue().toString() + "'/>");
            }
            string.append("</ownedParameter>");
        } else if (param instanceof ClassParameter) {
            ClassParameter cParam = (ClassParameter) param;
            string.append("<ownedParameter xmi:type='uml:Parameter' xmi:id='" + param.getId() + "' name='" + param.getName() + "' visibility='public' type='" + cParam.getType().getId() + "' direction='" + param.getDirection().toString().toLowerCase() + "'/>");
        } else {
            throw new Exception("A new type of parameter that you need to handle in processParameter!");

        }
        return string.toString();
    }

    private static String processProperty(Property p) throws Exception {
        StringBuilder string = new StringBuilder();

        if (p instanceof PrimitiveProperty) {
            PrimitiveProperty pp = (PrimitiveProperty) p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='" + p.getId() + "' name='" + p.getName() + "' visibility='" + p.getVisibility().toString().toLowerCase() + "'>");
            string.append("<type href='http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#" + pp.getType().toString() + "'/>");
            if (pp.getDefaultValue() != null) {
                string.append("<defaultValue xmi:type='uml:Literal" + pp.getType().toString() + "' xmi:id='" + Processor.uuidGenerator() + "' value='" + pp.getDefaultValue().toString() + "'/>");
            }
            string.append("</ownedAttribute>");
        } else if (p instanceof AssociationProperty) {
            AssociationProperty ap = (AssociationProperty) p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='" + p.getId() + "' name='" + p.getName() + "' visibility='" + p.getVisibility().toString().toLowerCase() + "' type='" + ap.getAssociation().getTarget().getId() + "' association='" + ap.getAssociation().getId() + "'/>");
        } else if (p instanceof ClassProperty) {
            ClassProperty cp = (ClassProperty) p;
            string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='" + p.getId() + "' name='" + p.getName() + "' visibility='" + p.getVisibility().toString().toLowerCase() + "' type='" + cp.getType().getId() + "'/>");
        } else {
            throw new Exception("A new type of property that you need to handle in processProperty!");
        }

        return string.toString();
    }

    public static int ID_COUNTER = 0;

    public static String uuidGenerator() {
        //UUID uuid = UUID.randomUUID();
        //return uuid.toString();
        return "ID" + ID_COUNTER++;
    }
}
