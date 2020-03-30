package ui;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Profile;
import model.SingleEntry;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Layout {
    protected Label label;
    protected Button button1;
    protected Button button2;
    protected Profile profile;
    protected TableView<SingleEntry> table;
    protected ArrayList<TextField> textFields = new ArrayList<>();
    protected Stage window;
    protected Scene loginScene;

    //für verschiedene layouts: abstract class machen mit subclasses HBOX, VBOX etc. ODER Type als Feld
    // wenn nested: array von layouts einfügen

    //profileLayout extends Layout

    public Layout(String labelString, String buttonString1, String buttonString2) {
        label = new Label(labelString);
        button1 = new Button(buttonString1);
        button2 = new Button(buttonString2);
    }

    public void addEventListener(String eventName, ActionListener listener){

    }

    public Button addButton(String buttonString) {
        return new Button(buttonString);
    }

    public Label addLabel(String labelString) {
        return new Label(labelString);
    }

    //public void onSearch(...) {
        //event on mouse click
    //}


    //EFFECTS: adds user input to table and to database
    //REQUIRES: at least description and meaning are entered
    //MODIFIES: this
    public void addButtonClicked() {
        SingleEntry singleEntry = new SingleEntry();
        singleEntry.setDescription(textFields.get(0).getText());
        singleEntry.setMeaning(textFields.get(1).getText());
        singleEntry.setComment(textFields.get(2).getText());
        singleEntry.setExample(textFields.get(3).getText());
        table.getItems().add(singleEntry);
        profile.getDatabase().addEntry(singleEntry);
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    public abstract void addEventHandler(EventType<MouseEvent> mousePressed,
                                         EventHandler<MouseEvent> mouseEventEventHandler);
}
