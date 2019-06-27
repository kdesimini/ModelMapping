package model;

import model.enums.TypeEnum;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private List<Parameter> parameters;
    private TypeEnum returnType;
    private String name;

    public Method (String name) {
        parameters = new ArrayList<>();
        this.name = name;
    }

    public Method setReturnType (TypeEnum type) {
        returnType = type;
        return this;
    }

    public Method addParameter (Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public TypeEnum getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }
}
