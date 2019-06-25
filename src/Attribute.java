public class Attribute {

    private Object defaultValue;
    private String name;
    private TypeEnum type;

    public Attribute (String name, TypeEnum type) {
        this.name = name;
        this.defaultValue = null;
        this.type = type;
    }

    public Attribute (String name, Object defaultValue, TypeEnum type) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }
}
