package persistence;

import com.google.gson.Gson;
import model.Profile;

import java.io.FileReader;
import java.io.IOException;

//Represents a reader to read files from json file
public class Reader {

    //EFFECTS: constructs a reader
    //SOURCE: code partly based on https://gist.github.com/julianbonilla/2784293
    public Reader() throws IOException {
        try (FileReader reader = new FileReader("data/profiles.json")) {
            Gson newGson = new Gson();
            // Convert JSON File to Java Object
            Profile newProfile = newGson.fromJson(reader, Profile.class);

            //do stuff
            System.out.println(newProfile.toString());
            System.out.println(newProfile.getName());
            System.out.println(newProfile.getDatabase());
            System.out.println(newProfile.getDatabase().getEntries());
            System.out.println(newProfile.getDatabase().getEntryBasedOnValue("description"));
            System.out.println(newProfile.getDatabase().getEntryBasedOnValue("description").getMeaning());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

