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
    private VBox testLayout;

    public TestLayout() {
        this.testLayout = new VBox(20);

        String labelText = "TEST\n To start testing, press 'Start!'. Hit Enter to get next question. Press"
                + "'Return to main' to return to main page.";
        Label testLabel = new Label(labelText);
        Button testStartButton = new Button("Start!");
        testStartButton.setOnAction(e -> showTestQuestion());
        Button testToMainButton = new Button("Return to main");
        testToMainButton.setOnAction(e -> handleEvent(e, "main"));
        Button testQuitButton = new Button("Quit");
        testQuitButton.setOnAction(e -> handleEvent(e, "quit"));
        this.questionLabel = new Label("");
        this.feedbackLabel = new Label("");
        this.testInput = new TextField();
        this.testInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                showTestFeedback();
                this.testInput.clear();
            }
        });

        this.testLayout.getChildren().addAll(testLabel, testStartButton, this.questionLabel, this.testInput,
                this.feedbackLabel, testToMainButton, testQuitButton);
    }

    //EFFECTS: sets question label to randomly chosen question
    //MODIFIES: this
    private void showTestQuestion() {
        double random = layoutProfile.getDatabase().getRandomFromSumOfFailureRates();
        this.selected = layoutProfile.getDatabase().getEntryBasedOnRandom(random);
        String questionPart = this.selected.getMeaning();
        String questionBody = "What's another word for ";
        String question = questionBody + questionPart + "?";
        this.questionLabel.setText(question);
    }

    //EFFECTS: sets feedback label according to user input
    //MODIFIES: this
    private void showTestFeedback() {
        String feedback;
        if (this.selected.getDescription().equals(this.testInput.getText())) {
            feedback = "Correct!";
        } else {
            feedback = "Unfortunately that is wrong. The right answer to " + this.selected.getMeaning() + " is "
                    + this.selected.getDescription() + ".";
        }
        this.selected.adjustDistribution(this.testInput.getText());
        this.feedbackLabel.setText(feedback);
        showTestQuestion();
    }

    @Override
    protected VBox getNode() {
        return this.testLayout;
    }
}


