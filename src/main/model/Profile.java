package model;

//Represents a profile having an owner name, a success rate and a database of entries
public class Profile {
    private String name;        //profile owner name
    private int successRate;    //rate of guessing words and expressions correctly
    Database database;          //database of entries

    //REQUIRES: profile name is of non-zero length
    //EFFECTS: constructs a profile and sets initial success rate to 0 and creates empty database
    public Profile(String name) {
        this.name = name;
        successRate = 0;
        database = new Database();
    }

    public String getName() {
        return name;
    }

    public int getSuccessRate() {
        return successRate;
    }
}


