import java.util.UUID;

class Processor {

    public static void StringBuilder(UML inputUML) {

        StringBuilder string = new StringBuilder();
        string.append("<?xml version='1.0' encoding='UTF-8'?>\n");
        string.append("<xmi:XMI xmlns:uml='http://www.omg.org/spec/UML/20131001'\n");
        string.append("xmlns:StandardProfile='http://www.omg.org/spec/UML/20131001/StandardProfile'\n");
        string.append("xmlns:xmi='http://www.omg.org/spec/XMI/20131001'>\n");
        string.append("<uml:Model xmi:type='uml:Model' xmi:id='eee_1045467100313_135436_1' name='Model'>\n");

        for (Package p : inputUML.getPackageList()) {
            System.out.println(p.getName());
            string.append(processPackage(p));
            string.append("</packagedElement>\n");
        }

        for (Class c : inputUML.getClassList()) {
            string.append(processClass(c));
        }

        string.append("</uml:Model>\n");
        string.append("</xmi:XMI>\n");

        FileWriter.writeFile(string, "file.xmi");



    }

    private static StringBuilder processPackage(Package p) {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Package' xmi:id='").append(p.getId()).append("' name='").append(p.getName()).append("'>\n");

        for (Class c : p.getClassList()
        ) {
            string.append(processClass(c));

            for (Attribute a : c.getAttributeList()
            ) {
                    string.append(processAttribute(a));
            }

            for (Method m : c.getMethodList()
            ) {
//                    string.append(processMethod(m));
            }
        }

        if(!p.getSubPackageList().isEmpty()){
            for (Package element: p.getSubPackageList()
                 ) {
                string.append("<packagedElement xmi:type='uml:Package' xmi:id='").append(p.getId()).append("' name='").append(element.getName()).append("'>\n");
            }
        }

        return string;
    }

    private static StringBuilder processClass(Class c) {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Class' xmi:id='").append(c.getId()).append("' name='").append(c.getName()).append("'>\n");

        string.append("</packagedElement>\n");
        return string;

    }

    private static StringBuilder processAttribute(Attribute a) {
        StringBuilder string = new StringBuilder();
        string.append("<ownedAttribute xmi:type='uml:Property' xmi:id='" + a.getId() + "' name='" + a.getName() + "' visibility='" + a.getVisibility().toString().toLowerCase() + "'>\n");


        string.append("<type href='http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi#" + a.getType().toString().toLowerCase() + "'/>\n");

        if (a.getDefaultValue() != null) {
            string.append("<defaultValue xmi:type='uml:Literal" + a.getType().toString().toLowerCase() + "' xmi:id='TODOFALSDJFLASJFASLKJD' value='" + a.getDefaultValue().toString() + "'/>\n");
        }

        string.append("</ownedAttribute>\n");
        return string;

    }

//    private static StringBuilder processMethod(Method m) {
//
//    }


    static String uuidGenerator() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
