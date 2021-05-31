package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class FuzzySet {
    private String name;
    private Membership membership;

    public FuzzySet(String name, Membership membership)
    {
        this.name = name;
        this.membership = membership;
    }

    public String getName(){return name;}
    public Membership getMembership()
    {
        return membership;
    }


}
