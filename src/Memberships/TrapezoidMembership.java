package Memberships;

public class TrapezoidMembership extends Membership {

    public TrapezoidMembership(String name, float a, float b, float c, float d)
    {
        super(name,a,b,c,d);
    }

    public float getDegree(float x)
    {
        if(x <= a) return 0;
        else if(x > a && x < b) return (x-a)/(b-a);
        else if(x >= b && x <= c) return 1;
        else if(x > c && x < d) return (d-x)/(d-c);
        else if(x >= d) return 0;
        else return 0;
    }
    public double getCardinality()
    {
        return ((d-a) + (c - b)) / 2;
    }
    public double getSupport() {return d-a;}

}
