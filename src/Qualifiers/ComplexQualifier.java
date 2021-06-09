package Qualifiers;

import Memberships.Membership;

public class ComplexQualifier extends Qualifier{
    private Qualifier q1;
    private Qualifier q2;
    private boolean or;

    public ComplexQualifier(String name, Membership membership, Qualifier q1, Qualifier q2, boolean or) {
        super(name, membership);
        this.q1 = q1;
        this.q2 = q2;
        this.or = or;
    }

    public Qualifier getMembership(double x, double y) {
        if (or)
        {
            if(q1.getMembership().getDegree((float) x) > q2.getMembership().getDegree((float) y)) return q1;
            else return q2;
        }
        else
        {
            if(q1.getMembership().getDegree((float) x) < q2.getMembership().getDegree((float) y)) return q1;
            else return q2;
        }
    }
}
