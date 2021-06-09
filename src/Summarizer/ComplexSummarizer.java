package Summarizer;

import Memberships.Membership;
import Qualifiers.Qualifier;
import Summarizer.Summarizer;

public class ComplexSummarizer extends Summarizer {
    private Summarizer s1, s2;
    private boolean or;
    public ComplexSummarizer(String name, Membership membership, Summarizer s1, Summarizer s2, boolean or) {
        super(name, membership);
        this.s1 = s1;
        this.s2 = s2;
        this.or = or;
    }

    public Summarizer getMembership(double x, double y) {
        if (or)
        {
            if(s1.getMembership().getDegree((float) x) > s2.getMembership().getDegree((float) y)) return s1;
            else return s2;
        }
        else
        {
            if(s1.getMembership().getDegree((float) x) < s2.getMembership().getDegree((float) y)) return s1;
            else return s2;
        }
    }
}
