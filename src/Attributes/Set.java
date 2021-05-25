package Attributes;

import java.util.ArrayList;

public class Set {
    private ArrayList<Value> values;
    public Set(){
        this.values = new ArrayList<>();
    }
    public ArrayList<Value> getValues()
    {
        return this.values;
    }
}
