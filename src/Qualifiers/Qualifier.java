package Qualifiers;

import Attributes.FuzzySet;
import Attributes.Value;

import java.util.ArrayList;

public class Qualifier {
    private ArrayList<String> idsOfQualifiedValues;
    private FuzzySet fuzzySet;
    private String label;

    public Qualifier(FuzzySet fuzzySet,String name)
    {
        this.label = name;
        this.fuzzySet = fuzzySet;
        this.idsOfQualifiedValues = new ArrayList<>();
    }

    public void qualify()
    {
        for(Value v: fuzzySet.getValues())
        {
            if(v.getLabel() == label)
            {
                idsOfQualifiedValues.add(v.getId());
            }
        }
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }

    public ArrayList<String> getIdsOfQualifiedValues()
    {
        return idsOfQualifiedValues;
    }

    public String getLabel() {return label;}
}
