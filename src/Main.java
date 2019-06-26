
public class Main {

    public static void main(String[] args) {

        UML uml1 = new UML("uml1");
        Package someClassPackage = new Package("someClassPackage");

        uml1.addPackage(someClassPackage);

        Class SomeClass = new Class("SomeClass");
        Class SomeClass2 = new Class("SomeClass2");
        Class SomeClass3 = new Class("SomeClass3");

        someClassPackage.addClass(SomeClass2);
        someClassPackage.addClass(SomeClass3);

        SomeClass.addAttribute(new Attribute("someAttribute", TypeEnum.Integer));
        SomeClass2.addAttribute(new Attribute("someAttribute2", TypeEnum.Integer));
        SomeClass3.addAttribute(new Attribute("someAttribute3",69 , TypeEnum.Integer));


        SomeClass.addMethod((new Method("someFunction").addParameter(new Parameter("someParameter", DirectionEnum.InOut, TypeEnum.Boolean))));

        SomeClass.addMethod(new Method("someProcedure").setReturnType(TypeEnum.Boolean));

        uml1.addClass(SomeClass);

        Processor.StringBuilder(uml1);

    }




}
