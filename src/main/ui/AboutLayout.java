package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//represents the layout for the about menu
public class AboutLayout extends Layout {
    VBox aboutLayout;

    //EFFECTS: constructs an about layout
    public AboutLayout() {
        this.aboutLayout = new VBox();
        aboutLayout.setAlignment(Pos.CENTER);
        Label aboutLabel = new Label("ABOUT");

        Label aboutTextLabel = new Label("This is my 210 project!");
        aboutLayout.getChildren().addAll(aboutLabel, aboutTextLabel);
    }

    @Override
    protected VBox getNode() {
        return this.aboutLayout;
    }
}

