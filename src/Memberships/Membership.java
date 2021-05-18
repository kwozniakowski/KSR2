package Memberships;

public abstract class Membership {
    protected float a,b,c,d;
    protected String name;

    public Membership(String name,float a, float b, float c, float d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public abstract float getDegree(float x);
}
