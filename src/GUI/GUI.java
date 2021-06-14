package GUI;
import Attributes.Attribute;
import Attributes.FuzzySet;
import Attributes.Match;
import Memberships.Membership;
import Memberships.TrapezoidMembership;
import Memberships.TriangularMembership;
import Qualifiers.Qualifier;
import Quantifiers.Quantifier;
import Summaries.FirstDegreeSummary;
import Summaries.SecondDegreeSummary;
import Summaries.SummaryGenerator;
import Summarizer.Summarizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GUI {
    private ArrayList<Attribute> attributes;
    private ArrayList<Match> matches;
    private ArrayList<ArrayList<Match>> matchesBySurface;
    private ArrayList<Quantifier> relativeQuantifiers;
    private ArrayList<Quantifier> absoluteQuantifiers;
    private JFrame frame;
    private JPanel panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JComboBox qualifierSubjectCB;
    private JComboBox attributesCB;
    private JComboBox summarizerCB;
    private JComboBox qualifierSubjectLabelCB;
    private JComboBox quantifierCB;
    private JComboBox subject1CB;
    private JComboBox subject2CB;
    private JComboBox formCB;
    private JRadioButton generalModeRB;

    public GUI(){

        DataParser dataParser = new DataParser();
        this.attributes = dataParser.parse();
        this.matches = dataParser.getMatches();
        this.matchesBySurface = dataParser.getMatchesBySurface();
        setQuantifiers();


        this.frame = new JFrame();
        this.panel = new JPanel();
        this.button1 = new JButton("Generate 1st form");
        this.button2 = new JButton("Generate 2nd form");
        this.button3 = new JButton("Generate multi subject summary");
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
        panel.add(new JLabel("Wybierz kwantyfikator:"));
        panel.add(quantifierCB);
        panel.add(button1);
        panel.add(new JLabel("Przydatne do podsumowań w drugiej formie:"));
        panel.add(new JLabel("Wybierz atrybut kwalifikujący:"));
        panel.add(qualifierSubjectCB);
        panel.add(new JLabel("Wybierz etykietę atrybutu kwalifikującego:"));
        panel.add(qualifierSubjectLabelCB);
        panel.add(button2);
        panel.add(new JLabel("Przydatne do podsumowań wielopodmiotowych:"));
        panel.add(new JLabel("Wybierz podmiot 1: "));
        panel.add(subject1CB);
        panel.add(new JLabel("Wybierz podmiot 2: "));
        panel.add(subject2CB);
        panel.add(new JLabel("Wybierz forme: "));

        panel.add(formCB);
        panel.add(button3);
        JLabel resultText = new JLabel("");
        panel.add(resultText);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<FirstDegreeSummary> summaries = new ArrayList<>();
                if(generalModeRB.isSelected())
                {
                    for(int j=0; j<attributes.size(); j++)
                    {
                        for(int i=0; i<attributes.get(j).getFuzzySets().size(); i ++)
                        {
                            for(int k=0; k<quantifierCB.getModel().getSize();k++)
                            {
                                Summarizer summarizer = new Summarizer(attributes.get(j).getName(),attributes.get(j).getFuzzySets().get(i).getName(),
                                        chooseMembership(
                                                attributes.get(j).getName(),
                                                attributes.get(j).getFuzzySets().get(i).getName()));

                                Quantifier quantifier = new Quantifier(quantifierCB.getItemAt(k).toString(),
                                        chooseMembership(
                                                quantifierCB.getItemAt(k).toString()),chooseAbsolutity(quantifierCB.getItemAt(k).toString()));

                                Attribute attribute = chooseAttribute(attributes.get(j).getName());
                                //Attribute qualifierAttribute = chooseAttribute(qualifierSubjectCB.getSelectedItem().toString());

                                SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier, null,attribute,summarizer,matches);
                                summaries.add(summaryGenerator.generateFirstFormSummary());
                            }
                        }
                    }
                }
                else
                {
                    Summarizer summarizer = new Summarizer(
                            attributesCB.getSelectedItem().toString(),
                            summarizerCB.getSelectedItem().toString(),
                            chooseMembership(
                                    attributesCB.getSelectedItem().toString(),
                                    summarizerCB.getSelectedItem().toString()));

                    Quantifier quantifier = new Quantifier(quantifierCB.getSelectedItem().toString(),
                            chooseMembership(
                                    quantifierCB.getSelectedItem().toString()),chooseAbsolutity(quantifierCB.getSelectedItem().toString()));

                    Attribute attribute = chooseAttribute(attributesCB.getSelectedItem().toString());
                    Attribute qualifierAttribute = chooseAttribute(qualifierSubjectCB.getSelectedItem().toString());

                    SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier, null, attribute,summarizer,matches);

                    summaryGenerator.generateFirstFormSummary();
                }
                for(FirstDegreeSummary s:summaries)
                {
                    s.calculateMeasures(summaries);
                }
                Collections.sort(summaries);
                for(FirstDegreeSummary s:summaries) System.out.println(s.toString());

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<FirstDegreeSummary> summaries = new ArrayList<>();
                if(generalModeRB.isSelected())
                {
                    for(int j=0; j<attributes.size(); j++)
                    {
                        for(int i=0; i<attributes.get(j).getFuzzySets().size(); i ++)
                        {
                            for(int k=0; k<quantifierCB.getModel().getSize();k++)
                            {
                                for(int l=0; l<attributes.size();l++)
                                {
                                    for(int m=0;m<attributes.get(l).getFuzzySets().size(); m ++)
                                    {
                                        if(attributes.get(l).getName() != attributes.get(j).getName())
                                        {
                                            Qualifier qualifier = new Qualifier(attributes.get(l).getName(), (String) attributes.get(l).getFuzzySets().get(m).getName(),
                                                    chooseMembership(
                                                            attributes.get(l).getName(),
                                                            attributes.get(l).getFuzzySets().get(m).getName()));
                                            Summarizer summarizer = new Summarizer(attributes.get(j).getName(),attributes.get(j).getFuzzySets().get(i).getName(),
                                                    chooseMembership(
                                                            attributes.get(j).getName(),
                                                            attributes.get(j).getFuzzySets().get(i).getName()));

                                            Quantifier quantifier = new Quantifier(quantifierCB.getItemAt(k).toString(),
                                                    chooseMembership(
                                                            quantifierCB.getItemAt(k).toString()),chooseAbsolutity(quantifierCB.getItemAt(k).toString()));

                                            Attribute attribute = chooseAttribute(attributes.get(j).getName());
                                            //Attribute qualifierAttribute = chooseAttribute(qualifierSubjectCB.getSelectedItem().toString());
                                            SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier,qualifier,attribute,summarizer,matches);

                                            summaries.add(summaryGenerator.generateSecondFormSummary());
                                        }

                                    }
                                }

                            }
                        }
                    }
                }
                else
                {
                    Qualifier qualifier = new Qualifier(
                            attributesCB.getSelectedItem().toString(),
                            (String) qualifierSubjectCB.getSelectedItem().toString(),
                            chooseMembership(
                                    qualifierSubjectCB.getSelectedItem().toString(),
                                    qualifierSubjectLabelCB.getSelectedItem().toString()));
                    Summarizer summarizer = new Summarizer(
                            attributesCB.getSelectedItem().toString(),
                            summarizerCB.getSelectedItem().toString(),
                            chooseMembership(
                                    attributesCB.getSelectedItem().toString(),
                                    summarizerCB.getSelectedItem().toString()));

                    Quantifier quantifier = new Quantifier(quantifierCB.getSelectedItem().toString(),
                            chooseMembership(
                                    quantifierCB.getSelectedItem().toString()), chooseAbsolutity(quantifierCB.getSelectedItem().toString()));

                    Attribute attribute = chooseAttribute(attributesCB.getSelectedItem().toString());
                    SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier,qualifier,attribute,summarizer,matches);

                    summaries.add(summaryGenerator.generateSecondFormSummary());
                }
                for(FirstDegreeSummary s:summaries)
                {
                    s.calculateMeasures(summaries);
                }
                Collections.sort(summaries);
                for(FirstDegreeSummary s:summaries)
                {
                    System.out.println(s.toString());
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<SecondDegreeSummary> summaries = new ArrayList<>();
                if(generalModeRB.isSelected())
                {
                    if(Integer.parseInt(formCB.getSelectedItem().toString()) == 4)
                    {
                        for(int j=0; j<attributes.size(); j++) {
                            for (int i = 0; i < attributes.get(j).getFuzzySets().size(); i++) {
                                for (ArrayList<Match> a : matchesBySurface) {
                                    for (ArrayList<Match> b : matchesBySurface) {
                                        if (a.get(0).getSurface() != b.get(0).getSurface()) {
                                            Summarizer summarizer = new Summarizer(attributes.get(j).getName(), attributes.get(j).getFuzzySets().get(i).getName(),
                                                    chooseMembership(
                                                            attributes.get(j).getName(),
                                                            attributes.get(j).getFuzzySets().get(i).getName()));


                                            Attribute attribute = chooseAttribute(attributes.get(j).getName());
                                            ArrayList<Match> matches1 = a;
                                            ArrayList<Match> matches2 = b;
                                            int form = Integer.parseInt(formCB.getSelectedItem().toString());
                                            SummaryGenerator summaryGenerator = new SummaryGenerator(null, null, attribute,
                                                    summarizer, matches1, matches2, 4);
                                            summaries.add(summaryGenerator.generateSecondDegreeSummary());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        for(int j=0; j<attributes.size(); j++)
                        {
                            for(int i=0; i<attributes.get(j).getFuzzySets().size(); i ++)
                            {
                                for(int k=0; k<relativeQuantifiers.size() ;k++)
                                {
                                    Quantifier quantifier = relativeQuantifiers.get(k);
                                    for(int l=0; l<attributes.size();l++)
                                    {
                                        for(int m=0;m<attributes.get(l).getFuzzySets().size(); m ++)
                                        {
                                            for(ArrayList<Match> a: matchesBySurface)
                                            {
                                                for(ArrayList<Match> b : matchesBySurface)
                                                {
                                                    if(a.get(0).getSurface() != b.get(0).getSurface())
                                                    {
                                                        Qualifier qualifier = new Qualifier(attributes.get(l).getName(), (String) attributes.get(l).getFuzzySets().get(m).getName(),
                                                                chooseMembership(
                                                                        attributes.get(l).getName(),
                                                                        attributes.get(l).getFuzzySets().get(m).getName()));
                                                        Summarizer summarizer = new Summarizer(attributes.get(j).getName(),attributes.get(j).getFuzzySets().get(i).getName(),
                                                                chooseMembership(
                                                                        attributes.get(j).getName(),
                                                                        attributes.get(j).getFuzzySets().get(i).getName()));


                                                        Attribute attribute = chooseAttribute(attributes.get(j).getName());
                                                        ArrayList<Match> matches1 = a;
                                                        ArrayList<Match> matches2 = b;
                                                        int form = Integer.parseInt(formCB.getSelectedItem().toString());
                                                        if(form == 1)
                                                        {
                                                            SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier,qualifier,attribute,
                                                                    summarizer,matches1,matches2,form);
                                                            summaries.add(summaryGenerator.generateSecondDegreeSummary());
                                                        }
                                                        else if(form == 2 || form == 3)
                                                        {
                                                            SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier,qualifier,attribute,
                                                                    summarizer,matches1,matches2,form);
                                                            summaries.add(summaryGenerator.generateSecondDegreeSummary());
                                                        }
                                                        else
                                                        {
                                                            SummaryGenerator summaryGenerator = new SummaryGenerator(null,qualifier,attribute,
                                                                    summarizer,matches1,matches2,form);
                                                            summaries.add(summaryGenerator.generateSecondDegreeSummary());
                                                        }
                                                    }


                                                }

                                            }


                                        }
                                    }

                                }
                            }
                        }
                    }

                }
                else
                {
                    Qualifier qualifier = new Qualifier(
                            attributesCB.getSelectedItem().toString(),
                            (String) qualifierSubjectCB.getSelectedItem().toString(),
                            chooseMembership(
                                    qualifierSubjectCB.getSelectedItem().toString(),
                                    qualifierSubjectLabelCB.getSelectedItem().toString()));
                    Summarizer summarizer = new Summarizer(
                            attributesCB.getSelectedItem().toString(),
                            summarizerCB.getSelectedItem().toString(),
                            chooseMembership(
                                    attributesCB.getSelectedItem().toString(),
                                    summarizerCB.getSelectedItem().toString()));

                    Quantifier quantifier = new Quantifier(quantifierCB.getSelectedItem().toString(),
                            chooseMembership(
                                    quantifierCB.getSelectedItem().toString()), chooseAbsolutity(quantifierCB.getSelectedItem().toString()));
                    ArrayList<Match> matches1 = chooseSubject1();
                    ArrayList<Match> matches2 = chooseSubject2();
                    int form = Integer.parseInt(formCB.getSelectedItem().toString());
                    Attribute attribute = chooseAttribute(attributesCB.getSelectedItem().toString());
                    SummaryGenerator summaryGenerator = new SummaryGenerator(quantifier,qualifier,attribute,
                            summarizer,matches1,matches2,form);
                    summaries.add(summaryGenerator.generateSecondDegreeSummary());

                }
                for(SecondDegreeSummary s:summaries)
                {
                    s.calculateMeasures();
                }
                Collections.sort(summaries);
                for(SecondDegreeSummary s:summaries)
                {
                    System.out.println(s.toString());
                }

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
        String QuantList[] = new String[18];
        int i = 0;
        ArrayList<String> NameList = new ArrayList<>();
        for(ArrayList<Match> a: matchesBySurface) NameList.add(a.get(0).getSurface());


        i=0;
        for(Attribute a: attributes)
        {
            FSList[i] = a.getName();
            QList[i] = a.getName();
            i = i+1;
        }
        FSList[10] = "Brak";
        QList[10] = "Brak";

        i = 0;
        for(Quantifier q: relativeQuantifiers)
        {
            QuantList[i] = q.getName();
            i = i+1;
        }
        for(Quantifier q: absoluteQuantifiers)
        {
            QuantList[i] = q.getName();
            i = i+1;
        }


        attributesCB = new JComboBox(FSList);
        summarizerCB = new JComboBox(new String[]{" "});
        qualifierSubjectCB = new JComboBox(QList);
        qualifierSubjectLabelCB = new JComboBox();
        quantifierCB = new JComboBox(QuantList);
        subject1CB = new JComboBox(NameList.toArray());
        subject2CB = new JComboBox(NameList.toArray());
        Integer[] integers = {1,2,3,4};
        formCB = new JComboBox(integers);

    }

    public void setQuantifiers()
    {
        relativeQuantifiers = new ArrayList<>();
        relativeQuantifiers.add(new Quantifier( "Prawie zaden",new TrapezoidMembership("Prawie zaden",-1,0, 0.05f, (float) 0.1), false));
        relativeQuantifiers.add(new Quantifier( "Mniejszosc",new TrapezoidMembership("Mniejszosc", 0.05f, 0.1f, 0.2f, 0.25f),false));
        relativeQuantifiers.add(new Quantifier( "Okolo 1/4", new TriangularMembership("Okolo 1/4", 0.2f, 0.25f,  (float) 0.3),false));
        relativeQuantifiers.add(new Quantifier( "Okolo 1/3", new TriangularMembership("Okolo 1/3", 0.25f, 0.33f,  (float) 0.41),false));
        relativeQuantifiers.add(new Quantifier( "Okolo polowy",new TrapezoidMembership("Okolo polowy", 0.35f, 0.4f, 0.6f, (float) 0.65),false));
        relativeQuantifiers.add(new Quantifier( "Okolo 2/3", new TriangularMembership("Okolo 2/3", 0.59f, 0.67f,  (float) 0.75),false));
        relativeQuantifiers.add(new Quantifier( "Okolo 3/4",new TriangularMembership("Okolo 3/4", 0.7f, 0.75f,  (float) 0.8),false));
        relativeQuantifiers.add(new Quantifier( "Wiekszosc",new TrapezoidMembership("Wiekszosc", 0.75f,0.8f,0.9f,0.95f),false));
        relativeQuantifiers.add(new Quantifier( "Prawie wszystkie",new TrapezoidMembership("Prawie wszystkie", 0.9f, 0.95f, 1, (float) 1.2),false));

        absoluteQuantifiers = new ArrayList<>();
        absoluteQuantifiers.add(new Quantifier( "Mniej niz 100",new TrapezoidMembership("Mniej niz 100", -1, 0, 100,110) ,true));
        absoluteQuantifiers.add(new Quantifier( "Okolo 200",new TriangularMembership("Okolo 200", 100,200,300) ,true ));
        absoluteQuantifiers.add(new Quantifier( "Okolo 500",new TriangularMembership("Okolo 500", 250,500,750) ,true ));
        absoluteQuantifiers.add(new Quantifier( "Okolo 1000",new TriangularMembership("Okolo 1000", 500,1000,1600)  ,true));
        absoluteQuantifiers.add(new Quantifier( "Okolo 2000",new TriangularMembership("Okolo 2000", 1500,2000,2600) ,true ));
        absoluteQuantifiers.add(new Quantifier( "Okolo 3000",new TriangularMembership("Okolo 3000", 2500,3000,3600)  ,true));
        absoluteQuantifiers.add(new Quantifier( "Okolo 4000",new TriangularMembership("Okolo 4000", 3500,4000,4800) ,true ));
        absoluteQuantifiers.add(new Quantifier( "Okolo 6000",new TriangularMembership("Okolo 6000", 4600,6000,7400) ,true ));
        absoluteQuantifiers.add(new Quantifier( "Ponad 8000",new TrapezoidMembership("Ponad 8000", 7200, 8000, 15000,16000)  ,true));

    }

    public Membership chooseMembership(String a,String s)
    {
        for(Attribute attribute: attributes)
        {
            if(a.equals(attribute.getName()))
            {
                for(FuzzySet f: attribute.getFuzzySets())
                {
                    if(f.getName().equals(s))
                    {
                        return f.getMembership();
                    }
                }
            }
        }
        return null;
    }
    public Membership chooseMembership(String s)
    {
        for(Quantifier q: relativeQuantifiers)
        {
            if(q.getName().equals(s))
            {
                return q.getMembership();
            }
        }
        for(Quantifier q: absoluteQuantifiers)
        {
            if(q.getName().equals(s))
            {
                return q.getMembership();
            }
        }
        return null;
    }


    public Attribute chooseAttribute(String a)
    {
        for(Attribute attribute: attributes)
        {
            if(a.equals(attribute.getName()))
            {
                return attribute;
            }
        }
        return null;
    }
    public boolean chooseAbsolutity(String s)
    {
        for(Quantifier q: relativeQuantifiers)
        {
            if(q.getName().equals(s))
            {
                return q.absolute;
            }
        }
        for(Quantifier q: absoluteQuantifiers)
        {
            if(q.getName().equals(s))
            {
                return q.absolute;
            }
        }
        return false;
    }

    public ArrayList<Match> chooseSubject1()
    {
        for(ArrayList<Match> a: matchesBySurface)
        {
            if(a.get(0).getSurface().equals(subject1CB.getSelectedItem().toString()))
            {
                return a;
            }
        }
        return matchesBySurface.get(0);
    }

    public ArrayList<Match> chooseSubject2()
    {
        for(ArrayList<Match> a: matchesBySurface)
        {
            if(a.get(0).getSurface().equals(subject2CB.getSelectedItem().toString()))
            {
                return a;
            }
        }
        return matchesBySurface.get(0);
    }
}
