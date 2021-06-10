package Memberships;

import Attributes.Attribute;
import Attributes.Match;
import Qualifiers.ComplexQualifier;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summaries.Summary;
import Summarizer.Summarizer;
import Summarizer.ComplexSummarizer;

import java.util.ArrayList;
import java.util.List;

public class Measures {
    public static double DegreeOfTruth(Quantifier quantifier, Attribute qualifierAttribute, Qualifier qualifier, Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        double rUp = 0;
        double rDown = 0;
        double val = 0;
        if(qualifier != null)
        {
            for(Match m : matches)
            {
                rUp += Math.min(
                        qualifier.getMembership().getDegree(m.getMatchAttribute(qualifierAttribute.getName())),
                        summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName())));

                rDown += qualifier.getMembership().getDegree(m.getMatchAttribute(summarizer.getName()));
            }
            val = quantifier.getMembership().getDegree((float) (rUp/rDown));
        }
        else
        {
            if(quantifier.absolute)
            {
                for(Match m: matches)
                {
                    rUp = rUp + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName()));
                }
                val = quantifier.getMembership().getDegree((float) rUp );
            }
            else
            {
                for(Match m: matches)
                {
                    rUp = rUp + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName()));

                }
                val = quantifier.getMembership().getDegree((float) rUp / matches.size() );
            }
        }
        return val;

    }

    public double T2(ArrayList<Match> matches, Summarizer summarizer){
        double pom =1.0;
        pom *= summarizer.getFuzziness(matches);
        if (summarizer instanceof ComplexSummarizer)
        {
            return 1 - Math.pow(pom,1.0 / summarizer.getSize());
        }
        return 1 - Math.pow(pom,1.0 / 1.0);
    }

    public double T3(ArrayList<Match> matches, Qualifier qualifier, Attribute qualifierAttribute , Summarizer summarizer)
    {
        double s = 0;
        double q = 0;
        for(Match m : matches)
        {
            if (qualifier == null){
                if (summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName())) > 0){
                    s++;
                }
            }
            else
            {
                if (qualifier.getMembership().getDegree(m.getMatchAttribute(qualifierAttribute.getName())) > 0){
                    q++;
                    if (summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName())) > 0) {
                        s++;
                    }
                }
            }

        }
        if (qualifier == null){
            return s/ matches.size();
        } else {
            return s / q;
        }
    }
    public double T4(ArrayList<Match> matches, Summarizer summarizer)
    {
        double a = 1;
        double b = 0;
        for(Match m: matches)
        {
            b = b + summarizer.getMembership().getDegree(m.getMatchAttribute(summarizer.getName()));
        }
        a = a * b/matches.size();
        return Math.abs(a - T3(matches));

    }
    public double T5(List<Summary> summaries, Summarizer summarizer){
        if (summarizer instanceof ComplexSummarizer){
            return 2 * Math.pow(0.5,((ComplexSummarizer) summarizer).getSize());

        } else {
            return 2 * Math.pow(0.5,1);
        }
    }

    public double T6(ArrayList<Match> matches, Quantifier quantifier)
    {
        //Ponizej moze okazac sie zle, ale nie musi
        double a = quantifier.support(matches).size();
        if (quantifier.absolute){
            a = a / matches.size();
        }
        return 1 - a;
    }

    public double T7(ArrayList<Match> matches, Quantifier quantifier)
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

    public double T8(ArrayList<Match> matches, List<LinguisticSummary> linguisticSummaries, Summarizer summarizer){
        double a = 1;
        if (summarizer instanceof ComplexSummarizer){
            a *= ((ComplexSummarizer) summarizer).getMembership().getCardinality();
            System.out.println(a);
            return 1.0 - Math.pow(a,1.0/((ComplexSummarizer) summarizer).size());
        } else {
            a *= summarizer.getMembership().getCardinality() / matches.size();
            return 1.0 - Math.pow(a,1);
        }
    }

    public double T9(ArrayList<Match> matches, Qualifier qualifier){
        if(qualifier != null) return 1.0 - qualifier.getFuzziness(matches);
        else return 0;
    }

    public double T10(ArrayList<Match> matches, Qualifier qualifier)
    {
        if (qualifier == null){
            return 0.0;
        }
        if (qualifier instanceof ComplexQualifier){
            return 1.0 - ((ComplexQualifier) qualifier).getMembership().getCardinality();
        }
        return 1.0 - qualifier.getMembership().getCardinality() / matches.size();
    }

    public double T11(ArrayList<Summary> summaries, Qualifier qualifier){
        if (qualifier == null){
            return 0.0;
        }
        if (qualifier instanceof ComplexQualifier){
            return 2 * Math.pow(0.5,((ComplexQualifier) qualifier).getSize());
        } else {
            return 2 * Math.pow(0.5,1);
        }
    }

    public ArrayList<Double> calculateMeasures(ArrayList<Summary> summaries, ArrayList<Match> matches,
                                               Attribute qualifierAttribute, Qualifier qualifier, Quantifier quantifier,
                                               Summarizer summarizer, Attribute attribute){
        ArrayList<Double> measures = new ArrayList<>();
        measures.add(DegreeOfTruth(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches));
        measures.add(T2(matches,summaries));
        measures.add(T3(matches, qualifier, summarizer));
        measures.add(T4(matches,summaries));
        measures.add(T5(summaries,summarizer));
        measures.add(T6(matches,quantifier));
        measures.add(T7(matches,quantifier));
        measures.add(T8(matches, summaries,summarizer));
        measures.add(T9(matches,summaries));
        measures.add(T10(matches,qualifier));
        measures.add(T11(summaries,qualifier));
        measures.forEach(System.out::println);
        double a = 0;
        for (Double m : measures)
        {
            a = a + ( (1.0 / 11) * m);
        }
        return measures;
    }


}
