package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import model.SingleEntry;

public class TestLayout extends Layout {
    private SingleEntry selected;
    private Label questionLabel;
    private Label feedbackLabel;
    private TextField testInput;

    public TestLayout(String labelString, String buttonString1, String buttonString2) {
        super(labelString, buttonString1, buttonString2);
        VBox testLayout = new VBox(20);
        button1.setOnAction(e -> showTestQuestion());
        questionLabel = addLabel("");
        feedbackLabel = addLabel("");
        testInput = new TextField();
        testInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                showTestFeedback();
                testInput.clear();
            }
        });
        button2.setOnAction(e -> rootLayout.setCenter(mainLayout));
        testLayout.getChildren().addAll(label, button1, questionLabel, testInput,
                feedbackLabel, button2);
    }

    //EFFECTS: sets question label to randomly chosen question
    //MODIFIES: this
    private void showTestQuestion() {
        double random = profile.getDatabase().getRandomFromSumOfFailureRates();
        selected = profile.getDatabase().getEntryBasedOnRandom(random);
        String questionPart = selected.getMeaning();
        String questionBody = "What's another word for ";
        String question = questionBody + questionPart + "?";
        questionLabel.setText(question);
    }

    //EFFECTS: sets feedback label according to user input
    //MODIFIES: this
    private void showTestFeedback() {
        String feedback;
        if (selected.getDescription().equals(testInput.getText())) {
            feedback = "Correct!";
        } else {
            feedback = "Unfortunately that is wrong. The right answer to " + selected.getMeaning() + " is "
                    + selected.getDescription() + ".";
        }
        selected.adjustDistribution(testInput.getText());
        feedbackLabel.setText(feedback);
        showTestQuestion();
    }
}


