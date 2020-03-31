package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Profile;
import persistence.Reader;
import persistence.Writer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Represents a vocabulary trainer application
public class VocabAppGuiFX extends Application implements EventHandler<ActionEvent> {
    private RootLayout rootLayout;
    private MainLayout mainLayout;
    private TestLayout testLayout;
    private DatabaseLayout databaseLayout;
    private SearchLayout searchLayout;
    private AboutLayout aboutLayout;
    private LoginLayout loginLayout;
    private Profile profile;
    private ArrayList<Profile> profiles;
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
        this.loginLayout.addEventListener("login", e -> this.profile = findOrCreateProfile());

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

        window.setScene(loginLayout.getLoginScene());


        //TODO: figure out what to do with this
        loginLayout.login(primaryStage);
    }

    public void addEventListenersToLayout(Layout layout) {
        layout.addEventListener("database", e -> this.rootLayout.setChildPane(databaseLayout));
        layout.addEventListener("search", e -> this.rootLayout.setChildPane(searchLayout));
        layout.addEventListener("test", e -> this.rootLayout.setChildPane(testLayout));
        layout.addEventListener("quit", e -> closeProgram());
        layout.addEventListener("main", e -> this.rootLayout.setChildPane(mainLayout));
    }


    //EFFECTS: reads the profiles
    //MODIFIES: this
    private void loadProfiles() {
        try {
            reader = new Reader();
            profiles = new ArrayList<>(Arrays.asList(reader.getProfiles()));
        } catch (IOException e) {
            System.out.println("Something went wrong with loading the profiles.");
        }
    }

    //EFFECTS: adds current session to records, closes program and writes everything into Json File
    //MODIFIES: this
    private void closeProgram() {
        profile.addSuccessRateOfSession();
        try {
            Writer.write(profiles);
        } catch (IOException e) {
            System.out.println("Something went wrong with closing the program.");
        }
        window.close();
    }


}


//Notes: the stage is the entire window, the scene is the content in the window
//Notes: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of loginLayout.setConstraints(loginLabel, 0, 0);?

//TODO: change Main such that it now runs VocabAppGuiFX