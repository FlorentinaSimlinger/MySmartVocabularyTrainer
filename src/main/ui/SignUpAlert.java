package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignUpAlert {

    static boolean answer;

    //EFFECTS: displays alert that new profile is created, returns true if sample data is to be loaded, false otherwise
    public static boolean displaySignUpAlert() {
        Stage window = new Stage();

        //blocking user interaction with other windows until this window is closed
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sign up");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Thank you for signing up for us. We have created a new profile for you. Would you like to " +
                "continue with sample data? You can also decide later to add sample data.");

        //Yes and no buttons
        Button yesButton = new Button("Continue with sample database");
        Button noButton = new Button("No thanks");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}

//Notes: use ctr + q to get more info
