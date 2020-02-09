package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

//A writer that can write data into the database
public class Writer {
    private PrintWriter printWriter;

    //EFFECTS: constructs a writer that will write data to the database
    // Source: TellerApp from CPSC 210
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    //MODIFIES: this
    //EFFECTS: writes saveable to file
    //Source: TellerApp from CPSC 210
    public void write(Saveable saveable) {
        saveable.save(printWriter);
    }

    //MODIFIES: this
    //EFFECTS: close printer
    //Source: TellerApp from CPSC 210
    public void close() {
        printWriter.close();
    }
}
