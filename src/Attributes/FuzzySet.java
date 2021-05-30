package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class FuzzySet {
    private String name;
    private Membership membership;
    private ArrayList<Value> values;
    private ArrayList<FuzzyValue> fuzzyValues;


    public FuzzySet(String name, Membership membership)
    {
        this.name = name;
        this.membership = membership;
        this.values = new ArrayList<>();
        this.fuzzyValues = new ArrayList<>();
    }

    public ArrayList<Value> getValues()
    {
        return values;
    }
    public String getName(){return name;}
    public Membership getMembership()
    {
        return membership;
    }
    public ArrayList<FuzzyValue> getFuzzyValues() {return fuzzyValues;}

    public Set union(FuzzySet f1)
    {
        Set newSet = new Set();
        for(Value v1: f1.getValues())
        {
            for(Value v: values)
            if(v.getId().equals(v1.getId()))
            {
                newSet.getValues().add(v1);
            }
        }
        return newSet;
    }

}
