package Quantifiers;

import Attributes.FuzzySet;
import Attributes.Match;
import Memberships.Membership;

import java.util.ArrayList;

public class Quantifier extends FuzzySet {
    public boolean absolute;

    public Quantifier(String name, Membership membership, boolean absolute) {
        super(null, name,membership);
        this.absolute = absolute;
    }
    public double support() {
        return getMembership().getSupport();
    }


}
