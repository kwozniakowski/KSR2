package Attributes;

public class FuzzyValue {
    private Value value;
    private float degree;

    public FuzzyValue(Value value, float degree) {
        this.value = value;
        this.degree = degree;
    }

    public Value getValue() {
        return value;
    }

    public float getDegree() {
        return degree;
    }
}
