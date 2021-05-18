package Attributes;

import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class Attribute {
    private String name;
    private ArrayList<TrapezoidMembership> trapezoidMemberships;
    private ArrayList<Value> values;

    public Attribute(String name, ArrayList<TrapezoidMembership> trapezoidMemberships)
    {
        this.trapezoidMemberships = trapezoidMemberships;
        this.name = name;
        parseValues();
    }

    private void parseValues()
    {
        //TODO: connection with database and parse values
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
}
