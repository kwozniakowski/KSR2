package Quantifiers;

import Attributes.FuzzySet;
import Attributes.Match;
import Memberships.Membership;

import java.util.ArrayList;

public class Quantifier extends FuzzySet {
    public boolean absolute;

    public Quantifier(String name, Membership membership, boolean absolute) {
        super(name,membership);
        this.absolute = absolute;
    }
    public ArrayList<Match> support(ArrayList<Match> matches) {
        ArrayList<Match> support = new ArrayList<>();
        for (Match m : matches) {
            if (getMembership().getDegree(m.getMatchAttribute(getName())) > 0) {
                support.add(m);
            }
        }
        return support;
    }


}
