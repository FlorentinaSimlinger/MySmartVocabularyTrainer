package model;


import java.util.ArrayList;

//Represents an array list of entries
public class Database {
    public ArrayList<SingleEntry> database;
    protected SingleEntry entry;

    //EFFECTS: constructs an empty database
    public Database() {
        database = new ArrayList<SingleEntry>();
    }

    //EFFECTS: adds an entry to the database
    //MODIFIES: this
    //REQUIRES: entry is not empty, entry does not yet exist
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
    protected SingleEntry searchEntry(String str) {
        for (SingleEntry entry : database) {
            if (entry.getValues().contains(str)) {
                return entry;
            }
        }
        return null;
    }

    //EFFECTS: removes an entry from database based on its description
    //MODIFIES: this
    //REQUIRES: entry is in database, database is not empty, there are no duplicates in any of the fields
    protected void removeEntry(SingleEntry entry) {
        String description = entry.getDescription();
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getDescription().equals(description)) {
                database.remove(database.get(i));
            }
        }
    }

    protected SingleEntry getEntry(int i) {
        return this.database.get(i);
    }
}

