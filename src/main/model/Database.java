package model;


import java.util.ArrayList;
import java.util.Random;

//Represents an list of all entries and its distribution to be selected
public class Database {
    private ArrayList<SingleEntry> entries;
    private ArrayList<String> distribution;
    public static final int TIMES = 3;


    //EFFECTS: constructs an empty database
    public Database() {
        entries = new ArrayList<SingleEntry>();
        distribution = new ArrayList<>();
    }

    //EFFECTS: adds entry to database and its description TIMES times to distribution
    //MODIFIES: this
    //REQUIRES: entry is not empty, entry does not yet exist
    public void addEntry(SingleEntry entry) {
        entries.add(entry);
        for (int i = 0; i < TIMES; i++) {
            distribution.add(entry.getDescription());
        }
    }

    //EFFECTS: removes entry and its description from database and distribution
    //MODIFIES: this
    //REQUIRES: entry is in database, database is not empty, there are no duplicates
    public void removeEntry(String description) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getDescription().equals(description)) {
                entries.remove(entries.get(i));
                i--;
            }
        }
        for (int j = 0; j < distribution.size(); j++) {
            if (distribution.get(j).equals(description)) {
                distribution.remove(distribution.get(j));
                j--;
            }
        }
    }

    //EFFECTS: removes description from distribution if guessed right unless would not be contained anymore,
    // adds if guessed wrong
    //MODIFIES: this
    public void adjustDistribution(SingleEntry entry, String input) {
        if ((entry.getDescription().equals(input))) {
            distribution.remove(entry.getDescription());
            if (!(distribution.contains(entry.getDescription()))) {
                distribution.add(entry.getDescription());
            }
        } else {
            distribution.add(entry.getDescription());
        }
    }

    //EFFECTS: returns an entry from entriesList based on randomly selected description in distribution
    //Sources: partly based on https://stackoverflow.com/questions/8065532/how-to-randomly-pick-an-element-from-an-array
    public SingleEntry selectFromDistribution() {
        int random = new Random().nextInt(distribution.size());
        return getEntryBasedOnValue(distribution.get(random));
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

    //EFFECTS: returns an entry based on index in entries
    public SingleEntry getEntryBasedOnIndex(int i) {
        return this.entries.get(i);
    }

    //EFFECTS: returns the description of entry based on index
    public String getDescriptionFromDistribution(int i) {
        return this.distribution.get(i);
    }

    //EFFECTS: returns true if entries is empty, false otherwise
    public boolean isEmptyEntries() {
        return entries.isEmpty();
    }

    //EFFECTS: returns true if distribution is empty, false otherwise
    public boolean isEmptyDistribution() {
        return distribution.isEmpty();
    }

    //EFFECTS: returns the size of the entries list
    public int getSizeEntries() {
        return entries.size();
    }

    //EFFECTS: returns the size of the distribution list
    public int getSizeDistribution() {
        return distribution.size();
    }

    //returns the entries
    public ArrayList<SingleEntry> getEntries() {
        return entries;
    }

}

