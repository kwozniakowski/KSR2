package Summarizer;

import Attributes.Match;
import Memberships.Membership;
import Summarizer.Summarizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComplexSummarizer extends Summarizer {
    private ArrayList<Summarizer> summarizers;
    private boolean or;

    public ComplexSummarizer(String attributeName, String name, Membership membership, ArrayList<Summarizer> summarizers, boolean or) {
        super(attributeName, name,  membership);
        this.summarizers = summarizers;
        this.or = or;
    }

    public double getMembership(Match match)
    {
        ArrayList<Double> values = new ArrayList<>();
        for (Summarizer s : summarizers){
            values.add((double) s.getMembership().getDegree(match.getMatchAttribute(s.getName())));
        }
        Collections.sort(values);
        if (or)
        {
            return values.get(values.size()-1);
        }
        else
        {
            return values.get(0);
        }
    }


    public ArrayList<Summarizer> getSummarizers() {
        return summarizers;
    }
}
