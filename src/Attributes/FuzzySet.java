package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class FuzzySet {
    private String name;
    private Membership membership;
    private ArrayList<Value> values;


    public FuzzySet(String name, Membership membership)
    {
        this.name = name;
        this.membership = membership;
        this.values = new ArrayList<>();
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

}
