package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a VocabApp GUI
public class VocabAppGui {
    private JPanel rootPanel;
    private JLabel helloLabel;
    private JLabel welcomeLabel;
    private JButton helloButton;
    private JTextField nameField;

    //EFFECTS: creates a VocabApp GUI
    //SOURCE: parts of code based on https://tutorials.tinyappco.com/Java/SwingGUI
    public VocabAppGui() {
        helloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String greeting = "Hello " + nameField.getText();
                helloLabel.setText(greeting);
            }
        });
    }

    //EFFECTS: creates JFrame
    //SOURCE: parts of code based on https://tutorials.tinyappco.com/Java/SwingGUI
    public static void main(String[] args) {
        JFrame frame = new JFrame("VocabAppGui");
        frame.setContentPane(new VocabAppGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

//Note: the logic should be the same we can call the VocabApp methods from VocabAppGui and jsut change sout to print
// label. The only thing that changes is how user input is fed in and how it is displayed.