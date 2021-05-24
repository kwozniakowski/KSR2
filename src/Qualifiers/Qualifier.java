package Qualifiers;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Value;

import java.util.ArrayList;

public class Qualifier {
    private ArrayList<String> idsOfQualifiedValues;
    private Attribute attribute;
    private String label;

    public Qualifier(Attribute attribute, String label)
    {
        this.attribute = attribute;
        this.label = label;
        this.idsOfQualifiedValues = new ArrayList<>();
    }

    public void qualify()
    {
        for(FuzzySet f: attribute.getFuzzySets())
        {
            if(f.getName().equals(label))
            {
                for(Value v: f.getValues())
                {
                    idsOfQualifiedValues.add(v.getId());
                }
            }
        }
    }

    public ArrayList<String> getIdsOfQualifiedValues()
    {
        return idsOfQualifiedValues;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public String getLabel() {
        return label;
    }
}
