package model;

import java.util.ArrayList;

// Represents the distribution of entries shown to user
public class Distribution {
    ArrayList<String> distribution;
    public static final int TIMES = 3;

    // EFFECTS: creates distribution of all the entries where each entry initially appears TIMES times
    public Distribution(Database d) {
        distribution = new ArrayList<>();
        for (int i = 0; i < d.getSize(); i++) {
            for (int j = 0; j < TIMES; j++) {
                distribution.add(d.getEntry(i).getDescription());
            }
        }
    }

    protected int getSize() {
        return distribution.size();
    }

    protected void adjustDistribution() {
        // stub
    }
}
