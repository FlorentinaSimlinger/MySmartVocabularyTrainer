package ui;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import model.Profile;
import model.SingleEntry;

import java.io.IOException;
import java.io.PrintWriter;

//Main class
public class Main {
    public static void main(String[] args) {
//      new VocabApp();

        Profile profile1 = new Profile();
        profile1.setName("Flo");
        SingleEntry entry1 = new SingleEntry("random", "random2", "hdfhdhf", "hahaj");
        profile1.getDatabase().addEntry(entry1);

        //Creating user1
        JsonObject user1 = new JsonObject();
        user1.put("name", "profilename");

        //Saving a single entry into the user
        JsonObject entrynew = new JsonObject();
        entrynew.put("description", "testdescription");
        entrynew.put("meaning", "testmeaning");
        entrynew.put("comment", "testcomment");
        entrynew.put("example", "testexample");
        entrynew.put("successRate", 50);

        JsonObject entry2 = new JsonObject();
        entrynew.put("description", "blah");
        entrynew.put("meaning", "blob");
        entrynew.put("comment", "blie");
        entrynew.put("example", "blum");
        entrynew.put("successRate", 50);

        //create list and add entries to it
        JsonArray entryList = new JsonArray();
        entryList.add(entrynew);
        entryList.add(entry2);

        //Write JSON file
        try (PrintWriter file = new PrintWriter("data/user.json")) {
            file.write(user1.toJson());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//        // writing JSON to file:"JSONExample.json" in cwd
//        PrintWriter pw = new PrintWriter("data/user.json");
//        pw.write(user.toJson());
//
//        pw.flush();
//        pw.close();
//    }
//
//    public static void Reader(String[] args) throws FileNotFoundException {
//        Object obj = new JSONParser().parse(new FileReader("data/user.json"));
//        JsonObject user = (JsonObject) obj;
//        String firstName = (String) user.get("name");
//
//    }


//notes to myself:
// - I create methods inside a class if I want to do more specific things than just access the fields
// - if I simply want to access the fields I could call Database.fieldname.size()
// - generally do NOT call the fields the same as the type name