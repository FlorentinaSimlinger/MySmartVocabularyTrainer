package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    TextField loginInput;
    ComboBox<String> comboBox;

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
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        loginLayout.setVgap(8);
        loginLayout.setHgap(10);
        //login label
        loginLabel = new Label("Welcome to MySmartVocabularyTrainer! \nPlease enter your name to continue.");
        GridPane.setConstraints(loginLabel, 0, 0);

        //login text field
        loginInput = new TextField();
        loginInput.setPromptText("name");
        GridPane.setConstraints(loginInput, 0, 1);

        //login button
        loginToMainButton = new Button("Continue");
        GridPane.setConstraints(loginToMainButton, 1, 2);
        loginToMainButton.setOnAction(this);

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        loginScene = new Scene(loginLayout, 500, 500);

        //MAIN
        mainLabel = new Label("Welcome to the main page.");
        mainToTestButton = new Button("Go to test scene");
        mainToTestButton.setOnAction(this);
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(this);

        //create comboBox
        comboBox = new ComboBox<>();
        //get Items returns the ObservableList object which you can add items into
        comboBox.getItems().add("DATABASE");
        comboBox.getItems().add("SEARCH");
        comboBox.getItems().add("TEST");
        //set default value of choice box
        comboBox.setPromptText("MORE");
        //listen for selection changes
        comboBox.setOnAction(this);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(comboBox, mainLabel, mainToTestButton, mainToQuitButton);
        mainScene = new Scene(mainLayout, 300, 250);

        //MAIN TOP (doesn't work atm)
        HBox topMenu = new HBox();
        Button topMenuToAboutButton = new Button("ABOUT");
        Button topMenuToProfileButton = new Button("PROFILE");
        Button topMenuToMoreButton = new Button("MORE");
        topMenu.getChildren().addAll(topMenuToAboutButton, topMenuToProfileButton, topMenuToMoreButton);

        //BORDER PANE WITH TOPMENU (doesn't work atm)
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);

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
            try {
                isString(loginInput, loginInput.getText());
            } catch (Exception e) {
                System.out.println("Do not enter an integer.");
            }
            boolean result = SignUpAlert.displaySignUpAlert();
            window.setScene(mainScene);
            System.out.println("TODO: implement result: " + result);
        } else if (event.getSource() == mainToTestButton) {
            window.setScene(testScene);
        } else if (event.getSource() == mainToQuitButton) {
            closeProgram();
        } else if (event.getSource() == comboBox) {
            System.out.println("TODO: implement choice of " + comboBox.getValue());
        } else if (event.getSource() == testToMainButton) {
            window.setScene(mainScene);
        }
    }

    private boolean isString(TextField input, String message) throws Exception {
        try {
            int name = Integer.parseInt(input.getText());
            throw new Exception("You entered an integer.");
        } catch (NumberFormatException e) {
            System.out.println("This is expected since you inputed a string");
            return true;
        }
    }

    private void closeProgram() {
        //add save here
        System.out.println("TODO: implement save here");
        window.close();
    }
}


//Notes: the stage is the entire window, the scene is the content in the window
//TODO: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of  loginLayout.setConstraints(loginLabel, 0, 0);?