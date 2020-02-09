package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabTest {
    private Profile profile;
    private SingleEntry entry1;
    private SingleEntry entry2;
    private Database database1;
    private Database database2;
    private Database database3;


    @BeforeEach
    void runBefore() {
        profile = new Profile("Jane");
        entry1 = new SingleEntry("toboggan", "sled", "canadian for sled",
                "riding down the hill with a toboggan");
        entry2 = new SingleEntry("eh", "right?", "used at end of sentence",
                "it's cold, eh?");
        database1 = new Database();
        database2 = new Database();
        database3 = new Database();

        database2.addEntry(entry1);
        database3.addEntry(entry1);
        database3.addEntry(entry2);
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
    void testIsEmpty() {
        assertTrue(database1.isEmpty());
        assertFalse(database2.isEmpty());
    }

    @Test
    void testGetSize() {
        assertEquals(2, database3.getSize());
    }

    @Test
    void testGetEntry() {
        assertEquals(entry2, database3.getEntry("eh"));
        assertNull(database3.getEntry("random String"));
        assertNull(database1.getEntry("ranom String"));
    }

}