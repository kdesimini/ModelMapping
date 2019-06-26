import java.util.Random;

class Processor {

    StringBuilder processPackage(Package p) {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Package' xmi:id='" + generateRandomString() +"' name='" + p.getName() + "'>\n");

        for (Class c : p.getClassList()
        ) {
            string.append(processClass(c));

            for (Attribute a : c.getAttributeList()
            ) {
//                    string.append(processor.processAttribute(a));
            }

            for (Method m : c.getMethodList()
            ) {
//                    string.append(processor.processMethod(m));
            }
        }

        if(!p.getSubPackageList().isEmpty()){
            for (Package element: p.getSubPackageList()
                 ) {
                string.append("<packagedElement xmi:type='uml:Package' xmi:id='" + generateRandomString() +"' name='" + element.getName() + "'>\n");
            }
        }

        return string;
    }

    StringBuilder processClass(Class c) {

        StringBuilder string = new StringBuilder();

        string.append("<packagedElement xmi:type='uml:Class' xmi:id='" + generateRandomString() + "' name='" + c.getName() + "'>\n");

        return string;
    }
//
//    public StringBuilder processMethod (Method m, UML input) {
//
//    }

    private String generateRandomString() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        return generatedString;
    }
}
