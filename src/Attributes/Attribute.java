package Attributes;

import Memberships.TrapezoidMembership;

import java.util.ArrayList;

public class Attribute {
    private String name;
    private ArrayList<TrapezoidMembership> trapezoidMemberships;

    public Attribute(String name, ArrayList<TrapezoidMembership> trapezoidMemberships)
    {
        this.trapezoidMemberships = trapezoidMemberships;
        this.name = name;
    }
}
