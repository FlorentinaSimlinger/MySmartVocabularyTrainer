package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabTest {
    private Profile profile;
    private SingleEntry entry1;
    private SingleEntry entry2;
    private SingleEntry entry3;
    private Database database0; //database with 0 entries
    private Database database1; //database with 1 entry
    private Database database2; //database with 2 entries
    private Database database3; //database with 3 entries
    public static final int TIMES = 3;


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
        assertEquals(0, profile.getSuccessRate());
        assertTrue(profile.getDatabase().isEmptyEntries());
        assertTrue(profile.getDatabase().isEmptyDistribution());
    }

    @Test
    void testEntryConstructor() {
        assertEquals("toboggan", entry1.getDescription());
        assertEquals("sled", entry1.getMeaning());
        assertEquals("canadian for sled", entry1.getComment());
        assertEquals("riding down the hill with a toboggan", entry1.getExample());
    }

    @Test
    void testDatabaseConstructor() {
        assertTrue(database0.isEmptyEntries());
        assertTrue(database0.isEmptyDistribution());
    }

    @Test
    void testCheckIfCorrectDescription() {
        assertTrue(entry1.checkIfCorrectDescription("toboggan"));
        assertFalse(entry1.checkIfCorrectDescription("eh"));
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
        assertEquals(1*TIMES, database0.getSizeDistribution());
        assertEquals("toboggan", database0.getDescriptionFromDistribution(0));
        assertEquals("toboggan", database0.getDescriptionFromDistribution(1));
        assertEquals(entry1, database0.getEntryBasedOnIndex(0));
    }

    @Test
    void testAddManyEntries() {
        database0.addEntry(entry1);
        database0.addEntry(entry2);
        database0.addEntry(entry3);
        assertEquals(3, database0.getSizeEntries());
        assertEquals(3*TIMES, database0.getSizeDistribution());
    }

    @Test
    void testRemoveOneEntry() {
        database2.removeEntry("eh");
        assertEquals(1, database2.getSizeEntries());
        assertEquals(1*TIMES, database2.getSizeDistribution());
        database0.removeEntry("toboggan");
        assertEquals(0, database0.getSizeEntries());
    }

    @Test
    void testRemoveManyEntries() {
        database3.removeEntry("description");
        database3.removeEntry("toboggan");
        assertEquals(1, database3.getSizeEntries());
        assertEquals(3, database3.getSizeDistribution());
    }

    @Test
    void testToString() {
        assertEquals(entry1.toString(),
                "toboggan, sled, canadian for sled, riding down the hill with a toboggan");
    }

    @Test
    void testSelectFromDistribution() {
       assertTrue(database3.getEntries().contains(database3.selectFromDistribution()));
    }

    @Test
    void testAdjustDistributionSuccess() {
        database1.adjustDistribution(entry1, "toboggan");
        assertEquals(TIMES - 1, database1.getSizeDistribution());
    }

    @Test
    void testAdjustDistributionFailure() {
        database1.adjustDistribution(entry1, "this description does not exist");
        assertEquals(TIMES + 1, database1.getSizeDistribution());
    }

    @Test
    void testAdjustDistributionNotBelowOne() {
        database1.adjustDistribution(entry1, "toboggan");
        database1.adjustDistribution(entry1, "toboggan");
        database1.adjustDistribution(entry1, "toboggan");
        assertEquals(1, database1.getSizeDistribution());
    }


}

