package ui;

import model.Profile;
import model.SingleEntry;

import java.util.Random;
import java.util.Scanner;

//Vocabulary trainer application
public class VocabApp {
    private Scanner input;
    Profile profile;

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

        System.out.println("Welcome to My Smart Vocabulary Trainer! To create a profile, please enter your name.");
        String name = input.nextLine();
        profile = new Profile();
        profile.setName(name);
        System.out.println("Hi " + name + ", let's get started!");

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

        System.out.println("\nGoodbye");
    }

    //EFFECTS: displays the menu
    //Source: TellerApp CPSC 210
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tTo add an entry, type 'add'");
        System.out.println("\tTo remove an entry, type 'remove'");
        System.out.println("\tTo search for an entry, type 'search'");
        System.out.println("\tTo display all entries, type 'display'");
        System.out.println("\tTo test yourself, type 'test'");
        System.out.println("\tTo end your session, type 'quit'");
    }

    //EFFECTS: processes user command
    //MODIFIES: this
    //Source: TellerApp CPSC 210
    private void processCommand(String command) {
        if (command.equals("add")) {
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

    //EFFECTS: adds entry to database
    //MODIFIES: Database
    private void doAddEntry() {
        input = new Scanner(System.in);
        System.out.println("Enter the word or phrase you'd like to add:");
        //input.nextLine();
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
        System.out.println("Comment: " + profile.getDatabase().getEntryBasedOnValue(search).getMeaning());
        System.out.println("Example: " + profile.getDatabase().getEntryBasedOnValue(search).getExample());
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
    private void doTestMyself() {
        System.out.println("Your test results are now recorded. "
                + "If you would like to return to the main menu, type 'return'.");
        boolean keepStudying = true;
        while (keepStudying) {
            input = new Scanner(System.in);
            SingleEntry selected = profile.getDatabase().selectFromDistribution();
            String shown = selected.getMeaning();
            System.out.println("What's another word or phrase for " + shown + "?");
            String meaning = input.nextLine();
            if (meaning.equals("return")) {
                keepStudying = false;
            } else if (selected.checkIfCorrectDescription(meaning)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Unfortunately that was wrong.");
            }
            profile.getDatabase().adjustDistribution(selected, meaning);
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

