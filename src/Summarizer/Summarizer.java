package Summarizer;

import Attributes.FuzzySet;
import Attributes.Match;
import Memberships.Membership;

import java.util.ArrayList;

public class Summarizer extends FuzzySet{
    public Summarizer(String name, Membership membership)
    {
        super(name, membership);
    }

    public ArrayList<Match> support(ArrayList<Match> matches) {
        ArrayList<Match> support = new ArrayList<>();
        for (Match m : matches) {
            if (getMembership().getDegree(m.getMatchStat(getName())) > 0) {
                support.add(m);
            }
        }
        return support;
    }

    public double getFuzziness(ArrayList<Match> matches) {
        return  ((double) support(matches).size()) / ((double) matches.size());
    }


}
