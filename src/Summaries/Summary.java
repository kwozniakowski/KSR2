package Summaries;

import Attributes.Attribute;
import Attributes.Match;
import Memberships.Measures;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class Summary {
    private Quantifier quantifier;
    private Attribute attribute;
    private Attribute qualifierAttribute;
    private Qualifier qualifier;
    private Summarizer summarizer;
    private ArrayList<Match> matches;

    public Summary(Quantifier quantifier, Attribute qualifierAttribute, Qualifier qualifier,
                   Attribute attribute, Summarizer summarizer, ArrayList<Match> matches) {
        this.qualifierAttribute = qualifierAttribute;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier = quantifier;
        this.attribute = attribute;
        this.matches = matches;
    }
    public Qualifier getQualifier() {
        return qualifier;
    }

    public Summarizer getSummarizer() {
        return summarizer;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Attribute getQualifierAttribute() {
        return qualifierAttribute;
    }

    public double calculateMeasures(ArrayList<Summary> summaries)
    {
        Measures measuresModule = new Measures();
        return measuresModule.calculateMeasures(summaries,matches,qualifierAttribute,qualifier,quantifier,summarizer,attribute);

    }
}
