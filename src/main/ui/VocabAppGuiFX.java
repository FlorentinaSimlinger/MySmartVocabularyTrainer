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
        LoginLayout loginLayout = new LoginLayout("Welcome to..", "Continue", "",
                reader, profiles);
        loginLayout.login(primaryStage);
        rootLayout = new RootLayout();
        String mainLabel = "Welcome! To get started, enter a word or phrase you'd like to learn,"
                + "\nthe synonym you're familiar with, a comment and an example sentence if you'd like.";
        mainLayout = new MainLayout(mainLabel, "Add", "Test myself!");
        String testLabel = "TEST\n To start testing, press 'Start!'. Hit Enter to get next question. Press"
                + "'Return to main' to return to main page.";
        testLayout = new TestLayout(testLabel, "Start!", "Return to main");
        DatabaseLayout databaseLayout = new DatabaseLayout("DATABASE");
        SearchLayout searchLayout = new SearchLayout("SEARCH", "Search!");
        AboutLayout aboutLayout = new AboutLayout("ABOUT");
        ProfileLayout profileLayout = new ProfileLayout("MY PROFILE", "Delete profile");



        mainLayout.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> rootLayout.setCenter(testLayout));

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource().equals("testButton")) {
            rootLayout.setCenter(testLayout);
        } else if (event.getSource().equals("quit")) {
            closeProgram();
        }
    }


    public class ButtonHandler implements EventHandler<ActionEvent> {
        public ButtonHandler() {
        }

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == "testButton") {
                rootLayout.setCenter(mainLayout);
            }

        }
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
            System.out.println("Unfortunately an error occurred.");
        }
        window.close();
    }


}




//Notes: the stage is the entire window, the scene is the content in the window
//Notes: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of loginLayout.setConstraints(loginLabel, 0, 0);?

//TODO: change Main such that it now runs VocabAppGuiFX