package persistence;

import com.google.gson.Gson;
import model.Profile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//Represents a reader to read files from json file
public class Reader {
    Profile[] profiles;

    //EFFECTS: constructs a reader
    //SOURCE: code partly based on https://gist.github.com/julianbonilla/2784293
    public Reader() {
        try (FileReader reader = new FileReader("data/profiles.json")) {
            Gson gson = new Gson();
            profiles = gson.fromJson(reader, Profile[].class);
            System.out.println(Arrays.toString(profiles));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profile[] getProfiles() {
        return profiles;
    }

    //EFFECTS: returns the profile that is being searched for
    public Profile findProfile(String name) {
        for (Profile profile : profiles) {
            if (profile.getName().equals(name)) {
                return profile;
            }
        }
        return null;
    }

}

