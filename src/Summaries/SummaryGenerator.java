package Summaries;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Value;
import GUI.DataParser;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import Memberships.TriangularMembership;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;

import java.util.ArrayList;

public class SummaryGenerator {
    private ArrayList<Attribute> attributes;
    private String attribute;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    private Qualifier qualifier;
    private String summarizer;
    private boolean generalMode;

    public SummaryGenerator(String attribute, Qualifier qualifier, String summarizer, boolean generalMode
    ,ArrayList<Attribute> attributes,Quantifier relativeQuantifier,Quantifier absoluteQuantifier)
    {
        this.generalMode = generalMode;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.attribute = attribute;
        this.attributes = attributes;
        this.relativeQuantifier =relativeQuantifier;
        this.absoluteQuantifier = absoluteQuantifier;
        setQualifier();
    }

    public void generateFirstFormSummary()
    {
        if(generalMode)
        {
            for(Attribute a : attributes)
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                    System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName());
                }
            }
            for(Attribute a : attributes)
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    if(f.getName().equals(summarizer))
                    {
                        String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float) f.getValues().size());
                        System.out.println("W " + linguisticDegree + " meczach " + a.getName() + " był " + f.getName());
                    }

                }
            }
        }
        else
        {
            for(Attribute a : attributes)
            {
                if(a.getName() == attribute)
                {
                    for(FuzzySet f: a.getFuzzySets())
                    {
                        if(summarizer == "Brak")
                        {
                            String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                            System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName());
                        }
                        else if(summarizer == f.getName())
                        {
                            String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                            System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName());
                        }

                    }
                }

            }
            for(Attribute a : attributes)
            {
                if(a.getName() == attribute)
                {
                    for(FuzzySet f: a.getFuzzySets())
                    {
                        if(summarizer == "Brak")
                        {
                            String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)f.getValues().size());
                            System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName());
                        }
                        else if(summarizer == f.getName())
                        {
                            String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)f.getValues().size());
                            System.out.println("W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName());
                        }
                    }
                }

            }
        }

    }
    public void generateSecondFormSummary()
    {
        qualifier.qualify();
        for(Attribute a : attributes)
        {
            int length = qualifier.getIdsOfQualifiedValues().size();
            if(a.getName().equals(attribute))
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    int counter = 0;
                    if(summarizer == "Brak")
                    {
                        for(Value v:a.getValues())
                        {
                            if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                            {
                                counter = counter + 1;
                            }
                        }
                    }
                    else if(summarizer == f.getName())
                    {
                        for(Value v:a.getValues())
                        {
                            if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                            {
                                counter = counter + 1;
                            }
                        }
                    }
                    String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)counter/length);
                    System.out.println("W " + linguisticDegree + " meczów, w ktorych "
                            + qualifier.getAttribute().getName() + " był " + qualifier.getLabel() +", "
                            + a.getName() + " był " + f.getName());
                }
            }

        }
        for(Attribute a : attributes)
        {
            if(a.getName().equals(attribute))
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    int counter = 0;
                    if(summarizer == "Brak")
                    {
                        for(Value v:f.getValues())
                        {
                            if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                            {
                                counter = counter + 1;
                            }
                        }
                    }
                    else if(summarizer == f.getName())
                    {
                        for(Value v:f.getValues())
                        {
                            if((qualifier.getIdsOfQualifiedValues()).contains(v.getId()))
                            {
                                counter = counter + 1;
                            }
                        }
                    }
                    String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)counter);
                    System.out.println("W " + linguisticDegree + " meczów, w ktorych "
                            + qualifier.getAttribute().getName() + " był " + qualifier.getLabel() +", "
                            + a.getName() + " był " + f.getName());
                }
            }

        }

    }



    public void setQualifier()
    {

    }
}
