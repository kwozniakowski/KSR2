package Memberships;

public class TriangularMembership extends Membership {

    public TriangularMembership(String name, float a, float b, float c)
    {
        super(name,a,b,c);
    }

    public float getDegree(float x)
    {
        if(x <= a) return 0;
        if(x > a && x < b) return (x-a)/(b-a);
        if(x == b) return 1;
        if(x > b && x < c) return (d-x)/(d-c);
        if(x >= c) return 0;
        else return 0;
    }
    public double getCardinality()
    {
        return (c - a) / 2;
    }

    public String getName()
    {
        return this.name;
    }
}

