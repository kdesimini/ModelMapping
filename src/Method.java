import java.util.ArrayList;
import java.util.List;

class Method {
    private List<Parameter> parameterList;
    private TypeEnum returnType;
    private String name;

    Method (String name) {
        parameterList = new ArrayList<>();
        this.name = name;
    }

    Method setReturnType (TypeEnum type) {
        returnType = type;
        return this;
    }

    Method addParameter (Parameter parameter) {
        parameterList.add(parameter);
        return this;
    }

}
