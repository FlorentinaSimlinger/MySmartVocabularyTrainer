package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Profile;
import model.SingleEntry;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Main class
public class Main {
    // Source: partly based on https://www.studytrails.com/java/json/java-google-json-parse-json-to-java/
    public static void main(String[] args) throws FileNotFoundException {
//      new VocabApp();

        Profile profile1 = new Profile();
        profile1.setName("Flo");
        SingleEntry entry1 = new SingleEntry("description", "meaning", "hdfhdhf", "hahaj");
        profile1.getDatabase().addEntry(entry1);
        SingleEntry entry2 = new SingleEntry("description2", "meaning2", "hdfhdj", "dhdjfh");
        profile1.getDatabase().addEntry(entry2);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(profile1);
        System.out.println(json);

        try (FileWriter file = new FileWriter("data/user.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // https://gist.github.com/julianbonilla/2784293

        try (FileReader reader = new FileReader("data/user.json")) {
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







//notes to myself:
// - I create methods inside a class if I want to do more specific things than just access the fields
// - if I simply want to access the fields I could call Database.fieldname.size()
// - generally do NOT call the fields the same as the type name
// https://www.w3schools.com/js/js_json_objects.asp
// we can access values from a JSON object by just calling with . notation.
// we need to use the parser if we have a String, which is often the case when we're
// receiving data from a webserver.
// https://stackoverflow.com/questions/7451600/jsonobject-how-to-get-a-value