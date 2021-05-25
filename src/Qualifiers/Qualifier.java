package Qualifiers;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Value;

import java.util.ArrayList;

public class Qualifier {
    private ArrayList<String> idsOfQualifiedValues;
    private String attribute;
    private String label;
    private FuzzySet fuzzySet;
    private ArrayList<Attribute> attributes;

    public Qualifier(String attribute, String label, ArrayList<Attribute> attributes)
    {
        this.attribute = attribute;
        this.label = label;
        this.idsOfQualifiedValues = new ArrayList<>();
        this.attributes = attributes;
        for(Attribute a: attributes)
        {
            if(a.getName().equals(attribute))
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    if(f.getName().equals(label))
                    {
                        this.fuzzySet = f;
                    }

                }
            }

        }
    }

    public ArrayList<String> getIdsOfQualifiedValues()
    {
        return idsOfQualifiedValues;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getLabel() {
        return label;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
