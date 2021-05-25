package GUI;
import Attributes.Attribute;
import Attributes.FuzzySet;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import Memberships.TriangularMembership;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summaries.SummaryGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private ArrayList<Attribute> attributes;
    private Quantifier relativeQuantifier;
    private Quantifier absoluteQuantifier;
    private JFrame frame;
    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JComboBox qualifierSubjectCB;
    private JComboBox attributesCB;
    private JComboBox summarizerCB;
    private JComboBox qualifierSubjectLabelCB;
    private JRadioButton generalModeRB;

    public GUI(){

        DataParser dataParser = new DataParser();
        this.attributes = dataParser.parse();
        setQuantifiers();
        this.frame = new JFrame();
        this.panel = new JPanel();
        this.button1 = new JButton("Generate 1st form");
        this.button2 = new JButton("Generate 2nd form");
        this.generalModeRB = new JRadioButton();
        setComboBoxes();

        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new GridLayout(14,1)); // rows and cols

        panel.add(new JLabel("Czy chcesz generalne podsumowanie?"));
        panel.add(generalModeRB);
        panel.add(new JLabel("Wybierz atrybut do podsumowania:"));
        panel.add(attributesCB);
        panel.add(new JLabel("Wybierz sumaryzator:"));
        panel.add(summarizerCB);
        panel.add(button1);
        panel.add(new JLabel("Przydatne do podsumowań w drugiej formie:"));
        panel.add(new JLabel("Wybierz atrybut kwalifikujący:"));
        panel.add(qualifierSubjectCB);
        panel.add(new JLabel("Wybierz etykietę atrybutu kwalifikującego:"));
        panel.add(qualifierSubjectLabelCB);
        panel.add(button2);
        JLabel resultText = new JLabel("");
        panel.add(resultText);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attribute = attributesCB.getSelectedItem().toString();
                String summarizer = summarizerCB.getSelectedItem().toString();
                SummaryGenerator summaryGenerator = new SummaryGenerator(attribute, summarizer, generalModeRB.isSelected(),
                        attributes, relativeQuantifier, absoluteQuantifier);
                ArrayList<String> results1 = summaryGenerator.generateFirstFormSummary();
                String result = "";
                for(String s: results1) result = result + s + "\n";
                resultText.setText(result);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Qualifier qualifier = new Qualifier((String) qualifierSubjectCB.getSelectedItem().toString(),
                        (String) qualifierSubjectLabelCB.getSelectedItem().toString(), attributes);
                String attribute = attributesCB.getSelectedItem().toString();
                String summarizer = summarizerCB.getSelectedItem().toString();
                SummaryGenerator summaryGenerator = new SummaryGenerator(attribute, summarizer, generalModeRB.isSelected(),
                        attributes, relativeQuantifier, absoluteQuantifier);
                ArrayList<String> results2 = summaryGenerator.generateSecondFormSummary(qualifier);
                String result = "";
                for(String s: results2) result = result + s + "\n";
                resultText.setText(result);
            }
        });

        attributesCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> summarizatorList = new ArrayList<>();
                if(attributesCB.getSelectedItem().toString() != "Brak")
                {
                    Attribute chosenAttribute = attributes.get(attributesCB.getSelectedIndex());
                    for(FuzzySet f: chosenAttribute.getFuzzySets())
                    {
                        summarizatorList.add(f.getName());
                    }
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
                    for(String s: summarizatorList)
                    {
                        model.addElement(s);
                    }
                    model.addElement("Brak");
                    summarizerCB.setModel(model);
                }


            }
        });

        qualifierSubjectCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> summarizatorList = new ArrayList<>();
                if(!qualifierSubjectCB.getSelectedItem().equals("Brak"))
                {
                    Attribute chosenSet = attributes.get(qualifierSubjectCB.getSelectedIndex());
                    for(FuzzySet f: chosenSet.getFuzzySets())
                    {
                        summarizatorList.add(f.getName());
                    }
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
                    for(String s: summarizatorList)
                    {
                        model.addElement(s);
                    }
                    qualifierSubjectLabelCB.setModel(model);
                }

            }
        });


    }


    public void setComboBoxes()
    {
        String FSList[] = new String[11];
        String QList[] = new String[11];
        int i = 0;


        i=0;
        for(Attribute a: attributes)
        {
            FSList[i] = a.getName();
            QList[i] = a.getName();
            i = i+1;
        }
        FSList[10] = "Brak";
        QList[10] = "Brak";

        attributesCB = new JComboBox(FSList);
        summarizerCB = new JComboBox(new String[]{" "});
        qualifierSubjectCB = new JComboBox(QList);
        qualifierSubjectLabelCB = new JComboBox();

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
        memberships.add(new TrapezoidMembership("Prawie wszystkie", 0.9f, 0.95f, 1, (float) 1.2));
        relativeQuantifier = new Quantifier(memberships);

        ArrayList<Membership> absoluteMemberships = new ArrayList<>();
        absoluteMemberships.add(new TrapezoidMembership("Mniej niz 100", -1, 0, 100,110) );
        absoluteMemberships.add(new TriangularMembership("Okolo 200", 100,200,200,300) );
        absoluteMemberships.add(new TriangularMembership("Okolo 500", 250,500,500,750) );
        absoluteMemberships.add(new TriangularMembership("Okolo 1000", 500,1000,1000,1600) );
        absoluteMemberships.add(new TriangularMembership("Okolo 2000", 1500,2000,2000,2600) );
        absoluteMemberships.add(new TriangularMembership("Okolo 3000", 2500,3000,3000,3600) );
        absoluteMemberships.add(new TriangularMembership("Okolo 4000", 3500,4000,4000,4800) );
        absoluteMemberships.add(new TriangularMembership("Okolo 6000", 4600,6000,6000,7400) );
        absoluteMemberships.add(new TrapezoidMembership("Ponad 8000", 7200, 8000, 15000,16000) );
        absoluteQuantifier = new Quantifier(absoluteMemberships);

    }
}
