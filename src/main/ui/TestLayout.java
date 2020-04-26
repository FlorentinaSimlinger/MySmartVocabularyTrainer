package ui;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.SingleEntry;

//represents a test layout where user can test himself/herself
public class TestLayout extends Layout {
    private SingleEntry selected;
    private Label questionLabel;
    private Label feedbackLabel;
    private TextField testInput;
    private VBox testLayout;
    public static final String EVENT_MAIN = "main";
    public static final String EVENT_QUIT = "quit";
    public static final String EVENT_SHOWTESTQUESTION = "show test question";
    public static final String EVENT_SHOWTESTFEEDBACK = "show test feedback";

    //EFFECTS: constructs a test layout
    public TestLayout() {
        this.testLayout = new VBox(20);

        String labelText = "TEST\n To start testing, press 'Start!'. Hit Enter to get next question. Press"
                + "'Return to main' to return to main page.";
        Label testLabel = new Label(labelText);
        Button testStartButton = new Button("Start!");
        testStartButton.setOnAction(e -> dispatchEvent(e, EVENT_SHOWTESTQUESTION));
        Button testToMainButton = new Button("Return to main");
        testToMainButton.setOnAction(e -> dispatchEvent(e, EVENT_MAIN));
        Button testQuitButton = new Button("Quit");
        testQuitButton.setOnAction(e -> dispatchEvent(e, EVENT_QUIT));
        this.questionLabel = new Label("");
        this.feedbackLabel = new Label("");
        this.testInput = new TextField();
        this.testInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                dispatchEvent(e, EVENT_SHOWTESTFEEDBACK);
                this.testInput.clear();
            }
        });

        this.testLayout.getChildren().addAll(testLabel, testStartButton, this.questionLabel, this.testInput,
                this.feedbackLabel, testToMainButton, testQuitButton);
    }

    //EFFECTS: sets question label to randomly chosen question
    //MODIFIES: this
    public void showTestQuestion(String questionPart) {
        String questionBody = "What's another word for ";
        String question = questionBody + questionPart + "?";
        this.questionLabel.setText(question);
    }

    //EFFECTS: sets feedback label according to user input
    //MODIFIES: this
    public void showTestFeedback(boolean correct, String selectedMeaning, String selectedDescription) {
        String feedback;
        if (correct) {
            feedback = "Correct!";
        } else {
            feedback = "Unfortunately that is wrong. The right answer to " + selectedMeaning + " is "
                    + selectedDescription + ".";
        }
        this.feedbackLabel.setText(feedback);
        dispatchEvent(new Event(MouseEvent.MOUSE_PRESSED), EVENT_SHOWTESTQUESTION);
    }

    @Override
    protected VBox getNode() {
        return this.testLayout;
    }

    public TextField getTestInput() {
        return this.testInput;
    }
}


