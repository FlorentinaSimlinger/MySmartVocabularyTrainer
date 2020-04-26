package ui;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

//represents the main layout
public class MainLayout extends Layout {
    private VBox mainLayout;
    public static final String EVENT_TEST = "test";
    public static final String EVENT_QUIT = "quit";
    public static final String EVENT_ADD = "add";
    private ArrayList<TextField> textFields = new ArrayList<>();
    private Label addFeedbackLabel;
    private Button addButton;

    //constructs a main layout
    public MainLayout() {
        this.mainLayout = new VBox();
        this.mainLayout.setAlignment(Pos.CENTER);
        this.mainLayout.setSpacing(20);

        //labels
        String labelText = "Welcome! To get started, enter a word or phrase you'd like to learn,"
                + "\nthe synonym you're familiar with, a comment and an example sentence if you'd like.";
        Label mainLabel = new Label(labelText);
        mainLabel.setMaxWidth(500);
        this.addFeedbackLabel = new Label("");
        this.mainLayout.getChildren().add(mainLabel);

        //user input
        this.addUserInputFields();

        //buttons
        this.addButton = new Button("Add");
        Button testButton = new Button("Test myself");
        Button quitButton = new Button("Quit");

        //bindings for text fields
        this.addBinding();

        this.mainLayout.getChildren().addAll(this.addFeedbackLabel, this.addButton, testButton, quitButton);

        this.addButton.setOnMouseClicked(e -> dispatchEvent(e, EVENT_ADD));
        testButton.setOnAction(e -> dispatchEvent(e, EVENT_TEST));
        quitButton.setOnAction(e -> dispatchEvent(e, EVENT_QUIT));
    }



    //EFFECTS: creates fields for user input
    //MODIFIES: this
    public void addUserInputFields() {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("description");
        fieldNames.add("meaning");
        fieldNames.add("comment");
        fieldNames.add("example sentence");
        for (String fieldName : fieldNames) {
            TextField textField = new TextField();
            textField.setPromptText(fieldName);
            textField.setMaxWidth(500);
            this.mainLayout.getChildren().add(textField);
            if (this.textFields.size() < 4) {
                this.textFields.add(textField);
            }
        }
    }

    //EFFECTS: disables add button so long fields 1 and 2 are not filled out
    //MODIFIES: this
    //SOURCE: https://stackoverflow.com/questions/23040531/how-to-disable-button-when-textfield-is-empty
    private void addBinding() {
        BooleanBinding booleanBinding = new BooleanBinding() {
            {
                super.bind(textFields.get(0).textProperty(),
                        textFields.get(1).textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (textFields.get(0).getText().isEmpty()
                        || textFields.get(1).getText().isEmpty());
            }
        };
        this.addButton.disableProperty().bind(booleanBinding);
    }


    public VBox getNode() {
        return this.mainLayout;
    }

    public ArrayList<TextField> getTextFields() {
        return this.textFields;
    }

    //EFFECTS: clears text fields
    public void clearTextFields() {
        for (TextField textField : this.textFields) {
            textField.clear();
        }
    }

}



