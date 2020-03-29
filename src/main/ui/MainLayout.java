package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.SingleEntry;

import java.util.ArrayList;
import java.util.List;

public class MainLayout extends Layout {
    VBox mainLayout;
    Button button;

    public MainLayout(String labelString, String buttonString1, String buttonString2) {
        super(labelString, buttonString1, buttonString2);
        mainLayout = new VBox();
        label.setMaxWidth(500);
        addUserInputFields();
        button = addButton("Quit");
        mainLayout.getChildren().addAll(button1, button2, button);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);
        button1.setOnMouseClicked(e -> addButtonClicked());
        button2.setOnAction(e -> rootLayout.setCenter(testLayout));
        button.setOnAction(e -> closeProgram());
        addUserInputFields();
        rootLayout.setCenter(mainLayout);
        rootScene = new Scene(rootLayout, 920, 600);
    }


    //EFFECTS: creates fields for user input
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
            textFields.add(textField);
        }
    }
}

//TODO: figure out how to pass different layouts
//TODO: figure out where to call rootLayout
//TODO: figure out how to call closeProgram()
//TODO: call MainLayout with text below as label:
//        mainLabel = new Label("Welcome! To get started, enter a word or phrase you'd like to learn,"
//                + "\nthe synonym you're familiar with, a comment and an example sentence if you'd like.");

