package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VocabAppGuiFX extends Application implements EventHandler<ActionEvent> {

    Button loginToMainButton;
    Button mainToTestButton;
    Button mainToQuitButton;
    Button testToMainButton;
    Scene loginScene;
    Scene mainScene;
    Scene profileScene;
    Scene databaseScene;
    Scene searchScene;
    Scene testScene;
    Label loginLabel;
    Label mainLabel;
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //SET WINDOW
        window = primaryStage;
        primaryStage.setTitle("Welcome to MySmartVocabularyTrainer!");
        window.setOnCloseRequest(e -> closeProgram());

        //LOGIN
        loginLabel = new Label("Welcome to the login scene.");
        loginToMainButton = new Button("Go to main scene");
        loginToMainButton.setOnAction(this);

        VBox loginLayout = new VBox(20);
        loginLayout.getChildren().addAll(loginToMainButton, loginLabel);
        loginScene = new Scene(loginLayout, 300, 250);

        //MAIN
        mainLabel = new Label("Welcome to the main page.");
        mainToTestButton = new Button("Go to test scene");
        mainToTestButton.setOnAction(this);
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(this);


        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(mainLabel, mainToTestButton, mainToQuitButton);
        mainScene = new Scene(mainLayout, 300, 250);

        //TEST
        testToMainButton = new Button("Go back to main scene");
        testToMainButton.setOnAction(this);

        VBox testLayout = new VBox(20);
        testLayout.getChildren().add(testToMainButton);
        testScene = new Scene(testLayout, 600, 600);

        //DISPLAY WINDOW
        window.setScene(loginScene);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == loginToMainButton) {
            boolean result = SignUpAlert.displaySignUpAlert();
            window.setScene(mainScene);
            System.out.println("TODO: implement result: " + result);
        } else if (event.getSource() == mainToTestButton) {
            window.setScene(testScene);
        } else if (event.getSource() == mainToQuitButton) {
            closeProgram();
        } else if (event.getSource() == testToMainButton) {
            window.setScene(mainScene);
        }
    }

    private void closeProgram() {
        //add save here
        System.out.println("TODO: implement save here");
        window.close();
    }
}


//Notes: the stage is the entire window, the scene is the content in the window