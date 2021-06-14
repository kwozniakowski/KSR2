package Summaries;

import Attributes.Attribute;
import Attributes.Match;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

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
    private ArrayList<Match> matches1;
    private ArrayList<Match> matches2;
    private int form;

    public SummaryGenerator(Quantifier quantifier, Qualifier qualifier,
                            Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier =quantifier;
        this.attribute = attribute;
        this.matches = matches;
    }
    public SummaryGenerator(Quantifier quantifier, Qualifier qualifier,
                            Attribute attribute, Summarizer summarizer, ArrayList<Match> matches1 ,
                            ArrayList<Match> matches2, int form)
    {
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.quantifier =quantifier;
        this.attribute = attribute;
        this.matches1 = matches1;
        this.matches2 = matches2;
        this.form = form;
    }

    public FirstDegreeSummary generateFirstFormSummary()
    {
        FirstDegreeSummary s = new FirstDegreeSummary(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);

        return s;

    }
    public FirstDegreeSummary generateSecondFormSummary()
    {
        FirstDegreeSummary s = new FirstDegreeSummary(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches);
        return s;
    }
    public SecondDegreeSummary generateSecondDegreeSummary()
    {
        SecondDegreeSummary s;
        if(form == 1)
        {
            s = new SecondDegreeSummary(matches1,matches2,matches1.get(0).getSurface(),
                    matches2.get(0).getSurface(),attribute,quantifier,summarizer,1);
        }
        else if(form == 2)
        {
            s = new SecondDegreeSummary(matches1,matches2,matches1.get(0).getSurface(),
                    matches2.get(0).getSurface(),attribute,quantifier,summarizer,qualifier,2);
        }
        else if(form == 3)
        {
            s = new SecondDegreeSummary(matches1,matches2,matches1.get(0).getSurface(),
                    matches2.get(0).getSurface(),attribute,quantifier,summarizer,qualifier,3);
        }
        else
        {
            s = new SecondDegreeSummary(matches1,matches2,matches1.get(0).getSurface(),
                    matches2.get(0).getSurface(),attribute,summarizer,4);
        }
        return s;
    }

}
