package Qualifiers;

import Attributes.Match;
import Memberships.Membership;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;
import java.util.Collections;

public class ComplexQualifier extends Qualifier{
    private ArrayList<Qualifier> qualifiers;
    private boolean or;

    public ComplexQualifier(String attributeName, String name, Membership membership, ArrayList<Qualifier> qualifiers, boolean or) {
        super(attributeName, name, membership);
        this.qualifiers = qualifiers;
        this.or = or;
    }
    public double getMembership(Match match)
    {
        ArrayList<Double> values = new ArrayList<>();
        for (Qualifier q: qualifiers){
            values.add((double) q.getMembership().getDegree(match.getMatchAttribute(q.getAttributeName())));
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

    public ArrayList<Qualifier> getQualifiers()
    {
        return qualifiers;
    }
}
