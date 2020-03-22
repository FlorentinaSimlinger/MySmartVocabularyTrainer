package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileTest {
    private Profile profile;
    private SingleEntry entry1;
    private SingleEntry entry2;
    private SingleEntry entry3;
    private Database database0; //database with 0 entries
    private Database database1; //database with 1 entry
    private Database database2; //database with 2 entries
    private Database database3; //database with 3 entries


    @BeforeEach
    void runBefore() {
        profile = new Profile();
        profile.setName("");
        entry1 = new SingleEntry("toboggan", "sled", "canadian for sled",
                "riding down the hill with a toboggan");
        entry2 = new SingleEntry("eh", "right?", "used at end of sentence",
                "it's cold, eh?");
        entry3 = new SingleEntry("description", "meaning", "comment", "example");
        database0 = new Database();
        database1 = new Database();
        database2 = new Database();
        database3 = new Database();

        database1.addEntry(entry1);
        database2.addEntry(entry1);
        database2.addEntry(entry2);
        database3.addEntry(entry1);
        database3.addEntry(entry2);
        database3.addEntry(entry3);
    }

    @Test
    void testProfileConstructor() {
        assertEquals("", profile.getName());
        assertEquals(0, profile.getSuccessRates().get(0));
        assertEquals(0, profile.getDatabase().getSizeEntries());
        assertTrue(profile.getDatabase().isEmptyEntries());
    }

    @Test
    void testAddSuccessRateOfSession() {
        profile.setDatabase(database2);
        assertEquals(0, profile.getTotalSuccesses());
        assertEquals(2, profile.getTotalAttempts());
        assertEquals(0.0, profile.getSuccessRates().get(0));
        entry1.setAttempts(10);
        entry1.setFailures(5);
        entry2.setAttempts(10);
        entry2.setFailures(2);
        assertEquals(20, profile.getTotalAttempts());
        assertEquals(13, profile.getTotalSuccesses());
        profile.addSuccessRateOfSession();
        assertEquals((13 / 20)*100.0, profile.getSuccessRates().get(1));
    }


}
