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
        setFuzzySets();
    }
    public void setFuzzySets()
    {
        //on the bottom we set fuzzy sets
        for(Value v:values)
        {
            float maxDegree = 0;
            FuzzySet maxFuzzySet = fuzzySets.get(0);
            for(FuzzySet f : fuzzySets)
            {
                float degree = f.getMembership().getDegree(v.getValue());
                f.getFuzzyValues().add(new FuzzyValue(v,degree));
                if(degree > maxDegree)
                {
                    maxDegree = degree;
                    maxFuzzySet = f;
                }
            }
            v.setLabel(maxFuzzySet.getName());
            maxFuzzySet.getValues().add(v);
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
