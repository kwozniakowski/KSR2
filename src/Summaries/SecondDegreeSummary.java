package Summaries;

import Attributes.Attribute;
import Attributes.Match;
import Memberships.Measures;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class SecondDegreeSummary implements Comparable<SecondDegreeSummary> {
    ArrayList<Match> matches1;
    ArrayList<Match> matches2;
    String subject1Name;
    String subject2Name;
    Quantifier quantifier;
    Summarizer summarizer;
    Attribute attribute;
    Qualifier qualifier;
    int form;
    double measure;
    Measures measuresModule;

    public SecondDegreeSummary(ArrayList<Match> matches1, ArrayList<Match> matches2, String subject1Name, String subject2Name,
                               Attribute attribute, Quantifier quantifier, Summarizer summarizer, int form) {
        this.matches1 = matches1;
        this.matches2 = matches2;
        this.subject1Name = subject1Name;
        this.subject2Name = subject2Name;
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.attribute = attribute;
        this.form = form;
    }

    public SecondDegreeSummary(ArrayList<Match> matches1, ArrayList<Match> matches2, String subject1Name, String subject2Name,
                               Attribute attribute,Quantifier quantifier, Summarizer summarizer, Qualifier qualifier, int form) {
        this.matches1 = matches1;
        this.matches2 = matches2;
        this.subject1Name = subject1Name;
        this.subject2Name = subject2Name;
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.qualifier = qualifier;
        this.attribute = attribute;
        this.form = form;
    }

    public SecondDegreeSummary(ArrayList<Match> matches1, ArrayList<Match> matches2, String subject1Name, String subject2Name,
                               Attribute attribute,Summarizer summarizer, int form) {
        this.matches1 = matches1;
        this.matches2 = matches2;
        this.subject1Name = subject1Name;
        this.subject2Name = subject2Name;
        this.summarizer = summarizer;
        this.attribute = attribute;
        this.form = form;
    }

    public double getMatches1Cardinality()
    {
        double sum = 0;
        if (form == 1){
            for (Match m : matches1){
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        } else if (form == 2){
            for (Match m : matches1){
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        } else if (form == 3) {
            for (Match m : matches1) {
                sum = sum + Math.min(summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName())),
                        qualifier.getMembership().getDegree(m.getMatchAttribute(qualifier.getAttributeName())));
            }
        }
        else
        {
            for (Match m : matches1)
            {
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        }
        return sum;
    }

    public double getMatches2Cardinality()
    {
        double sum = 0;
        if (form == 1){
            for (Match m : matches2){
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        } else if (form == 2){
            for (Match m : matches2){
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        } else if (form == 3) {
            for (Match m : matches2) {
                sum = sum + Math.min(summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName())),
                        qualifier.getMembership().getDegree(m.getMatchAttribute(qualifier.getAttributeName())));
            }
        }
        else
        {
            for (Match m : matches2)
            {
                sum = sum + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
        }
        return sum;
    }

    public String toString() {
        if (form == 1) {
            return "W " + quantifier.getName() + " meczow, ktore byly rozgrywane na " + subject1Name +
                    " w porównaniu do meczy rozgrywanych na " + subject2Name + ", " + attribute.getName() +
                    " był " + summarizer.getName() + " [" + measure + "]";
        } else if (form == 2) {
            return "W " + quantifier.getName() + " meczow, ktore byly rozgrywane na" + subject1Name +
                    " w porównaniu do meczy rozgrywanych na " + subject2Name + ", w których " +
                    qualifier.getAttributeName() + " był " + qualifier.getName() + "," + attribute.getName() +
                    " był " + summarizer.getName() + " [" + measure + "]";
        } else if (form == 3) {
            return "W " + quantifier.getName() + " meczow, ktore byly rozgrywane na " + subject1Name +
                    ", w których " + qualifier.getAttributeName() + " był " + qualifier.getName()
                    + " w porównaniu do meczy rozgrywanych na " + subject2Name + attribute.getName()
                    + " był " + summarizer.getName() + " [" + measure + "]";
        } else if (form == 4) {
            return "W wiekszej ilosci meczow, ktore byly rozgrywane na" + subject1Name +
                    " niz meczow ktore byly rozgrywane na " + subject2Name + ", " +
                    attribute.getName() + " bylo " + summarizer.getName() + " [" + measure + "]";
        } else {
            return "Błąd!";
        }
    }

    public ArrayList<Match> getMatches1() {
        return matches1;
    }

    public ArrayList<Match> getMatches2() {
        return matches2;
    }

    public String getSubject1Name() {
        return subject1Name;
    }

    public String getSubject2Name() {
        return subject2Name;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public Summarizer getSummarizer() {
        return summarizer;
    }

    public Qualifier getQualifier() {
        return qualifier;
    }

    public int getForm() {
        return form;
    }

    public Double getMeasure() {return measure;}

    public int compareTo(SecondDegreeSummary s)
    {
        return this.getMeasure().compareTo(s.getMeasure());
    }

    public void calculateMeasures()
    {
        this.measuresModule = new Measures();
        this.measure = measuresModule.calculateMeasures(this);
    }
}
