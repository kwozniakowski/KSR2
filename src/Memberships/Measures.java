package Memberships;

import Attributes.Attribute;
import Attributes.Match;
import Attributes.Value;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Measures {
    public static double DegreeOfTruth(Quantifier quantifier, Attribute qualifierAttribute, Qualifier qualifier, Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        double rUp = 0;
        double rDown = 0;

        if(qualifier != null)
        {
            for(Match m : matches)
            {
                rUp += Math.min(qualifier.getMembership().getDegree(m.getMatchStat(qualifierAttribute.getName())),
                        summarizer.getMembership().getDegree(m.getMatchStat(attribute.getName())));

                rDown += qualifier.getMembership().getDegree(m.getMatchStat(qualifier.getName()));
            }
        }
        else
        {
            for(Match m : matches)
            {
                rUp += summarizer.getMembership().getDegree(m.getMatchStat(attribute.getName()));
                rDown += 1;
            }
        }
        if(quantifier.absolute) return quantifier.getMembership().getDegree((float) rUp );
        return quantifier.getMembership().getDegree((float) rUp / (float) rDown);

    }

    public double T2(ArrayList<Match> matches, Summarizer summarizer){
        double pom =1.0;
        pom *= summarizer.getFuzziness(matches);
        //if (summarizer instanceof ComplexSummarizer) return 1 - Math.pow(pom,1.0 / 2.0);
        return 1 - Math.pow(pom,1.0 / 1.0);
    }

    public double T3(ArrayList<Match> matches, Qualifier qualifier, Summarizer summarizer)
    {
        double s = 0;
        double q = 0;
        for(Match m : matches)
        {
            if (qualifier == null){
                if (summarizer.getMembership().getDegree(m.getMatchStat(summarizer.getName())) > 0){
                    s++;
                }
            }
            else
            {
                if (qualifier.getMembership().getDegree(m.getMatchStat(qualifier.getName())) > 0){
                    q++;
                    if (summarizer.getMembership().getDegree(m.getMatchStat(summarizer.getName())) > 0) {
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
            b = b + summarizer.getMembership().getDegree(m.getMatchStat(summarizer.getName()));
        }
        a = a * b/matches.size();
        return Math.abs(a - T3(matches, null, summarizer));

    }
    public double T5(List<LinguisticSummary> linguisticSummaries, Summarizer summarizer){
        List<LinguisticSummary> linguisticSummaryList = new ArrayList<>();
        for (LinguisticSummary linguisticSummary : linguisticSummaries){
            if (linguisticSummary.summarizer.name.equals(summarizer.getName())){
                linguisticSummaryList.add(linguisticSummary);
            }
        }
        return 2 * Math.pow(0.5,linguisticSummaryList.size());
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
        List<LinguisticSummary> linguisticSummaryList = new ArrayList<>();
        for (LinguisticSummary linguisticSummary : linguisticSummaries){
            if (linguisticSummary.summarizer.name.equals(this.summarizer.name)){
                linguisticSummaryList.add(linguisticSummary);
            }
        }
        double a = 1.0;
        for (LinguisticSummary linguisticSummary : linguisticSummaryList){
            a = a * linguisticSummary.summarizer.cardinality / players.size();
        }
        return 1.0 - Math.pow(a,1.0/linguisticSummaryList.size());
    }

    public double T9(ArrayList<Match> matches, Qualifier qualifier){
//        if (qualifier == null){
//            return 0.0;
//        }
//        List<LinguisticSummary> linguisticSummaryList = new ArrayList<>();
//        for (LinguisticSummary linguisticSummary : linguisticSummaries){
//            if (linguisticSummary.qualifier.name.equals(this.qualifier.name)){
//                linguisticSummaryList.add(linguisticSummary);
//            }
//        }
//        double pom = 1.0;
//        for (LinguisticSummary linguisticSummary : linguisticSummaryList){
//            pom *= linguisticSummary.getFuzziness3(players);
//        }
//        return 1.0 - Math.pow(pom,1.0/linguisticSummaryList.size());
        return 1.0 - qualifier.getFuzziness(matches);
    }

    public double T10(ArrayList<Match> matches, Qualifier qualifier)
    {
        //        if (qualifier == null){
//            return 0.0;
//        }
//        List<LinguisticSummary> linguisticSummaryList = new ArrayList<>();
//        for (LinguisticSummary linguisticSummary : linguisticSummaries){
//            if (linguisticSummary.qualifier.name.equals(this.qualifier.name)){
//                linguisticSummaryList.add(linguisticSummary);
//            }
//        }
//        double pom = 1.0;
//        for (LinguisticSummary linguisticSummary : linguisticSummaryList){
//            pom *= linguisticSummary.qualifier.cardinality;
//        }
//        return 1.0 - Math.pow(pom,1.0/linguisticSummaryList.size());
        return 1.0 - qualifier.getMembership().getCardinality() / matches.size();
    }

    public double T11(List<LinguisticSummary> linguisticSummaries, Qualifier qualifier){
        if (qualifier == null){
            return 0.0;
        }
        List<LinguisticSummary> linguisticSummaryList = new ArrayList<>();
        for (LinguisticSummary linguisticSummary : linguisticSummaries){
            if (linguisticSummary.qualifier.name.equals(this.qualifier.name)){
                linguisticSummaryList.add(linguisticSummary);
            }
        }
        return 2 * Math.pow(0.5,linguisticSummaryList.size());
    }

    public ArrayList<Double> calculateMeasures(List<LinguisticSummary> linguisticSummaries, ArrayList<Match> matches,
                                               Attribute qualifierAttribute, Qualifier qualifier, Quantifier quantifier,
                                               Summarizer summarizer, Attribute attribute){
        ArrayList<Double> measures = new ArrayList<>();
        measures.add(DegreeOfTruth(quantifier,qualifierAttribute,qualifier,attribute,summarizer,matches));
        measures.add(T2(matches,linguisticSummaries));
        measures.add(T3(matches, qualifier, summarizer));
        measures.add(T4(matches,linguisticSummaries));
        measures.add(T5(linguisticSummaries,summarizer));
        measures.add(T6(matches,quantifier));
        measures.add(T7(matches,quantifier));
        measures.add(T8(matches, linguisticSummaries,summarizer));
        measures.add(T9(matches,linguisticSummaries));
        measures.add(T10(matches,qualifier));
        measures.add(T11(linguisticSummaries,qualifier));
        measures.forEach(System.out::println);
        double a = 0;
        for (Double m : measures)
        {
            a = a + ( (1.0 / 11) * m);
        }
        return measures;
    }


}
