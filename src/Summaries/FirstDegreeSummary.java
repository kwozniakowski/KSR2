package Summaries;

import Attributes.Attribute;
import Attributes.Match;
import Memberships.Measures;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class FirstDegreeSummary implements Comparable<FirstDegreeSummary> {
    private Quantifier quantifier;
    private Attribute attribute;
    private Qualifier qualifier;
    private Summarizer summarizer;
    private ArrayList<Match> matches;
    private Double measure;
    private Measures measuresModule;

    public FirstDegreeSummary(Quantifier quantifier, Qualifier qualifier,
                              Attribute attribute, Summarizer summarizer, ArrayList<Match> matches) {
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

    public Measures getMeasuresModule() {return measuresModule;}

    public void calculateMeasures(ArrayList<FirstDegreeSummary> summaries)
    {
        this.measuresModule = new Measures(quantifier,attribute,qualifier,summarizer,matches);
        this.measure = measuresModule.calculateMeasures(summaries);
    }

    public String toString()
    {
        if(qualifier == null)
        {
            return "W " + quantifier.getName() + " meczów " + attribute.getName() + " był " + summarizer.getName() + " [" + measure + "]";

        }
        else
        {
            return "W " + quantifier.getName() + " meczów, w ktorych " + qualifier.getAttributeName()
                    + " był " + qualifier.getName()+ ", "+ attribute.getName() + " był " + summarizer.getName() + " [" + measure + "]";

        }
    }

    public Double getMeasure() {return measure;}

    @Override
    public int compareTo(FirstDegreeSummary s)
    {
        return this.getMeasure().compareTo(s.getMeasure());
    }
}
