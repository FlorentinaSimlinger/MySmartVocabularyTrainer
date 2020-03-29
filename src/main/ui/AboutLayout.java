package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AboutLayout extends Layout {
    VBox aboutLayout;

    public AboutLayout(String labelString) {
        super(labelString, "", "");
        aboutLayout = new VBox();
        Label label1 = addLabel("This is my 210 project!");
        aboutLayout.getChildren().addAll(label, label1);
        aboutLayout.setAlignment(Pos.CENTER);
    }
}

