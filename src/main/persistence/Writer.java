package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import model.Profile;
import model.SingleEntry;

import java.io.FileWriter;
import java.io.IOException;

//Represents a writer to write data into json file
public class Writer {

    //EFFECTS: constructs a Writer
    //SOURCE: partly based on https://www.studytrails.com/java/json/java-google-json-parse-json-to-java/
    public Writer() throws IOException {
        Profile randomProfile = new Profile();
        randomProfile.setName("Flo");
        SingleEntry entry1 = new SingleEntry("description", "meaning", "hdfhdhf", "hahaj");
        randomProfile.getDatabase().addEntry(entry1);
        SingleEntry entry2 = new SingleEntry("description2", "meaning2", "hdfhdj", "dhdjfh");
        randomProfile.getDatabase().addEntry(entry2);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        JsonArray array = new JsonArray();

        String json = gson.toJson(randomProfile);
        System.out.println(json);

        try (FileWriter file = new FileWriter("data/name.json")) {
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
