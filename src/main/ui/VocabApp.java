package ui;

import model.Profile;
import model.SingleEntry;
import persistence.Reader;
import persistence.Writer;

import java.util.*;

//Represents a vocabulary trainer application
public class VocabApp {
    private Scanner input;
    Profile profile;
    List<Profile> profiles = new ArrayList<>();
    Reader reader = new Reader();

    //EFFECTS: runs the vocabulary app
    public VocabApp() {
        runApp();
    }

    //EFFECTS: processes user input
    //MODIFIES: this
    //Source: TellerApp CPSC 210
    public void runApp() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        loginOrSignUp();
        displayMenu();


        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        Writer.write(profiles);
        System.out.println("Goodbye!");
    }

    private void loginOrSignUp() {
        System.out.println("Welcome to My Smart Vocabulary Trainer! To login or sign up, please enter your name.");
        String name = input.nextLine();
        profiles = new ArrayList<>(Arrays.asList(reader.getProfiles()));
        Profile userProfile = reader.findProfile(name);
        if (userProfile == null) {
            userProfile = new Profile();
            userProfile.setName(name);
            System.out.println("We created a new profile for you!");
            profiles.add(userProfile);
        }
        profile = userProfile;
        System.out.println("Hi " + name + ", let's get started!");
    }



    //EFFECTS: displays the menu
    //Source: TellerApp CPSC 210
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tTo load an example database, enter 'load'");
        System.out.println("\tTo add an entry, enter 'add'");
        System.out.println("\tTo remove an entry, enter 'remove'");
        System.out.println("\tTo search for an entry, enter 'search'");
        System.out.println("\tTo display all entries, enter 'display'");
        System.out.println("\tTo test yourself, enter 'test'");
        System.out.println("\tTo end your session, enter 'quit'");
    }

    //EFFECTS: processes user command
    //MODIFIES: this
    //Source: TellerApp CPSC 210
    private void processCommand(String command) {
        if (command.equals("load")) {
            doLoadExampleEntries();
        } else if (command.equals("add")) {
            doAddEntry();
        } else if (command.equals("remove")) {
            doRemoveEntry();
        } else if (command.equals("search")) {
            doSearchEntry();
        } else if (command.equals("display")) {
            doDisplayAllEntries();
        } else if (command.equals("test")) {
            doTestMyself();
        } else {
            doHandleException();
        }
    }

    private void doLoadExampleEntries() {
        SingleEntry entry1 = new SingleEntry("toboggan", "sled",
                "verb is 'to toboggan'", "riding down a hill with a sled");
        SingleEntry entry2 = new SingleEntry("eh?", "'right?'",
                "does not have to be used as question", "it's cold today, eh?");
        SingleEntry entry3 = new SingleEntry("ubiquitous", "everywhere",
                "yü-ˈbi-kwə-təs", "");
        profile.getDatabase().addEntry(entry1);
        profile.getDatabase().addEntry(entry2);
        profile.getDatabase().addEntry(entry3);
        System.out.println("What else would you like to do?");
    }

    //EFFECTS: adds entry to database
    //MODIFIES: Database
    private void doAddEntry() {
        input = new Scanner(System.in);
        System.out.println("Enter the word or phrase you'd like to add:");
        String description = input.nextLine();
        System.out.println("Enter the meaning or synonym that you use:");
        String meaning = input.nextLine();
        System.out.println("Enter a comment, if you'd like to add one:");
        String comment = input.nextLine();
        System.out.println("Enter an example sentence for the meaning or synonym:");
        String example = input.nextLine();

        SingleEntry entry;
        entry = new SingleEntry(description, meaning, comment, example);

        profile.getDatabase().addEntry(entry);

        System.out.println("You successfully added an entry for " + description
                + "\nWhat else would you like to do?\n");
    }

    //EFFECTS: removes entry from database
    //MODIFIES: Database
    private void doRemoveEntry() {
        System.out.println("Enter the word or phrase you'd like to remove");
        input.nextLine();
        String description = input.nextLine();

        profile.getDatabase().removeEntry(description);
        System.out.println("You successfully deleted " + description + ".\nWhat else would you like to do?");
    }

    //EFFECTS: returns entry based on search
    private void doSearchEntry() {
        System.out.println("What is the word or phrase you'd like to search for?");
        input.nextLine();
        String search = input.nextLine();
        System.out.println("The entry you're looking for has the following properties: ");
        System.out.println("Description: " + profile.getDatabase().getEntryBasedOnValue(search).getDescription());
        System.out.println("Meaning: " + profile.getDatabase().getEntryBasedOnValue(search).getMeaning());
        System.out.println("Comment: " + profile.getDatabase().getEntryBasedOnValue(search).getComment());
        System.out.println("Example: " + profile.getDatabase().getEntryBasedOnValue(search).getExample());
        if (profile.getDatabase().getEntryBasedOnValue(search).getAttempts() == 1) {
            System.out.println("You have no tests recorded for this entry.");
        } else {
            System.out.println("Your success rate for this entry is "
                    + profile.getDatabase().getEntryBasedOnValue(search).getSuccessRate() + "%.");
        }
        System.out.println("What else would you like to do?");
    }

    //EFFECTS: displays all entries
    private void doDisplayAllEntries() {
        for (SingleEntry entry : profile.getDatabase().getEntries()) {
            System.out.println(entry.toString());
        }
        System.out.println("What else would you like to do?");
    }

    //EFFECTS: tests user and adjusts distribution
    //MODIFIES: this
    private void doTestMyself() {
        System.out.println("Your test results are now recorded. "
                + "If you would like to return to the main menu, type 'return'.");
        boolean keepStudying = true;
        while (keepStudying) {
            input = new Scanner(System.in);
            double random = profile.getDatabase().getRandomFromSumOfFailureRates();
            SingleEntry selected = profile.getDatabase().getEntryBasedOnRandom(random);
            String shown = selected.getMeaning();
            System.out.println("What's another word or phrase for " + shown + "?");
            String userInput = input.nextLine();
            if (userInput.equals("return")) {
                keepStudying = false;
            } else if (selected.getDescription().equals(userInput)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Unfortunately that was wrong. The correct answer is "
                        + selected.getDescription() + ".");
            }
            selected.adjustDistribution(userInput);
        }
        System.out.println("What else would you like to do?");
    }

    private void doHandleException() {
        int random = new Random().nextInt(3);
        if (random == 0) {
            System.out.println("Sorry, I did not get that, please try again.");
            input.nextLine();
        } else if (random == 1) {
            System.out.println("Roses are red, violets are blue, I think there's a typo, "
                    + "no worries I got bugs too.");
            input.nextLine();
        } else if (random == 2) {
            System.out.println("Cannot.compute...please.try.again...");
            input.nextLine();
        }
    }
}

// TODO: is it okay to have loop in ui? Yes, as long as not too complex.
// TODO: is it okay to have methods that are currently not used in ui? Yes, because might need later.
// TODO: is it okay to have all the tests in one class? Yes, however split in more classes in future.
// TODO: is there a better way than to have all the methods public? Could put more function in single method, but not
//  advised because of modular approach
// TODO: do we need to throw exceptions? Not at this point.
// TODO: optimize distribution
// TODO: how can I better test random? had to split up method for testing but now I need to do a lot of
//  backend in frontend
// TODO: how to fix code coverage in getEntryBasedOnRandom? (ie, how to return outside for loop)
// TODO: fix the successrate

