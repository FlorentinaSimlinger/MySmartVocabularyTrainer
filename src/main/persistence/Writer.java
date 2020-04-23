package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Profile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

//Represents a writer to write data into json file
public class Writer {

    //EFFECTS: constructs a Writer
    //SOURCE: partly based on https://www.studytrails.com/java/json/java-google-json-parse-json-to-java/
    public static void write(List<Profile> list) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String json = gson.toJson(list);
        //System.out.println(json); for testing

        FileWriter file = new FileWriter("data/profiles.json");
        file.write(json);
        file.flush();
    }
}

//This is another working version with a JsonArray
//Source: parts of https://www.youtube.com/watch?v=Vl7C2OwY3K4&t=356s
//        GsonBuilder builderNew = new GsonBuilder();
//        Gson gsonNew = builderNew.setPrettyPrinting().create();
//        String jsonNew = gsonNew.toJson(randomProfile);
//        JsonArray jsonArray = new JsonArray();
//        jsonArray.add(jsonNew);
//        System.out.println(jsonNew);

//        try (FileWriter file = new FileWriter("data/profiles.json")) {
//                file.write(json);
//                file.flush();
//                } catch (IOException e) {
//                e.printStackTrace();
//                }