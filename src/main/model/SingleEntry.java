package model;


import java.util.Collection;
import java.util.HashMap;

//Represents an entry consisting of description, meaning, comment, example, hashmap, & total attempted and failed times
public class SingleEntry {
    protected HashMap<String, String> match;
    protected Integer failures;
    protected Integer attempts;


    //EFFECTS: constructs a hashmap consisting of keys and their values
    public SingleEntry(String description, String meaning, String comment, String example) {
        failures = 1;
        attempts = 1;
        match = new HashMap<>();
        match.put("description: ", description);
        match.put("meaning: ", meaning);
        match.put("comment: ", comment);
        match.put("example: ", example);
    }

    //EFFECTS: constructs a hashmap consisting of keys and their values
    public SingleEntry() {
        failures = 1;
        attempts = 1;
        match = new HashMap<>();
        match.put("description: ", "");
        match.put("meaning: ", "");
        match.put("comment: ", "");
        match.put("example: ", "");
    }

    //EFFECTS: adjusts the failure rate based on whether guessed right or wrong
    //MODIFIES: this
    public void adjustDistribution(String input) {
        if (this.getDescription().equals(input)) {
            adjustFailuresDown();
        } else {
            adjustFailuresUp();
        }
        adjustAttempts();
    }

    // EFFECTS: increases failures by one
    // MODIFIES: this
    public void adjustFailuresUp() {
        failures += 1;
    }

    // EFFECTS: decreases the failures unless would go zero
    // MODIFIES: this
    public void adjustFailuresDown() {
        if (failures > 1) {
            failures -= 1;
        }
    }

    // EFFECTS: adjusts the attempts
    // MODIFIES: this
    public void adjustAttempts() {
        attempts += 1;
    }

    // EFFECTS: resets the fields for failures and attempts to 1
    // MODIFIES: this
    public void resetFailuresAndAttempts() {
        setFailures(1);
        setAttempts(1);
    }

    @Override
    public String toString() {
        return getDescription() + ", " + getMeaning() + ", " + getComment() + ", " + getExample();
    }

    public String getDescription() {
        return match.get("description: ");
    }

    public void setDescription(String description) {
        match.replace("description: ", description);
    }

    public String getMeaning() {
        return match.get("meaning: ");
    }

    public void setMeaning(String meaning) {
        match.replace("meaning: ", meaning);
    }

    public String getComment() {
        return match.get("comment: ");
    }

    public void setComment(String comment) {
        match.replace("comment: ", comment);
    }

    public String getExample() {
        return match.get("example: ");
    }

    public void setExample(String example) {
        match.replace("example: ", example);
    }

    public int getFailures() {
        return failures;
    }

    public int getAttempts() {
        return attempts;
    }

    public double getFailureRate() {
        return ((double)failures / attempts) * 100;
    }

    public double getSuccessRate() {
        return 100 - getFailureRate();
    }

    //MODIFIES: this
    public void setFailures(int failures) {
        this.failures = failures;
    }

    //MODIFIES: this
    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Collection getValues() {
        return match.values();
    }
}


