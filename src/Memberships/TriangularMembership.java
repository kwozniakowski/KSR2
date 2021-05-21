package Memberships;

public class TriangularMembership extends Membership {

    public TriangularMembership(String name, float a, float b, float c, float d)
    {
        super(name,a,b,c,d);
    }

    public float getDegree(float x)
    {
        if(x <= a) return 0;
        if(x > a && x < b) return (x-a)/(b-a);
        if(x == b) return 1;
        if(x > c && x < d) return (d-x)/(d-c);
        if(x >= d) return 0;
        else return 0;
    }

    public String getName()
    {
        return this.name;
    }
}

