package Attributes;

public class Value {
    private float value;
    private String label;

    public Value(float value) {
        this.value = value;
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
}
