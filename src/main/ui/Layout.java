package ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Profile;
import model.SingleEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Layout {

    private Map<String, List<EventHandler>> listeners = new HashMap<>();
    protected Profile layoutProfile;
    protected TableView<SingleEntry> table;
    protected ArrayList<TextField> textFields = new ArrayList<>();

    protected void handleEvent(Event e, String eventName) {
        if (this.listeners.get(eventName) != null) {
            for (EventHandler handler : this.listeners.get(eventName)) {
                handler.handle(e);
            }
        }
    }

    public void setLayoutProfile(Profile appProfile) {
        layoutProfile = appProfile;
    }

    public void addEventListener(String eventName, EventHandler listener) {
        if (this.listeners.get(eventName) == null) {
            this.listeners.put(eventName, new ArrayList<>());
        }
        this.listeners.get(eventName).add(listener);
    }

    //EFFECTS: adds user input to table and to database
    //REQUIRES: at least description and meaning are entered
    //MODIFIES: this
    public void addButtonClicked() {
        SingleEntry singleEntry = new SingleEntry();
        singleEntry.setDescription(textFields.get(0).getText());
        singleEntry.setMeaning(textFields.get(1).getText());
        singleEntry.setComment(textFields.get(2).getText());
        singleEntry.setExample(textFields.get(3).getText());
        this.table.getItems().add(singleEntry);
        this.layoutProfile.getDatabase().addEntry(singleEntry);
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    protected abstract Node getNode();
}
