package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class Attribute {
    private String name;
    private ArrayList<TrapezoidMembership> trapezoidMemberships;
    private ArrayList<Value> values;
    private ArrayList<String> possibleLabels;

    public Attribute(String name, ArrayList<Value> values, ArrayList<TrapezoidMembership> trapezoidMemberships)
    {
        this.trapezoidMemberships = trapezoidMemberships;
        possibleLabels = new ArrayList<>();
        for(TrapezoidMembership m: trapezoidMemberships) possibleLabels.add(m.getName());
        this.name = name;
        this.values = values;
        setLabels();
    }

    public void setLabels()
    {
        //on the bottom we set labels
        for(Value v:values)
        {
            float maxDegree = 0;
            String maxLabel = null;
            for(TrapezoidMembership t:trapezoidMemberships)
            {
                String label = t.getName();
                float degree = t.getDegree(v.getValue());
                if(degree > maxDegree)
                {
                    maxDegree = degree;
                    maxLabel = label;
                }
            }
            v.setLabel(maxLabel);
        }
    }

    public ArrayList<Value> getValues()
    {
        return values;
    }
    public ArrayList<String> getPossibleLabels() {return possibleLabels;}
    public String getName(){return name;}
}
