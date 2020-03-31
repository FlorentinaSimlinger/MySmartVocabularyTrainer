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

    public TestLayout() {
        VBox testLayout = new VBox(20);

        String labelText = "TEST\n To start testing, press 'Start!'. Hit Enter to get next question. Press"
                + "'Return to main' to return to main page.";
        Label testLabel = new Label(labelText);
        Button testStartButton = new Button("Start!");
        testStartButton.setOnAction(e -> showTestQuestion());
        Button testToMainButton = new Button("Return to main");
        testToMainButton.setOnAction(e -> handleEvent(e, "test"));
        Button testQuitButton = new Button("Quit");
        testQuitButton.setOnAction(e -> handleEvent(e, "quit"));
        questionLabel = new Label("");
        feedbackLabel = new Label("");
        testInput = new TextField();
        testInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                showTestFeedback();
                testInput.clear();
            }
        });

        testLayout.getChildren().addAll(testLabel, testStartButton, questionLabel, testInput,
                feedbackLabel, testQuitButton);
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


