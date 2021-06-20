package Memberships;

import Attributes.Attribute;
import Attributes.Match;
import Qualifiers.ComplexQualifier;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summaries.FirstDegreeSummary;
import Summaries.SecondDegreeSummary;
import Summarizer.Summarizer;
import Summarizer.ComplexSummarizer;

import java.util.ArrayList;

public class Measures {
    private Quantifier quantifier;
    private Attribute attribute;
    private Qualifier qualifier;
    private Summarizer summarizer;
    private ArrayList<Match> matches;
    ArrayList<Double> measures;

    public Measures(Quantifier quantifier, Attribute attribute, Qualifier qualifier, Summarizer summarizer, ArrayList<Match> matches) {
        this.quantifier = quantifier;
        this.attribute = attribute;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.matches = matches;
        this.measures = new ArrayList<>();
    }

    public Measures(){};

    public double DegreeOfTruth()
    {
        double rUp = 0;
        double rDown = 0;
        double val = 0;
        if(qualifier != null)
        {
            for(Match m : matches)
            {
                rUp += Math.min(
                        qualifier.getMembership().getDegree(m.getMatchAttribute(qualifier.getAttributeName())),
                        summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName())));

                rDown += qualifier.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
            }
            if(quantifier.absolute){
                val = quantifier.getMembership().getDegree((float) (rUp));
            }
            else
            {
                val = quantifier.getMembership().getDegree((float) (rUp/rDown));
            }
        }
        else
        {
            if(quantifier.absolute)
            {
                for(Match m: matches)
                {
                    rUp = rUp + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
                }
                val = quantifier.getMembership().getDegree((float) rUp );
            }
            else
            {
                for(Match m: matches)
                {
                    rUp = rUp + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));

                }
                val = quantifier.getMembership().getDegree((float) rUp / matches.size() );
            }
        }
        return val;

    }

    public double T2(){
        double pom =1.0;
        pom *= summarizer.getFuzziness(matches);
        if (summarizer instanceof ComplexSummarizer)
        {
            return 1 - Math.pow(pom,1.0 / ((ComplexSummarizer) summarizer).getSummarizers().size());
        }
        return 1 - Math.pow(pom,1.0 / 1.0);
    }

    public double T3()
    {
        double s = 0;
        double q = 0;
        for(Match m : matches)
        {
            if (qualifier == null){
                if (summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName())) > 0){
                    s++;
                }
            }
            else
            {
                if (qualifier.getMembership().getDegree(m.getMatchAttribute(qualifier.getAttributeName())) > 0){
                    q++;
                    if (summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName())) > 0) {
                        s++;
                    }
                }
            }

        }
        if (qualifier == null){
            return s/ matches.size();
        } else {
            if(q==0) return 0;
            else return s / q;
        }
    }
    public double T4()
    {
        double a = 1;
        double b = 0;
        for(Match m: matches)
        {
            b = b + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getAttributeName()));
        }
        a = a * b/matches.size();
        return Math.abs(a - T3());

    }
    public double T5(){
        if (summarizer instanceof ComplexSummarizer){
            return 2 * Math.pow(0.5,((ComplexSummarizer) summarizer).getSummarizers().size());

        } else {
            return 2 * Math.pow(0.5,1);
        }
    }

    public double T6()
    {
        double a = quantifier.support();
        if (quantifier.absolute){
            a = a / matches.size();
        }
        return 1 - a;
    }

    public double T7()
    {
        if (quantifier.absolute)
        {
            return 1 - (quantifier.getMembership().getCardinality() / matches.size());
        }
        else
        {
            return 1 - quantifier.getMembership().getCardinality();
        }
    }

    public double T8(){
        double a = 1;
        if (summarizer instanceof ComplexSummarizer){
            a *= ((ComplexSummarizer) summarizer).getMembership().getCardinality();
            System.out.println(a);
            return 1.0 - Math.pow(a,1.0/((ComplexSummarizer) summarizer).getSummarizers().size());
        } else {
            a *= summarizer.getMembership().getCardinality() / matches.size();
            return 1.0 - Math.pow(a,1);
        }
    }

    public double T9()
    {
        if(qualifier != null) return 1.0 - qualifier.getFuzziness(matches);
        else return 0;
    }

    public double T10()
    {
        if (qualifier == null){
            return 0.0;
        }
        if (qualifier instanceof ComplexQualifier){
            return 1.0 - ((ComplexQualifier) qualifier).getMembership().getCardinality();
        }
        return 1.0 - qualifier.getMembership().getCardinality() / matches.size();
    }

    public double T11(){
        if (qualifier == null){
            return 0.0;
        }
        if (qualifier instanceof ComplexQualifier){
            return 2 * Math.pow(0.5,((ComplexQualifier) qualifier).getQualifiers().size());
        } else {
            return 2 * Math.pow(0.5,1);
        }
    }

    public double T(SecondDegreeSummary s)
    {
        if (s.getForm() == 1 || s.getForm() == 2 || s.getForm() == 3)
        {
            double rUp = s.getMatches1Cardinality() / s.getMatches1().size();
            double rDown = s.getMatches1Cardinality() / s.getMatches1().size() + s.getMatches2Cardinality() / s.getMatches2().size();
            return s.getQuantifier().getMembership().getDegree((float) (rUp / rDown));
        }
        else
        {
            double rUp = s.getMatches1Cardinality();
            double rDown = s.getMatches1Cardinality() + s.getMatches2Cardinality();
            return rUp /rDown;
        }
    }

    public double calculateMeasures(ArrayList<FirstDegreeSummary> summaries){

        measures.add(T2());//0.06
        measures.add(T3());//0.06
        measures.add(T4());//0.06
        measures.add(T5());//0.06
        measures.add(T6());//0.06
        measures.add(T7());//0.06
        measures.add(T8());//0.06
        measures.add(T9());//0.06
        measures.add(T10());//0.06
        measures.add(T11());//0.06
        double a = 4.0 * DegreeOfTruth()/10;//0.4
        for (Double m : measures)
        {
            a = a + ( (6.0 / 100) * m);
        }
        return a;
    }

    public double calculateMeasures(SecondDegreeSummary summary){

        return T(summary);
    }

    public ArrayList<Double> getMeasures(){return measures;}


}
