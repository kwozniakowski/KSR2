package Memberships;

import Attributes.Attribute;
import Attributes.Match;
import Attributes.Value;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summarizer.Summarizer;

import java.util.ArrayList;

public class Measures {
    public static double DegreeOfTruth(Quantifier quantifier, Qualifier qualifier, Attribute attribute, Summarizer summarizer, ArrayList<Match> matches)
    {
        double rUp = 0;
        double rDown = 0;

        if(qualifier != null)
        {
            for(Match m : matches)
            {
                rUp += Math.min(qualifier.getMembership().getDegree(m.getMatchStat(qualifier.getName())),
                        summarizer.getMembership().getDegree(m.getMatchStat(attribute.getName())));

                rDown += qualifier.getMembership().getDegree(m.getMatchStat(qualifier.getName()));
            }
        }
        else
        {
            for(Match m : matches)
            {
                rUp += summarizer.getMembership().getDegree(m.getMatchStat(attribute.getName()));
                rDown += 1;
            }
        }
        if(quantifier.absolute) return quantifier.getMembership().getDegree((float) rUp );
        return quantifier.getMembership().getDegree((float) rUp / (float) rDown);

    }

}
