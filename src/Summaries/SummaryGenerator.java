package Summaries;

import Attributes.Attribute;
import Attributes.Value;
import Quantifiers.Quantifier;

import java.util.ArrayList;

public class SummaryGenerator {
    private ArrayList<Attribute> attributes;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    public SummaryGenerator(ArrayList<Attribute> attributes, Quantifier relativeQuantifier)
    {
        this.attributes = attributes;
        this.relativeQuantifier = relativeQuantifier;
    }

    public void generate()
    {
        for(Attribute a : attributes)
        {
            int length = a.getValues().size();
            for(String label: a.getPossibleLabels())
            {
                int counter = 0;
                for(Value v:a.getValues())
                {
                    if(v.getLabel() == label)
                    {
                        counter = counter + 1;
                    }
                }
                String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)counter/length);
                System.out.println(linguisticDegree + " " + a.getName() + " był " +label);
            }
        }
    }
}
