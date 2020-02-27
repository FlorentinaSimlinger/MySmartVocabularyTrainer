package persistence;

import model.Profile;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    void testReadProfiles() {
        try {
            assertEquals("Flo", new Reader().findProfile("Flo").getName());
            assertNull(new Reader().findProfile("NonExistentProfileName"));
            assertEquals(1, new Reader().getProfiles().length);
            assertNotNull(new Reader().getProfiles());
        } catch (IOException e) {
            fail("IOException should not have been thrown.");
        }
    }


    @Test
    void testReadTestProfiles1() {
        try {
            Profile[] profiles = Reader.readForTestPurposes(new File ("data/testProfiles1.json"));
            assertEquals(2, profiles.length);
        } catch (IOException e) {
            fail("IOException should not have been thrown.");
        }
    }

    //SOURCE: test based on TellerApp CPSC 210
    @Test
    void testIOException() {
        try {
            Reader.readForTestPurposes(new File(".data/nonExistingTestProfiles.json"));
        } catch (IOException e) {
            System.out.println("Expected Exception is thrown.");
        }
    }
}
