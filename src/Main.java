
public class Main {

    public static void main(String[] args) {

        UML uml1 = new UML("uml1");
        Package someClassPackage = new Package("someClassPackage");
//        Package package2 = new Package("package2");

//        Package subPackage = new Package("subPackage");

        uml1.addPackage(someClassPackage);
//        uml1.addPackage(package2);
//        package2.addSubPackage(subPackage);

        Class SomeClass = new Class("SomeClass");
        Class SomeClass2 = new Class("SomeClass2");
        Class SomeClass3 = new Class("SomeClass3");

        someClassPackage.addClass(SomeClass2);
        someClassPackage.addClass(SomeClass3);

        SomeClass.addAttribute(new Attribute("someAttribute", TypeEnum.Integer));
        SomeClass2.addAttribute(new Attribute("someAttribute2", TypeEnum.Integer));
        SomeClass3.addAttribute(new Attribute("someAttribute3", TypeEnum.Integer));

        SomeClass.addMethod((new Method("someFunction").addParameter(new Parameter("someParameter", DirectionEnum.InOut, TypeEnum.Boolean))));

        SomeClass.addMethod(new Method("someProcedure").setReturnType(TypeEnum.Boolean));

        uml1.addClass(SomeClass);

        StringBuilder(uml1);

    }

    private static void StringBuilder(UML inputUML) {

        StringBuilder string = new StringBuilder();
        string.append("<?xml version='1.0' encoding='UTF-8'?>\n");
        string.append("<xmi:XMI xmlns:uml='http://www.omg.org/spec/UML/20131001'\n");
        string.append("xmlns:StandardProfile='http://www.omg.org/spec/UML/20131001/StandardProfile'\n");
        string.append("xmlns:xmi='http://www.omg.org/spec/XMI/20131001'>\n");
        string.append("<uml:Model xmi:type='uml:Model' xmi:id='eee_1045467100313_135436_1' name='Model'>\n");



        Processor processor = new Processor();

        for (Package p: inputUML.getPackageList()
             ) {
            System.out.println(p.getName());
            string.append(processor.processPackage(p));
            string.append("</packagedElement>\n");
        }

            for (Class c : inputUML.getClassList()
            ) {
                string.append(processor.processClass(c));
//
//                for (Attribute a : c.getAttributeList()
//                ) {
//                    string.append(processor.processAttribute(a));
//                }
//
//                for (Method m : c.getMethodList()
//                ) {
//                    string.append(processor.processMethod(m));
//                }
            }

        string.append("</uml:Model>\n");
        string.append("</xmi:XMI>\n");

        FileWriter.writeFile(string);



    }


}
