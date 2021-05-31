package Quantifiers;

import Attributes.FuzzySet;
import Memberships.Membership;
import Memberships.TriangularMembership;

import java.util.ArrayList;

public class Quantifier extends FuzzySet {
    public boolean absolute;

    public Quantifier(String name, Membership membership, boolean absolute) {
        super(name,membership);
        this.absolute = absolute;
    }


}
