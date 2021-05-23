package Summaries;

import Attributes.FuzzySet;
import Attributes.Value;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;

import java.util.ArrayList;
import java.util.Arrays;

public class SummaryGenerator {
    private ArrayList<FuzzySet> fuzzySets;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    private Qualifier qualifier;
    public SummaryGenerator(ArrayList<FuzzySet> fuzzySets, Quantifier relativeQuantifier
            , Quantifier absoluteQuantifier, Qualifier qualifier)
    {
        this.fuzzySets = fuzzySets;
        this.relativeQuantifier = relativeQuantifier;
        this.absoluteQuantifier = absoluteQuantifier;
        this.qualifier = qualifier;
    }

    public void generateFirstFormSummary()
    {
        for(FuzzySet a : fuzzySets)
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
                System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " +label);
            }
        }
        for(FuzzySet a : fuzzySets)
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
                String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)counter);
                System.out.println("W " + linguisticDegree + " meczach " + a.getName() + " był " +label);
            }
        }

    }
    public void generateSecondFormSummary()
    {
        qualifier.qualify();
        for(FuzzySet a : fuzzySets)
        {
            int length = qualifier.getIdsOfQualifiedValues().size();
            for(String label: a.getPossibleLabels())
            {
                int counter = 0;
                for(Value v:a.getValues())
                {
                    if(v.getLabel() == label)
                    {
                        if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                        {
                            counter = counter + 1;
                        }
                    }
                }
                String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)counter/length);
                System.out.println("W " + linguisticDegree + " meczów, w ktorych "
                        + qualifier.getFuzzySet().getName() + " był " + qualifier.getLabel() +", "
                        + a.getName() + " był " +label);
            }
        }
        for(FuzzySet a : fuzzySets)
        {
            int length = a.getValues().size();
            for(String label: a.getPossibleLabels())
            {
                int counter = 0;
                for(Value v:a.getValues())
                {
                    if(v.getLabel() == label)
                    {
                        if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                        {
                            counter = counter + 1;
                        }
                    }
                }
                String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)counter);
                System.out.println("W " + linguisticDegree + " meczach, w ktorych "
                        + qualifier.getFuzzySet().getName() + " był " + qualifier.getLabel() +", "
                        + a.getName() + " był " +label);
            }
        }

    }
}
