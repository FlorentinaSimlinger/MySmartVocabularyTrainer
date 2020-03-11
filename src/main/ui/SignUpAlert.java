package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignUpAlert {

    public static void displaySignUpAlert() {
        Stage window = new Stage();

        //blocking user interaction with other windows until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sign up");
        window.setMinWidth(250);

        Label label = new Label("You do not have have a profile yet, we set a new one up.");
        Button closeButton = new Button("Continue");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}

//Notes: use ctr + q to get more info
