package Quantifiers;

import Memberships.Membership;
import Memberships.TriangularMembership;

import java.util.ArrayList;

public class Quantifier {
    private ArrayList<Membership> memberships;

    public Quantifier(ArrayList<Membership> memberships) {
        this.memberships = memberships;
    }

    public String setLinguisticDegree(float degree)
    {
        float maxDegree = 0;
        String linguisticDegree = null;
        for(Membership m:memberships)
        {
            float newDegree = m.getDegree(degree);
            String newLinguisticDegree = m.getName();
            if(newDegree > maxDegree)
            {
                maxDegree = newDegree;
                linguisticDegree = newLinguisticDegree;
            }
        }
        return linguisticDegree;
    }
}
