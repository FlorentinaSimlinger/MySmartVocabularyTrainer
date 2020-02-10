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
    private Distribution distribution;
    public static final int TIMES = 3;


    @BeforeEach
    void runBefore() {
        profile = new Profile("Jane");
        entry1 = new SingleEntry("toboggan", "sled", "canadian for sled",
                "riding down the hill with a toboggan");
        entry2 = new SingleEntry("eh", "right?", "used at end of sentence",
                "it's cold, eh?");
        entry3 = new SingleEntry("description", "meaning", "comment", "example");
        database0 = new Database();
        database1 = new Database();
        database2 = new Database();

        database1.addEntry(entry1);
        database2.addEntry(entry1);
        database2.addEntry(entry2);
    }

    @Test
    void testProfileConstructor() {
        assertEquals("Jane", profile.getName());
        assertEquals(0, profile.getSuccessRate());
    }

    @Test
    void testEntryConstructor() {
        assertEquals("toboggan", entry1.getDescription());
        assertEquals("sled", entry1.getMeaning());
        assertEquals("canadian for sled", entry1.getComment());
        assertEquals("riding down the hill with a toboggan", entry1.getExample());
    }

    @Test
    void testDistributionConstructor() {
        distribution = new Distribution(database2);
        assertEquals(database2.getSize()*TIMES, distribution.getSize());
    }
    //TODO: why do I have to instantiate new Distribution inside test?

    @Test
    void testCheckIfCorrectDescription() {
        assertTrue(entry1.checkIfCorrectDescription("toboggan"));
        assertFalse(entry1.checkIfCorrectDescription("eh"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(database0.isEmpty());
        assertFalse(database1.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(2, database2.getSize());
    }

    @Test
    void testSearchEntry() {
        assertEquals(entry2, database2.searchEntry("eh"));
        assertNull(database2.searchEntry("random String"));
        assertNull(database0.searchEntry("random String"));
    }

    @Test
    void testRemoveEntry() {
        database2.removeEntry(entry2);
        assertEquals(1, database2.getSize());
        database0.removeEntry(entry1);
        assertEquals(0, database0.getSize());

    }

}

