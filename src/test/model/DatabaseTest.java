package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
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

        entry1.setFailures(1);
        entry1.setAttempts(1);
        entry1.setFailures(1);
        entry2.setAttempts(2);
        entry3.setFailures(1);
        entry3.setAttempts(4);

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
    void testDatabaseConstructor() {
        assertTrue(database0.isEmptyEntries());
    }

    @Test
    void testIsEmpty() {
        assertTrue(database0.isEmptyEntries());
        assertFalse(database1.isEmptyEntries());
    }

    @Test
    void testSearchEntry() {
        assertEquals(entry2, database2.getEntryBasedOnValue("eh"));
        assertNull(database2.getEntryBasedOnValue("random String"));
        assertNull(database0.getEntryBasedOnValue("random String"));
    }

    @Test
    void testAddOneEntry() {
        database0.addEntry(entry1);
        assertEquals(1, database0.getSizeEntries());
        assertEquals(entry1, database0.getEntryBasedOnIndex(0));
        assertEquals(1, database0.getEntries().size());
    }

    @Test
    void testAddManyEntries() {
        database0.addEntry(entry1);
        database0.addEntry(entry2);
        database0.addEntry(entry3);
        assertEquals(3, database0.getSizeEntries());
    }

    @Test
    void testRemoveOneEntry() {
        database2.removeEntry("eh");
        assertEquals(1, database2.getSizeEntries());
        database0.removeEntry("toboggan");
        assertEquals(0, database0.getSizeEntries());
    }

    @Test
    void testRemoveManyEntries() {
        database3.removeEntry("description");
        database3.removeEntry("toboggan");
        assertEquals(1, database3.getSizeEntries());
    }

    @Test
    void testGetRandomFromSumOfFailureRates() {
        double random = database2.getRandomFromSumOfFailureRates();
        assertTrue(random <= database2.getSumOfFailureRates());
    }

    @Test
    void testGetEntryBasedOnRandom() {
        double random1 = 100;
        double random2 = 150;
        double random3 = 175;
        assertEquals(entry1, database3.getEntryBasedOnRandom(random1));
        assertEquals(entry2, database3.getEntryBasedOnRandom(random2));
        assertEquals(entry3, database3.getEntryBasedOnRandom(random3));
    }

    @Test
    void testGetSumOfFailureRates() {
        assertEquals(100 + 50 + 25, database3.getSumOfFailureRates());
    }
}

