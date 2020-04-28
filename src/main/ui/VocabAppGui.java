package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Profile;
import model.SingleEntry;
import persistence.Reader;
import persistence.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Represents a vocabulary trainer application
public class VocabAppGui extends Application {
    private RootLayout rootLayout;
    private MainLayout mainLayout;
    private TestLayout testLayout;
    private DatabaseLayout databaseLayout;
    private SearchLayout searchLayout;
    private AboutLayout aboutLayout;
    private LoginLayout loginLayout;
    private ProfileLayout profileLayout;
    private Profile profile;
    private SingleEntry selected;
    private ArrayList<Profile> profiles;
    private ObservableList<SingleEntry> databaseTable;
    private Reader reader;
    private Stage window;


    //EFFECTS: launches the app
    //SOURCE: if not otherwise indicated, code of the entire app is partly based on
    // https://www.youtube.com/watch?v=FLkOX4Eez6o&list=PLkY8n-MZcmgRwjYDebUGDcf1PCxT3JA5z and the following videos in
    // that series
    public static void main(String[] args) {
        launch(args);
    }

    //EFFECTS: starts the app
    //MODIFIES: this
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadProfiles();
        this.window = primaryStage;
        this.window.setOnCloseRequest(e -> closeProgram());
        this.window.setTitle("MySmartVocabularyTrainer");

        this.loginLayout = new LoginLayout();
        this.rootLayout = new RootLayout();
        this.mainLayout = new MainLayout();
        this.searchLayout = new SearchLayout();
        this.testLayout = new TestLayout();
        this.databaseLayout = new DatabaseLayout();
        this.aboutLayout = new AboutLayout();
        this.profileLayout = new ProfileLayout();

        addEventListenersToRootLayout();
        addEventListenersToMainLayout();
        addEventListenersToDatabaseLayout();
        addEventListenersToTestLayout();
        addEventListenersToSearchLayout();
        addEventListenersToProfileLayout();
        addEventListenersToLoginLayout();

        this.window.setScene(this.loginLayout.getLoginScene());

