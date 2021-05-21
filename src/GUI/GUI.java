package GUI;
import Attributes.Attribute;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import Memberships.TriangularMembership;
import Quantifiers.Quantifier;
import Summaries.SummaryGenerator;
import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GUI {
    private ArrayList<Attribute> attributes;
    private Quantifier relativeQuantifier;

    public GUI(){
        //TODO: całe GUI
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Izrael bezprawnie okupuje Palestynę a opinia publiczna milczy");
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
        DataParser dataParser = new DataParser();
        attributes = dataParser.parse();
        setQuantifier();
        for(Attribute a : attributes)
        {
            a.setLabels();
        }
        SummaryGenerator summaryGenerator = new SummaryGenerator(attributes,relativeQuantifier);
        summaryGenerator.generate();

    }

    public void setQuantifier()
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
    }
}
