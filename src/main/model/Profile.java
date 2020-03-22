package model;

import java.util.ArrayList;

//Represents a profile having owner name, overall success rate, database of entries, distribution of entries
public class Profile {
    protected String name;        //profile owner name
    protected int successRate;    //rate of guessing words and expressions correctly
    Database database;            //database of entries


    //REQUIRES: profile name is of non-zero length
    //EFFECTS: constructs a profile and sets initial success rate to 0 and creates empty database
    public Profile() {
        name = "";
        successRate = 0;
        database = new Database();
    }

    public Database getDatabase() {
        return database;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public int getTotalSuccesses() {
        int successes = 0;
        ArrayList<SingleEntry> allEntries = database.getEntries();
        for (SingleEntry entry : allEntries) {
            successes += entry.getSuccesses();
        }
        return successes;
    }

    public int getTotalAttempts() {
        int attempts = 0;
        ArrayList<SingleEntry> allEntries = database.getEntries();
        for (SingleEntry entry : allEntries) {
            attempts += entry.getAttempts();
        }
        return attempts;
    }
}


