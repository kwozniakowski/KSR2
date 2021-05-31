package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class Attribute {
    private String name;
    private ArrayList<FuzzySet> fuzzySets;
    private ArrayList<Value> values;
    public Attribute(String name, ArrayList<Value> values, ArrayList<Membership> memberships){
        this.name = name;
        this.values = values;
        this.fuzzySets = new ArrayList<>();
        for(Membership m: memberships)
        {
            this.fuzzySets.add(new FuzzySet(m.getName(), m));
        }
    }


    public ArrayList<FuzzySet> getFuzzySets()
    {
        return fuzzySets;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Value> getValues() {
        return values;
    }
}
