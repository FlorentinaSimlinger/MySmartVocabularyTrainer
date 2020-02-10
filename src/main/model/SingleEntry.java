package model;


import java.util.Collection;
import java.util.HashMap;

//Represents a single entry consisting of a description, the meaning, a comment and an example
public class SingleEntry {
    protected HashMap<String, String> entry;
    protected String description;
    protected String meaning;
    protected String comment;
    protected String example;


    //EFFECTS: constructs a hashmap consisting of keys and their values
    protected SingleEntry(String description, String meaning, String comment, String example) {
        this.description = description;
        this.meaning = meaning;
        this.comment = comment;
        this.example = example;
        entry = new HashMap<String, String>();
        entry.put("description: ", description);
        entry.put("meaning: ", meaning);
        entry.put("comment: ", comment);
        entry.put("example: ", example);
    }

    // EFFECTS: checks if the string is the description
    protected boolean checkIfCorrectDescription(String str) {
        return (str.equals(description));
    }

    protected String getDescription() {
        return entry.get("description: ");
    }

    protected String getMeaning() {
        return entry.get("meaning: ");
    }

    protected String getComment() {
        return entry.get("comment: ");
    }

    protected String getExample() {
        return entry.get("example: ");
    }

    protected Collection getValues() {
        return entry.values();
    }
}

