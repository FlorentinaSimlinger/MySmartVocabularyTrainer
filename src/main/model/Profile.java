package model;

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


}


