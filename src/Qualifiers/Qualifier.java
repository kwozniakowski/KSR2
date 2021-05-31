package Qualifiers;

import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Value;
import Memberships.Membership;

import java.util.ArrayList;

public class Qualifier extends FuzzySet {
    private ArrayList<String> idsOfQualifiedValues;
    private String attribute;
    private String label;
    private FuzzySet fuzzySet;
    private ArrayList<Attribute> attributes;
    private Membership membership;

    public Qualifier(String name, Membership membership)
    {
        super(name, membership);

    }

}
