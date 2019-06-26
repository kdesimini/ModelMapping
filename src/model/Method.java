package model;

import model.enums.TypeEnum;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private List<Parameter> parameterList;
    private TypeEnum returnType;
    private String name;

    public Method (String name) {
        parameterList = new ArrayList<>();
        this.name = name;
    }

    public Method setReturnType (TypeEnum type) {
        returnType = type;
        return this;
    }

    public Method addParameter (Parameter parameter) {
        parameterList.add(parameter);
        return this;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public TypeEnum getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }
}
