package ui;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.SingleEntry;

import java.awt.event.ActionEvent;
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
        //button2.setOnAction(e -> rootLayout.setCenter(testLayout));
        button.setOnAction(e -> closeProgram());
        addUserInputFields();
        rootLayout.setCenter(mainLayout);
        rootScene = new Scene(rootLayout, 920, 600);
        button2.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                VocabAppGuiFX.changeToTestLayout();
            }
        });
        button2.setOnAction(VocabAppGuiFX);
        button2.addEventHandler(MouseEvent.MOUSE_PRESSED, new ButtonHandler());
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

    @Override
    public void addEventListener(String eventName) {

    }

    @Override
    public void addEventHandler(EventType<MouseEvent> mousePressed, EventHandler<MouseEvent> mouseEventEventHandler) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    public int giveFeedbackWhatToDo(String eventName) {
        if (eventName.equals("addButtonClicked")) {
            addButtonClicked();
            return 0;
        } else if (eventName.equals("testButtonClicked")) {
            return 1;
        } else if (eventName.equals("quitButtonClicked")) {
            return 2;
        }
        return 0;
    }



    //sees that an event comes in, checks what is applicable and sends back info what to do
    //this class knows about the buttons, the main class knows about the layouts. Therefore,
    // main class adds eventListeners
}

//TODO: figure out how to pass different layouts
//TODO: figure out where to call rootLayout
//TODO: figure out how to call closeProgram()