        this.window.show();
    }

    //EFFECTS: add event listeners to root layout
    //MODIFIES: this
    private void addEventListenersToRootLayout() {
        this.rootLayout.addEventListener(RootLayout.EVENT_ABOUT, e -> this.rootLayout.setChildPane(this.aboutLayout));
        this.rootLayout.addEventListener(RootLayout.EVENT_MAIN, e -> this.rootLayout.setChildPane(this.mainLayout));
        this.rootLayout.addEventListener(RootLayout.EVENT_PROFILE, e -> {
            this.rootLayout.setChildPane(this.profileLayout);
            this.updateProfileLineChart();
        });
        this.rootLayout.addEventListener(RootLayout.EVENT_TEST, e -> this.rootLayout.setChildPane(this.testLayout));
        this.rootLayout.addEventListener(RootLayout.EVENT_SEARCH, e -> this.rootLayout.setChildPane(this.searchLayout));
        this.rootLayout.addEventListener(RootLayout.EVENT_DATABASE, e -> {
            this.rootLayout.setChildPane(this.databaseLayout);
            setDatabaseTable();
        });
        this.rootLayout.addEventListener(RootLayout.EVENT_QUIT, e -> closeProgram());
    }

    //EFFECTS: add event listeners to main layout
    //MODIFIES: this
    private void addEventListenersToMainLayout() {
        this.mainLayout.addEventListener(MainLayout.EVENT_TEST, e -> this.rootLayout.setChildPane(this.testLayout));
        this.mainLayout.addEventListener(MainLayout.EVENT_QUIT, e -> closeProgram());
        this.mainLayout.addEventListener(MainLayout.EVENT_ADD, e -> {
            ArrayList<TextField> textFields = mainLayout.getTextFields();
            SingleEntry singleEntry = new SingleEntry();
            singleEntry.setDescription(textFields.get(0).getText());
            singleEntry.setMeaning(textFields.get(1).getText());
            singleEntry.setComment(textFields.get(2).getText());
            singleEntry.setExample(textFields.get(3).getText());
            databaseLayout.getTable().getItems().add(singleEntry);
            profile.getDatabase().addEntry(singleEntry);
            mainLayout.clearTextFields();
        });
    }

    //EFFECTS: add event listeners to database layout
    //MODIFIES: this
    private void addEventListenersToDatabaseLayout() {
        this.databaseLayout.addEventListener(DatabaseLayout.EVENT_ADD, e -> {
            SingleEntry singleEntry = new SingleEntry();
            ArrayList<TextField> textFields = databaseLayout.getTextFields();
            singleEntry.setDescription(textFields.get(0).getText());
            singleEntry.setMeaning(textFields.get(1).getText());
            singleEntry.setComment(textFields.get(2).getText());
            singleEntry.setExample(textFields.get(3).getText());
            databaseLayout.getTable().getItems().add(singleEntry);
            profile.getDatabase().addEntry(singleEntry);
            databaseLayout.clearTextFields();
        });
        this.databaseLayout.addEventListener(DatabaseLayout.EVENT_DELETE, e -> {
            ObservableList<SingleEntry> selectedSingleEntries;
            ObservableList<SingleEntry> allSingleEntries;
            selectedSingleEntries = this.databaseLayout.getSelectedSingleEntries();
            allSingleEntries = this.databaseLayout.getAllSingleEntries();
            this.databaseLayout.removeSelectedEntries(selectedSingleEntries, allSingleEntries);
            for (SingleEntry entry : selectedSingleEntries) {
                this.profile.getDatabase().removeEntry(entry.getDescription());
            }
        });
    }

    //EFFECTS: add event listeners to test layout
    //MODIFIES: this
    private void addEventListenersToTestLayout() {
        this.testLayout.addEventListener(TestLayout.EVENT_MAIN, e -> this.rootLayout.setChildPane(this.mainLayout));
        this.testLayout.addEventListener(TestLayout.EVENT_QUIT, e -> closeProgram());
        this.testLayout.addEventListener(TestLayout.EVENT_SHOWTESTQUESTION, e -> {
            double random = profile.getDatabase().getRandomFromSumOfFailureRates();
            this.selected = profile.getDatabase().getEntryBasedOnRandom(random);
            String questionPart = this.selected.getMeaning();
            this.testLayout.showTestQuestion(questionPart);
        });
        this.testLayout.addEventListener(TestLayout.EVENT_SHOWTESTFEEDBACK, e -> {
            boolean correct;
            correct = this.selected.getDescription().equals(this.testLayout.getTestInput().getText());
            String selectedMeaning = this.selected.getMeaning();
            String selectedDescription = this.selected.getDescription();
            this.selected.adjustDistribution(this.testLayout.getTestInput().getText());
            testLayout.showTestFeedback(correct, selectedMeaning, selectedDescription);
        });
    }

    //EFFECTS: add event listeners to search layout
    //MODIFIES: this
    public void addEventListenersToSearchLayout() {
        this.searchLayout.addEventListener(SearchLayout.EVENT_SEARCHENTRY, e -> {
            boolean entryFound = false;
            SingleEntry entry = profile.getDatabase().getEntryBasedOnValue(this.searchLayout.getSearchInput());
            String description = "";
            String meaning = "";
            String comment = "";
            String example = "";
            int successRate = 0;
            boolean entryAttempted = false;
            if (entry != null) {
                entryFound = true;
                description = entry.getDescription();
                meaning = entry.getMeaning();
                comment = entry.getComment();
                example = entry.getExample();
                if (entry.getAttempts() > 1) {
                    entryAttempted = true;
                    successRate = (int) entry.getSuccessRate();
                }
            }
            this.searchLayout.setSearchFeedback(entryFound, entryAttempted, description, meaning, comment, example,
                    successRate);
        });
    }

    //EFFECTS: adds event listeners to profile layout
    //MODIFIES: this
    private void addEventListenersToProfileLayout() {
        this.profileLayout.addEventListener(ProfileLayout.EVENT_SUCCESSRATES, e -> this.updateProfileLineChart());
    }

    //EFFECTS: add additional event listeners
    //MODIFIES: this
    private void addEventListenersToLoginLayout() {
        this.loginLayout.addEventListener("login",
                e -> {
                    this.profile = this.findOrCreateProfile();
                    this.rootLayout.setChildPane(this.mainLayout);
                    Scene rootScene = new Scene(this.rootLayout.getNode(), 920, 600);
                    this.window.setScene(rootScene);
                });
    }

    //EFFECTS: reads the profiles
    //MODIFIES: this
    private void loadProfiles() {
        try {
            this.reader = new Reader();
            this.profiles = new ArrayList<>(Arrays.asList(this.reader.getProfiles()));
        } catch (IOException e) {
            System.out.println("Something went wrong with loading the profiles.");
        }
    }

    //EFFECTS: sets the profile to the profile of user or creates new if not found
    //MODIFIES: this
    public Profile findOrCreateProfile() {
        String name = this.loginLayout.getLoginInput();
        this.profile = new Profile();
        if (this.reader.findProfile(name) == null) {
            this.profile.setName(name);
            this.profiles.add(this.profile);
            if (SignUpAlert.displaySignUpAlert(name)) {
                loadExampleDatabase();
            }
        } else {
            this.profile = this.reader.findProfile(name);
        }
        return this.profile;
    }

    //EFFECTS: loads example data base
    //MODIFIES: this
    private void loadExampleDatabase() {
        SingleEntry entry1 = new SingleEntry("toboggan", "sled",
                "verb is 'to toboggan'", "riding down a hill with a sled");
        SingleEntry entry2 = new SingleEntry("eh?", "'right?'",
                "does not have to be used as question", "it's cold today, eh?");
        SingleEntry entry3 = new SingleEntry("ubiquitous", "everywhere",
                "yü-ˈbi-kwə-təs", "");
        this.profile.getDatabase().addEntry(entry1);
        this.profile.getDatabase().addEntry(entry2);
        this.profile.getDatabase().addEntry(entry3);
    }

    //EFFECTS: sets the database table of database layout
    //MODIFIES: this
    public void setDatabaseTable() {
        if (this.profile != null) {
            this.databaseLayout.getTableItems().clear();
            for (SingleEntry entry : this.profile.getDatabase().getEntries()) {
                this.databaseLayout.getTableItems().add(entry);
            }
        }
    }

    //EFFECTS: updates success rate line chart in profile layout
    //MODIFIES: this
    public void updateProfileLineChart() {
        if (this.profile != null) {
            ArrayList<Double> successRates = this.profile.getSuccessRates();
            this.profileLayout.clearLineChart();
            for (int i = 0, k = 0; i < successRates.size(); i++, k++) {
                this.profileLayout.fillSeriesWithData(k, successRates.get(i));
            }
            this.profileLayout.addSeriesToLineChart();
        }
    }

    //EFFECTS: adds current session to records, closes program and writes everything into Json File
    //MODIFIES: this
    private void closeProgram() {
        if (this.profile != null) {
            this.profile.addSuccessRateOfSession();
            try {
                Writer.write(this.profiles);
            } catch (IOException e) {
                System.out.println("Something went wrong with closing the program.");
            }
        }
        this.window.close();
    }
}


//Notes: the stage is the entire window, the scene is the content in the window
//Notes: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of loginLayout.setConstraints(loginLabel, 0, 0);?

//TODO: change Main such that it now runs VocabAppGuiFX