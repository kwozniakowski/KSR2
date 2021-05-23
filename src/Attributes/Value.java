package Attributes;

public class Value {
    private float value;
    private String label;
    private String id;
    public Value(float value, String id) {
        this.value = value;
        this.id = id;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public float getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
    public String getId() {return id;}
}
