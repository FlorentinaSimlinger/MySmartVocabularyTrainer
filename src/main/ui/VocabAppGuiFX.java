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

    Button loginButton;
    Button mainButton;
    Button testButton;
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
        window = primaryStage;
        primaryStage.setTitle("Welcome to MySmartVocabularyTrainer!");

        //LOGIN
        loginLabel = new Label("Welcome to the login scene.");
        loginButton = new Button("Go to main scene");
        loginButton.setOnAction(this);

        VBox loginLayout = new VBox(20);
        loginLayout.getChildren().addAll(loginButton, loginLabel);
        loginScene = new Scene(loginLayout, 300, 250);

        //MAIN
        mainLabel = new Label("Welcome to the main page.");
        mainButton = new Button("Go to test scene.");
        mainButton.setOnAction(this);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(mainLabel, mainButton);
        mainScene = new Scene(mainLayout, 300, 250);

        //TEST
        testButton = new Button("Go back to main scene");
        testButton.setOnAction(this);

        VBox testLayout = new VBox(20);
        testLayout.getChildren().add(testButton);
        testScene = new Scene(testLayout, 600, 600);

        window.setScene(loginScene);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == loginButton) {
            window.setScene(mainScene);
            System.out.println("Go to main scene");
        } else if (event.getSource() == mainButton) {
            window.setScene(testScene);
            System.out.println("Go to test scene");
        } else if (event.getSource() == testButton) {
            window.setScene(mainScene);
            System.out.println("Go back to main scene");
        }
    }
}


//Notes: the stage is the entire window, the scene is the content in the window