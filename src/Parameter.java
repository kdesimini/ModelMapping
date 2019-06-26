public class Parameter {

    private String name;
    private DirectionEnum direction;
    private TypeEnum type;
    private Object defaultValue;

    Parameter(String name, DirectionEnum direction, TypeEnum type) {
        this.name = name;
        this.direction = direction;
        this.type = type;
        this.defaultValue = null;
    }

    public Parameter (String name, DirectionEnum direction, TypeEnum type, Object defaultValue) {
        this.name = name;
        this.direction = direction;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public TypeEnum getType() {
        return type;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}
