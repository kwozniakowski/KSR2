package Qualifiers;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Match;
import Attributes.Value;
import Memberships.Membership;

import java.util.ArrayList;
import java.util.List;

public class Qualifier extends FuzzySet {
    private ArrayList<String> idsOfQualifiedValues;
    private String attribute;
    private String label;
    private FuzzySet fuzzySet;
    private ArrayList<Attribute> attributes;
    private Membership membership;

    public Qualifier(String name, Membership membership)
    {
        super(name, membership);

    }

    public double getFuzziness(ArrayList<Match> matches)
    {
        return ((double) support(matches).size()) / ((double) matches.size());
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

}
