import java.util.ArrayList;
import java.util.List;

public class Method {
    private List<Parameter> parameterList;
    private TypeEnum returnType;
    private String name;

    public Method () {
        parameterList = new ArrayList<>();
    }

    public void setReturnType (TypeEnum type) {
        returnType = type;
    }

}
