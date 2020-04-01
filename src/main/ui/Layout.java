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

//represents an abstract layout class
public abstract class Layout {
    public static Profile profile;
    private Map<String, List<EventHandler>> listeners = new HashMap<>();
    //protected Profile profile;
    protected TableView<SingleEntry> table;
    protected ArrayList<TextField> textFields = new ArrayList<>();

    //EFFECTS: sets the static field profile
    public static void setProfile(Profile profile) {
        Layout.profile = profile;
    }

    //EFFECTS: goes through all EventHandlers and handles events
    protected void handleEvent(Event e, String eventName) {
        if (this.listeners.get(eventName) != null) {
            for (EventHandler handler : this.listeners.get(eventName)) {
                handler.handle(e);
            }
        }
    }

    //EFFECTS: adds an event listener to list of event listeners
    //MODIFIES: this
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
        profile.getDatabase().addEntry(singleEntry);
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    protected abstract Node getNode();
}

//TODO: do you do this. notation even if there is no constructor and therefore no object?
//TODO: does the handleEvent method modify anything?