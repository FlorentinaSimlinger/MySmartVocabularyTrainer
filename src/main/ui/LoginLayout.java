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
        loginToMainButton.setOnAction(e -> dispatchEvent(e, "login"));
        GridPane.setConstraints(loginToMainButton, 1, 2);

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        this.loginScene = new Scene(loginLayout, 850, 500);
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