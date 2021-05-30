package Summaries;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Set;
import Memberships.Measures;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class SummaryGenerator {
    private ArrayList<Attribute> attributes;
    private String attribute;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    private String summarizerString;
    private boolean generalMode;
    private ArrayList<String> results;
    private Summarizer summarizer;

    public SummaryGenerator(String attribute, String summarizerString, boolean generalMode
    , ArrayList<Attribute> attributes, Quantifier relativeQuantifier, Quantifier absoluteQuantifier)
    {
        this.generalMode = generalMode;
        this.summarizerString = summarizerString;
        this.attribute = attribute;
        this.attributes = attributes;
        this.relativeQuantifier =relativeQuantifier;
        this.absoluteQuantifier = absoluteQuantifier;
        this.results = new ArrayList<>();
        setSummarizer();
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
                        if(summarizerString == "Brak")
                        {
                            String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)f.getValues().size()/a.getValues().size());
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
                        }
                        else if(summarizerString == f.getName())
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
                        if(summarizerString == "Brak")
                        {
                            String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)f.getValues().size());
                            String s = "W " + linguisticDegree + " meczów " + a.getName() + " był " + f.getName();
                            System.out.println(s);
                            results.add(s);
                        }
                        else if(summarizerString == f.getName())
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
                    if(summarizerString == "Brak")
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)union.getValues().size()/qualifier.getFuzzySet().getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }
                    else if(summarizerString == f.getName())
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = relativeQuantifier.setLinguisticDegree((float)union.getValues().size()/qualifier.getFuzzySet().getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                        System.out.println(Measures.DegreeOfTruth(relativeQuantifier,linguisticDegree,qualifier,summarizer,a));

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
                    if(summarizerString == "Brak")
                    {
                        Set union = f.union(qualifier.getFuzzySet());
                        String linguisticDegree = absoluteQuantifier.setLinguisticDegree((float)union.getValues().size());
                        String s = "W " + linguisticDegree + " meczów, w ktorych "
                                + qualifier.getAttribute() + " był " + qualifier.getLabel() +", "
                                + a.getName() + " był " + f.getName();
                        System.out.println(s);
                        results.add(s);
                    }
                    else if(summarizerString == f.getName())
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



    public void setSummarizer()
    {
        if(summarizerString != "Brak")
        {
            for(Attribute a:attributes)
            {
                for(FuzzySet f: a.getFuzzySets())
                {
                    if(f.getName().equals(summarizerString)) this.summarizer = new Summarizer(f);
                }
            }
        }

    }
}
