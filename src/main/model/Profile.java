package model;

import java.util.ArrayList;

//Represents a profile having owner name, overall success rate, database of entries, distribution of entries
public class Profile {
    protected String name;        //profile owner name
    protected ArrayList<Double> successRates;    //rates of guessing words and expressions correctly
    protected int successes;
    protected int attempts;
    Database database;            //database of entries


    //REQUIRES: profile name is of non-zero length
    //EFFECTS: constructs a profile and sets initial success rate to 0 and creates empty database
    public Profile() {
        name = "";
        successRates = new ArrayList<>();
        successRates.add(0.0);
        successes = 0;
        attempts = 0;
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

    public ArrayList<Double> getSuccessRates() {
        return successRates;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    //EFFECTS: adds success rate to list of success rates
    //MODIFIES: this
    public void addSuccessRateOfSession() {
        successes = getTotalSuccesses();
        attempts = getTotalAttempts();
        Double successRateDouble = 100.0 * successes / attempts;
        successRates.add(successRateDouble);
    }

    //EFFECTS: return the successes of all entries
    public int getTotalSuccesses() {
        successes = 0;
        ArrayList<SingleEntry> allEntries = database.getEntries();
        for (SingleEntry entry : allEntries) {
            successes += entry.getSuccesses();
        }
        return successes;
    }

    //EFFECTS: return the attempts of all entries
    public int getTotalAttempts() {
        attempts = 0;
        ArrayList<SingleEntry> allEntries = database.getEntries();
        for (SingleEntry entry : allEntries) {
            attempts += entry.getAttempts();
        }
        return attempts;
    }
}


