package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SingleEntryTest {
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
    void testEntryConstructor() {
        assertEquals("toboggan", entry1.getDescription());
        assertEquals("sled", entry1.getMeaning());
        assertEquals("canadian for sled", entry1.getComment());
        assertEquals("riding down the hill with a toboggan", entry1.getExample());
    }

    @Test
    void testToString() {
        assertEquals(entry1.toString(),
                "toboggan, sled, canadian for sled, riding down the hill with a toboggan");
    }

    @Test
    void testAdjustDistribution() {
        entry1.adjustDistribution("non existing description");
        assertEquals(2, entry1.getFailures());
        assertEquals(2, entry1.getAttempts());
        assertEquals(100, entry1.getFailureRate());
        entry3.adjustDistribution("description");
        assertEquals(1, entry3.getFailures());
        assertEquals(5, entry3.getAttempts());
        assertEquals(4, entry3.getSuccesses());
        assertEquals(20, entry3.getFailureRate());
    }

    @Test
    void testFailuresUpAndDown() {
        entry1.adjustFailuresUp();
        assertEquals(2, entry1.getFailures());
        entry1.adjustFailuresDown();
        assertEquals(1, entry1.getFailures());
        entry1.adjustFailuresDown();
        assertEquals(1, entry1.getFailures());
    }

    @Test
    void testResetFailuresAndAttempts() {
        entry3.resetFailuresAndAttempts();
        assertEquals(1, entry1.getFailures());
        assertEquals(1, entry1.getAttempts());
    }

    @Test
    void testGetSuccessRate() {
        assertEquals(75, entry3.getSuccessRate());
    }

    @Test
    void testSetEntryFields() {
        SingleEntry entry4 = new SingleEntry();
        entry4.setDescription("random description");
        entry4.setMeaning("random meaning");
        entry4.setComment("random comment");
        entry4.setExample("random example");
        assertEquals("random description", entry4.getDescription());
        assertEquals("random meaning", entry4.getMeaning());
        assertEquals("random comment", entry4.getComment());
        assertEquals("random example", entry4.getExample());
    }
}
