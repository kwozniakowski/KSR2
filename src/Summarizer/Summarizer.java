package Summarizer;

import Attributes.FuzzySet;

public class Summarizer {
    private FuzzySet fuzzySet;
    public Summarizer(FuzzySet fuzzySet)
    {
        this.fuzzySet = fuzzySet;
    }

    public FuzzySet getFuzzySet() {
        return fuzzySet;
    }
}
