package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
    private Profile appProfile;
    private ArrayList<Profile> profiles;
    private ObservableList<SingleEntry> databaseTable;
    private Reader reader;
    private Stage window;


    //public VocabAppGui() { launch(args); }


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

        //instantiating layouts and adding event listeners
        this.loginLayout = new LoginLayout();


        this.rootLayout = new RootLayout();
        addEventListenersToLayout(this.rootLayout);
        this.mainLayout = new MainLayout();
        addEventListenersToLayout(this.mainLayout);
        this.searchLayout = new SearchLayout();
        addEventListenersToLayout(this.searchLayout);
        this.testLayout = new TestLayout();
        addEventListenersToLayout(this.testLayout);
        this.databaseLayout = new DatabaseLayout();
        addEventListenersToLayout(this.databaseLayout);
        this.aboutLayout = new AboutLayout();
        addEventListenersToLayout(this.aboutLayout);
        this.profileLayout = new ProfileLayout();
        addEventListenersToLayout(this.profileLayout);

        this.loginLayout.addEventListener("login",
                e -> {
                    this.appProfile = loginLayout.findOrCreateProfile(reader, profiles);
                    databaseLayout.setProfile(this.appProfile);
                    databaseLayout.addTable();
                    this.rootLayout.setChildPane(this.mainLayout);
                    Scene rootScene = new Scene(this.rootLayout.getNode(), 920, 600);
                    this.window.setScene(rootScene);
                });

        //TODO: this was previously in LoginLayout.findOrCreateProfile(), is there a better way?
//        databaseLayout.table.setItems(databaseLayout.getTableItems());

        //setting scenes for window
        //TODO: this might be a problem because overwritten by setScene(loginLayout...)
        //TODO: this was previously in LoginLayout.findOrCreateProfile(). is there a better way?
//        this.rootLayout.setChildPane(this.mainLayout);
//        Scene rootScene = new Scene(this.rootLayout.getRootLayout(), 920, 600);
//        this.window.setScene(rootScene);

        this.window.setScene(this.loginLayout.getLoginScene());

        this.window.show();
    }

    public void addEventListenersToLayout(Layout layout) {
        layout.addEventListener("database", e -> this.rootLayout.setChildPane(this.databaseLayout));
        layout.addEventListener("search", e -> this.rootLayout.setChildPane(this.searchLayout));
        layout.addEventListener("test", e -> this.rootLayout.setChildPane(this.testLayout));
        layout.addEventListener("quit", e -> closeProgram());
        layout.addEventListener("main", e -> this.rootLayout.setChildPane(this.mainLayout));
        layout.addEventListener("about", e -> this.rootLayout.setChildPane(this.aboutLayout));
        layout.addEventListener("profile", e -> this.rootLayout.setChildPane(this.profileLayout));
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

    //EFFECTS: adds current session to records, closes program and writes everything into Json File
    //MODIFIES: this
    private void closeProgram() {
        this.appProfile.addSuccessRateOfSession();
        try {
            Writer.write(this.profiles);
        } catch (IOException e) {
            System.out.println("Something went wrong with closing the program.");
        }
        this.window.close();
    }

}


//Notes: the stage is the entire window, the scene is the content in the window
//Notes: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of loginLayout.setConstraints(loginLabel, 0, 0);?

//TODO: change Main such that it now runs VocabAppGuiFX