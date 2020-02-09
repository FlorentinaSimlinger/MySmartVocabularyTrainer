package model;

import java.util.ArrayList;

//Represents an array list of entries
public class Database {
    protected ArrayList<SingleEntry> database;
    protected SingleEntry entry;

    //EFFECTS: constructs an empty database
    public Database() {
        database = new ArrayList<SingleEntry>();
    }

    //EFFECTS: adds an entry to the database
    //MODIFIES: this
    //REQUIRES: entry is not empty
    protected void addEntry(SingleEntry entry) {
        database.add(entry);
    }

    //EFFECTS: returns true if database is empty, false otherwise
    protected boolean isEmpty() {
        return database.isEmpty();
    }

    protected int getSize() {
        return database.size();
    }

    //EFFECTS: returns an entry from database based on any value
    protected SingleEntry getEntry(String str) {
        for (SingleEntry entry : database) {
            if (entry.getValues().contains(str)) {
                return entry;
            }
        }
        return null;
    }

    //EFFECTS: removes an entry from database
    //MODIFIES: this
    //REQUIRES: entry is not empty
    protected void removeEntry(SingleEntry entry) {
        //##
    }
}

