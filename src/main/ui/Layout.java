package ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//represents an abstract layout class
public abstract class Layout {
    private Map<String, List<EventHandler>> listeners = new HashMap<>();

    //EFFECTS: goes through all EventHandlers and handles events
    protected void dispatchEvent(Event e, String eventName) {
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

    protected abstract Node getNode();
}

//TODO: does the handleEvent method modify anything?