public class Main {

    public static void main(String[] args) {

        UML uml1 = new UML("uml1");
        Package package1 = new Package("package1");
        Class class1 = new Class("class1");

        class1.addAttribute(new Attribute("someAttribute", TypeEnum.Integer));

        class1.addMethod((new Method("someMethod").addParameter(new Parameter("someParameter", DirectionEnum.InOut, TypeEnum.Boolean))));
        // Why can't we do this?
        class1.addMethod(new Method("someMethod2").setReturnType(TypeEnum.Boolean));


    }
}
