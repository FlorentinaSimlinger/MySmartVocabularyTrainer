package model;


import java.util.Collection;
import java.util.HashMap;

//Represents a single entry consisting of a description, the meaning, a comment and an example
public class SingleEntry {
    protected HashMap<String, String> match;
    protected String description;
    protected String meaning;
    protected String comment;
    protected String example;


    //EFFECTS: constructs a hashmap consisting of keys and their values
    public SingleEntry(String description, String meaning, String comment, String example) {
        this.description = description;
        this.meaning = meaning;
        this.comment = comment;
        this.example = example;
        match = new HashMap<>();
        match.put("description: ", description);
        match.put("meaning: ", meaning);
        match.put("comment: ", comment);
        match.put("example: ", example);
    }

    // EFFECTS: checks if the string is the description
    public boolean checkIfCorrectDescription(String str) {
        return (str.equals(description));
    }

    public String getDescription() {
        return match.get("description: ");
    }

    public String getMeaning() {
        return match.get("meaning: ");
    }

    public String getComment() {
        return match.get("comment: ");
    }

    public String getExample() {
        return match.get("example: ");
    }

    public Collection getValues() {
        return match.values();
    }

    @Override
    public String toString() {
        return getDescription() + ", " + getMeaning() + ", " + getComment() + ", " + getExample();
    }
}


