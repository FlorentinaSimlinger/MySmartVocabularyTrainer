package model;


import java.util.ArrayList;
import java.util.Random;

//Represents a list of all entries
public class Database {
    private ArrayList<SingleEntry> entries;

    //EFFECTS: constructs a database
    public Database() {
        entries = new ArrayList<SingleEntry>();
    }

    //EFFECTS: adds entry to database
    //MODIFIES: this
    //REQUIRES: entry is not empty, entry does not yet exist
    public void addEntry(SingleEntry entry) {
        entries.add(entry);
    }

    //EFFECTS: removes entry from database
    //MODIFIES: this
    //REQUIRES: entry is in database, database is not empty, there are no duplicates
    public void removeEntry(String description) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getDescription().equals(description)) {
                entries.remove(entries.get(i));
                i--;
            }
        }
    }

    //EFFECTS: returns random number from sum of failure rates
    //Sources: partly based on https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
    public double getRandomFromSumOfFailureRates() {
        return new Random().nextInt((int) getSumOfFailureRates());
    }

    //EFFECTS: returns a randomly selected entry based on its failureRate
    public SingleEntry getEntryBasedOnRandom(double random) {
        double num = 0;
        for (SingleEntry e : entries) {
            num += e.getFailureRate();
            if (random <= num) {
                return e;
            }
        }
        return null;
    }


    //EFFECTS: returns an entry based on any value
    public SingleEntry getEntryBasedOnValue(String str) {
        for (SingleEntry entry : entries) {
            if (entry.getValues().contains(str)) {
                return entry;
            }
        }
        return null;
    }

    //EFFECTS: returns the sum of all FailureRates
    public double getSumOfFailureRates() {
        double sum = 0;
        for (SingleEntry e : entries) {
            sum += e.getFailureRate();
        }
        return sum;
    }

    //EFFECTS: returns an entry based on index in entries
    public SingleEntry getEntryBasedOnIndex(int i) {
        return this.entries.get(i);
    }


    //EFFECTS: returns true if entries is empty, false otherwise
    public boolean isEmptyEntries() {
        return entries.isEmpty();
    }

    //EFFECTS: returns the size of the entries list
    public int getSizeEntries() {
        return entries.size();
    }

    //returns the entries
    public ArrayList<SingleEntry> getEntries() {
        return entries;
    }

}

