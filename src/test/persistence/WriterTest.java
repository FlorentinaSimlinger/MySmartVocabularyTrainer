package persistence;

import model.Profile;
import model.SingleEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private Profile profile;
    private Profile profile1;
    List<Profile> profiles;
    private SingleEntry entry1;
    private SingleEntry entry2;

    //Source: Teller App CPSC 210
    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        profile = new Profile();
        profile1 = new Profile();
        profile.setName("Flo");
        profile1.setName("Random");
        entry1 = new SingleEntry("toboggan", "sled", "canadian for sled",
                "riding down the hill with a toboggan");
        entry2 = new SingleEntry("eh", "right?", "used at end of sentence",
                "it's cold, eh?");
        profile.getDatabase().addEntry(entry1);
        profiles = new ArrayList<>();
        profiles.add(profile);
    }


    @Test
    void testWriteProfiles() {
        try {
            Writer writer = new Writer();
            writer.write(profiles);
            assertNotNull(writer);
        } catch (IOException e) {
            fail("IOException not expected");
        }
    }
}
