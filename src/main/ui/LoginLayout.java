package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

//represents the layout for login
public class LoginLayout extends Layout {
    private TextField loginInput;
    private Scene loginScene;
    private GridPane loginLayout;

    //EFFECTS: constructs a login layout
    public LoginLayout() {
        this.loginLayout = new GridPane();
        this.loginLayout.setPadding(new Insets(10, 10, 10, 10));
        this.loginLayout.setVgap(20);
        this.loginLayout.setHgap(10);

        //login label
        Label loginLabel = new Label("Welcome to MySmartVocabularyTrainer! "
                + "\n\nPlease enter your name and hit enter to continue.");
        GridPane.setConstraints(loginLabel, 4, 0);

        //login text field
        this.loginInput = new TextField();
        this.loginInput.setPromptText("name");
        GridPane.setConstraints(loginInput, 4, 1);

        //login button
        Button loginToMainButton = new Button("Continue");
        loginToMainButton.setOnAction(e -> dispatchEvent(e, "login"));
        GridPane.setConstraints(loginToMainButton, 5, 2);

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        this.loginScene = new Scene(loginLayout, 850, 500);
        this.loginScene.getStylesheets().add("ui/LoginStylesheet.css");
    }

    public Scene getLoginScene() {
        return loginScene;
    }


    public String getLoginInput() {
        return this.loginInput.getText().trim();
    }

    @Override
    protected GridPane getNode() {
        return this.loginLayout;
    }
}