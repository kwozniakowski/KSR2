package GUI;
import Attributes.FuzzySet;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import Memberships.TriangularMembership;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summaries.SummaryGenerator;

import javax.swing.*;
import java.util.ArrayList;

public class GUI {
    private ArrayList<FuzzySet> fuzzySets;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;

    public GUI(){
        //TODO: całe GUI
        JFrame frame = new JFrame("Podsumowania lingwistyczne");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Izrael bezprawnie okupuje Palestynę a opinia publiczna milczy");
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
        DataParser dataParser = new DataParser();
        fuzzySets = dataParser.parse();
        setQuantifiers();
        Qualifier qualifier = new Qualifier(fuzzySets.get(0),"Topowy");
        for(FuzzySet a : fuzzySets)
        {
            a.setLabels();
        }
        SummaryGenerator summaryGenerator = new SummaryGenerator(fuzzySets,relativeQuantifier, absoluteQuantifier,qualifier);
        summaryGenerator.generateFirstFormSummary();
        summaryGenerator.generateSecondFormSummary();

    }

    public void setQuantifiers()
    {
        ArrayList<Membership> memberships = new ArrayList<>();
        memberships.add(new TrapezoidMembership("Prawie zaden",-1,0, 0.05f, (float) 0.1));
        memberships.add(new TrapezoidMembership("Mniejszosc", 0.05f, 0.1f, 0.2f, 0.25f));
        memberships.add(new TriangularMembership("Okolo 1/4", 0.2f, 0.25f, 0.25f, (float) 0.3));
        memberships.add(new TriangularMembership("Okolo 1/3", 0.25f, 0.33f, 0.33f, (float) 0.41));
        memberships.add(new TrapezoidMembership("Okolo polowy", 0.35f, 0.4f, 0.6f, (float) 0.65));
        memberships.add(new TriangularMembership("Okolo 2/3", 0.59f, 0.67f, 0.67f, (float) 0.75));
        memberships.add(new TriangularMembership("Okolo 3/4", 0.7f, 0.75f, 0.75f, (float) 0.8));
        memberships.add(new TrapezoidMembership("Wiekszosc", 0.75f,0.8f,0.9f,0.95f));
        memberships.add(new TrapezoidMembership("Wiekszosc", 0.9f, 0.95f, 1, (float) 1.2));
        relativeQuantifier = new Quantifier(memberships);

        ArrayList<Membership> absoluteMemberships = new ArrayList<>();
        absoluteMemberships.add(new TrapezoidMembership("Mniej niz 100", -1, 0, 100,110) );
        absoluteMemberships.add(new TriangularMembership("Około 200", 100,200,200,300) );
        absoluteMemberships.add(new TriangularMembership("Około 500", 250,500,500,750) );
        absoluteMemberships.add(new TriangularMembership("Około 1000", 500,1000,1000,1600) );
        absoluteMemberships.add(new TriangularMembership("Około 2000", 1500,2000,2000,2600) );
        absoluteMemberships.add(new TriangularMembership("Około 3000", 2500,3000,3000,3600) );
        absoluteMemberships.add(new TriangularMembership("Około 4000", 3500,4000,4000,4800) );
        absoluteMemberships.add(new TriangularMembership("Około 6000", 4600,6000,6000,7400) );
        absoluteMemberships.add(new TrapezoidMembership("Ponad 8000", 7200, 8000, 15000,16000) );
        absoluteQuantifier = new Quantifier(absoluteMemberships);

    }
}
