package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Profile;
import model.SingleEntry;
import persistence.Reader;

import java.util.ArrayList;
//
//public class LoginLayout extends Layout {
//    private TextField loginInput;
//
//
//    public LoginLayout(String labelString, String buttonString1, String buttonString2, Reader r, ArrayList<Profile> p) {
//        super(labelString, buttonString1, buttonString2);
//        GridPane loginLayout = new GridPane();
//        loginLayout.setPadding(new Insets(10, 10, 10, 10));
//        loginLayout.setVgap(8);
//        loginLayout.setHgap(10);
//        GridPane.setConstraints(label, 0, 0);
//        loginInput = new TextField();
//        loginInput.setPromptText("name");
//        GridPane.setConstraints(loginInput, 0, 1);
//        GridPane.setConstraints(button1, 1, 2);
//        button1.setOnAction(e -> profile = findOrCreateProfile(r, p));
//
//        loginLayout.getChildren().addAll(label, loginInput, button1);
//        loginScene = new Scene(loginLayout, 850, 500);
//    }
//
//    //EFFECTS: initializes window and login
//    //MODIFIES: this
//    public void login(Stage primaryStage) {
//        window = primaryStage;
//        primaryStage.setTitle("MySmartVocabularyTrainer");
//        window.setOnCloseRequest(e -> closeProgram());
//        window.setScene(loginScene);
//        window.show();
//    }
//
//    //EFFECTS: sets the profile to the profile of user or creates new if not found
//    //MODIFIES: this
//    private Profile findOrCreateProfile(Reader reader, ArrayList<Profile> profiles) {
//        String name = loginInput.getText().trim(); //Note: trim excludes any space at end
//        profile = new Profile();
//        if (reader.findProfile(name) == null) {
//            profile.setName(name);
//            profiles.add(profile);
//            if (SignUpAlert.displaySignUpAlert(name)) {
//                loadExampleDatabase();
//            }
//        } else {
//            profile = reader.findProfile(name);
//        }
//        table.setItems(getTableItems());
//        window.setScene(rootScene);
//        return profile;
//    }
//
//    //EFFECTS: loads example data base
//    //MODIFIES: this
//    private void loadExampleDatabase() {
//        SingleEntry entry1 = new SingleEntry("toboggan", "sled",
//                "verb is 'to toboggan'", "riding down a hill with a sled");
//        SingleEntry entry2 = new SingleEntry("eh?", "'right?'",
//                "does not have to be used as question", "it's cold today, eh?");
//        SingleEntry entry3 = new SingleEntry("ubiquitous", "everywhere",
//                "yü-ˈbi-kwə-təs", "");
//        profile.getDatabase().addEntry(entry1);
//        profile.getDatabase().addEntry(entry2);
//        profile.getDatabase().addEntry(entry3);
//    }
//}

//TODO: getTableItems() is in DatabaseLayout, how could I best access it?


public class LoginLayout extends Layout {
    private TextField loginInput;
    private Scene loginScene;
    private GridPane loginLayout;


    public LoginLayout() {
        this.loginLayout = new GridPane();
        this.loginLayout.setPadding(new Insets(10, 10, 10, 10));
        this.loginLayout.setVgap(8);
        this.loginLayout.setHgap(10);

        //login label
        Label loginLabel = new Label("Welcome to MySmartVocabularyTrainer! \nPlease enter your name to continue.");
        GridPane.setConstraints(loginLabel, 0, 0);

        //login text field
        this.loginInput = new TextField();
        this.loginInput.setPromptText("name");
        GridPane.setConstraints(loginInput, 0, 1);

        //login button
        Button loginToMainButton = new Button("Continue");
        loginToMainButton.setOnAction(e -> handleEvent(e, "login"));
        GridPane.setConstraints(loginToMainButton, 1, 2);

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        this.loginScene = new Scene(loginLayout, 850, 500);
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    //EFFECTS: sets the profile to the profile of user or creates new if not found
    //MODIFIES: this
    public Profile findOrCreateProfile(Reader reader, ArrayList<Profile> profiles) {
        String name = loginInput.getText().trim(); //trim excludes any space at end
        layoutProfile = new Profile();
        if (reader.findProfile(name) == null) {
            layoutProfile.setName(name);
            profiles.add(layoutProfile);
            if (SignUpAlert.displaySignUpAlert(name)) {
                loadExampleDatabase();
            }
        } else {
            layoutProfile = reader.findProfile(name);
        }
        //table.setItems(databaseLayout.getTableItems());
        //window.setScene(rootScene);
        return layoutProfile;
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
        layoutProfile.getDatabase().addEntry(entry1);
        layoutProfile.getDatabase().addEntry(entry2);
        layoutProfile.getDatabase().addEntry(entry3);
    }

    @Override
    protected GridPane getNode() {
        return this.loginLayout;
    }
}