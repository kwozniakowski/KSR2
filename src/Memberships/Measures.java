package Memberships;

import Attributes.Attribute;
import Attributes.Value;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class Measures {
    public static double DegreeOfTruth(Quantifier quantifier, String linguisticDegree, Qualifier qualifier, Summarizer summarizer, Attribute a)
    {
        double rUp = 0;
        double rDown = 0;

        for(Value v: a.getValues())
        {
            rUp += Math.min(qualifier.getFuzzySet().getMembership().getDegree(v.getValue()), summarizer.getFuzzySet().getMembership().getDegree(v.getValue()));
            rDown += qualifier.getFuzzySet().getMembership().getDegree(v.getValue());
        }
        System.out.println("rUP: " + rUp);
        System.out.println("rDown: " + rDown);
        for(Membership m: quantifier.getMemberships())
        {
            if(m.getName().equals(linguisticDegree))
            {
                return m.getDegree((float) rUp / (float) rDown);
            }
        }
        return 0;

    }

}
