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

    public SummaryGenerator(Quantifier quantifier, Qualifier qualifier,
                            Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier =quantifier;
        this.attribute = attribute;
        this.matches = matches;
    }

    public Summary generateFirstFormSummary()
    {
        Summary s = new Summary(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);

        return s;

    }
    public Summary generateSecondFormSummary()
    {
        Summary s = new Summary(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);
        return s;
    }

}
