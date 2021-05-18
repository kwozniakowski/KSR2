package Summaries;

import Attributes.Attribute;
import Attributes.Value;
import Quantifiers.Quantifier;

import java.util.ArrayList;

public class SummaryGenerator {
    private ArrayList<Attribute> attributes;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    public SummaryGenerator()
    {

    }

    public void generate()
    {
        for(Value v: attributes.get(0).getValues())
        {
            //TODO: the whole summary generating algorithm
        }
    }
}
