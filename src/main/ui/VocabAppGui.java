package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a VocabApp GUI
public class VocabAppGui {
    private JPanel rootPanel;
    private JLabel nameLabel;
    private JButton button;
    private JLabel greeting;
    private JTextField nameField;

    //EFFECTS: creates a VocabApp GUI
    //SOURCE: parts of code based on https://tutorials.tinyappco.com/Java/SwingGUI
    public VocabAppGui() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String greeting = "Hello " + nameField.getText();
                nameLabel.setText(greeting);
            }
        });
    }

    //EFFECTS: creates JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("VocabAppGui");
        frame.setContentPane(new VocabAppGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
