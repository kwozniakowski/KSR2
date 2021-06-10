package Summaries;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Match;
import Attributes.Set;
import Memberships.Measures;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;
import jdk.jshell.SourceCodeAnalysis;

import java.util.ArrayList;

public class SummaryGenerator {
    private ArrayList<Attribute> attributes;
    private Quantifier quantifier;
    private String summarizerString;
    private boolean generalMode;
    private ArrayList<String> results;
    private Summarizer summarizer;
    private Qualifier qualifier;
    private Attribute attribute;
    private Attribute qualifierAttribute;
    private ArrayList<Match> matches;

    public SummaryGenerator(Quantifier quantifier, Attribute qualifierAttribute, Qualifier qualifier,
                            Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        this.qualifierAttribute = qualifierAttribute;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier =quantifier;
        this.attribute = attribute;
        this.matches = matches;
    }

    public void generateFirstFormSummary()
    {
        Summary s = new Summary(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);
        String result = "W " + quantifier.getName() + " meczów " + attribute.getName() + " był " + summarizer.getName();
        System.out.println(result + " ["+ Measures.DegreeOfTruth(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches) + "]");

    }
    public void generateSecondFormSummary()
    {

        String result = "W " + quantifier.getName() + " meczów, w ktorych "
                                + qualifierAttribute.getName() + " był " + qualifier.getName() +", "
                                + attribute.getName() + " był " + summarizer.getName();
        double dof = Measures.DegreeOfTruth(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);
        //if(dof > 0.7)
            System.out.println(result + " [" + dof + "]" );

    }

}
