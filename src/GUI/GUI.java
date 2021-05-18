package GUI;
import javax.swing.*;
public class GUI {

    public GUI(){
        //TODO: całe GUI
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JButton button = new JButton("Izrael bezprawnie okupuje Palestynę a opinia publiczna milczy");
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}
