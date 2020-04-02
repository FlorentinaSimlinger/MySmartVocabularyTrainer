package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

//represents the main layout
public class MainLayout extends Layout {
    VBox mainLayout;
    public static final String EVENT_TEST = "test";
    public static final String EVENT_QUIT = "quit";
    public static final String EVENT_ADD = "add";
    private ArrayList<TextField> textFields = new ArrayList<>();

    //constructs a main layout
    public MainLayout() {
        this.mainLayout = new VBox();
        this.mainLayout.setAlignment(Pos.CENTER);
        this.mainLayout.setSpacing(20);

        //label
        String labelText = "Welcome! To get started, enter a word or phrase you'd like to learn,"
                + "\nthe synonym you're familiar with, a comment and an example sentence if you'd like.";
        Label mainLabel = new Label(labelText);
        mainLabel.setMaxWidth(500);
        this.mainLayout.getChildren().add(mainLabel);

        //user input
        this.addUserInputFields();

        //buttons
        Button addButton = new Button("Add");
        Button testButton = new Button("Test myself");
        Button quitButton = new Button("Quit");

        this.mainLayout.getChildren().addAll(addButton, testButton, quitButton);

        addButton.setOnMouseClicked(e -> dispatchEvent(e, EVENT_ADD));
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
            mainLayout.getChildren().add(textField);
            if (textFields.size() < 4) {
                textFields.add(textField);
            }
        }
    }

    public VBox getNode() {
        return this.mainLayout;
    }

    public ArrayList<TextField> getTextFields() {
        return this.textFields;
    }

    public void clearTextFields() {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

}



