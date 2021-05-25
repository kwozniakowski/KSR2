package Summaries;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Set;
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
    private String summarizer;
    private boolean generalMode;
    private ArrayList<String> results;

    public SummaryGenerator(String attribute, String summarizer, boolean generalMode
    ,ArrayList<Attribute> attributes,Quantifier relativeQuantifier,Quantifier absoluteQuantifier)
    {
        this.generalMode = generalMode;
        this.summarizer = summarizer;
        this.attribute = attribute;
        this.attributes = attributes;
        this.relativeQuantifier =relativeQuantifier;
        this.absoluteQuantifier = absoluteQuantifier;
        this.results = new ArrayList<>();
        setQualifier();
    }

    public ArrayList<String> generateFirstFormSummary()
    {
        this.results.clear();
        if(generalMode)
        {
            for(Attribute a : attributes)
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                    String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                    System.out.println(s);
                    results.add(s);
                }
            }
            for(Attribute a : attributes)
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float) f.getValues().size());
                    String s = "W " + linguisticDegree + " meczach " + a.getName() + " był " + f.getName();
                    System.out.println(s);
                    results.add(s);
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
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
                        }
                        else if(summarizer == f.getName())
                        {
                            String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
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
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
                        }
                        else if(summarizer == f.getName())
                        {
                            String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)f.getValues().size());
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
                        }
                    }
                }

            }
        }
        return results;

    }
    public ArrayList<String> generateSecondFormSummary(Qualifier qualifier)
    {

        this.results.clear();
        for(Attribute a : attributes)
        {
            if(a.getName().equals(attribute))
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    if(summarizer == "Brak")
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)union.getValues().size()/qualifier.getFuzzySet().getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }
                    else if(summarizer == f.getName())
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)union.getValues().size()/qualifier.getFuzzySet().getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }

                }
            }

        }
        for(Attribute a : attributes)
        {
            if(a.getName().equals(attribute))
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    if(summarizer == "Brak")
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)union.getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }
                    else if(summarizer == f.getName())
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)union.getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }

                }
            }

        }
        return results;

    }



    public void setQualifier()
    {


    }
}
