package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AboutLayout extends Layout {
    VBox aboutLayout;

    public AboutLayout(String labelString) {
        super(labelString, "", "");
        aboutLayout = new VBox();
        aboutLayout.getChildren().add(label);
        aboutLayout.setAlignment(Pos.CENTER);
    }
}

//TODO: gibt es einen besseren Weg, damit ich nicht buttons zum layout hinzufügen muss?
//TODO: soll ich helper methods machen?
