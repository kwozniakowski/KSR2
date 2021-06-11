package Attributes;

import Memberships.Membership;
import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class FuzzySet {
    private String name;
    private String attributeName;
    private Membership membership;

    public FuzzySet(String attributeName, String name, Membership membership)
    {
        this.attributeName = attributeName;
        this.name = name;
        this.membership = membership;
    }

    public String getName(){return name;}
    public Membership getMembership()
    {
        return membership;
    }
    public double getCardinality(ArrayList<Match> matches)
    {
        double sum = 0;
        for(Match m: matches)
        {
            sum = sum + getMembership().getDegree(m.getMatchAttribute(name));
        }
        return sum;
    }

    public String getAttributeName() {return attributeName;}


}
