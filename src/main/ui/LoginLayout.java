package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

//represents the layout for login
public class LoginLayout extends Layout {
    private TextField loginInput;
    private Scene loginScene;
    private VBox loginLayout;
    public static final String LOGIN = "login";

    //EFFECTS: constructs a login layout
    public LoginLayout() {
        this.loginLayout = new VBox();
        this.loginLayout.setSpacing(20);
        this.loginLayout.setAlignment(Pos.TOP_CENTER);

        //labels
        Label welcomeLabel = new Label("Welcome to MySmartVocabularyTrainer!");
        Label nameLabel = new Label("Please enter your name to continue");
        welcomeLabel.setPadding(new Insets(100, 0, 50, 0));

        //login text field
        this.loginInput = new TextField();
        this.loginInput.setPromptText("name");
        this.loginInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                dispatchEvent(e, LOGIN);
            }
        });
        this.loginInput.setMaxWidth(250);


        loginLayout.getChildren().addAll(welcomeLabel, nameLabel, loginInput);
        this.loginScene = new Scene(loginLayout, 850, 500);

        //css
        this.loginScene.getStylesheets().add("ui/LoginStyle.css");
        welcomeLabel.getStyleClass().add("label-welcome");
    }

    public Scene getLoginScene() {
        return loginScene;
    }


    public String getLoginInput() {
        return this.loginInput.getText().trim();
    }

    @Override
    protected VBox getNode() {
        return this.loginLayout;
    }
}