package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MenuBarTestClass extends Application {
    Stage window;
    BorderPane layout;
    Menu moreMenu;
    MenuBar menuBar;
    VBox mainVBox;
    Label mainLabel;
    Scene scene;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Test window");

        //layout
        layout = new BorderPane();

        //More menu
        moreMenu = new Menu("MORE");

        //Menu items
        moreMenu.getItems().add(new MenuItem("DATABASE"));
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(new MenuItem("SEARCH"));
        moreMenu.getItems().add(new MenuItem("TEST"));


        //Main menu bar
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(moreMenu);

        //Main VBox with label
        mainVBox = new VBox();
        mainLabel = new Label("This is a label");
        mainVBox.getChildren().addAll(mainLabel);

        layout.setTop(menuBar);
        layout.setCenter(mainVBox);


        scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();

    }
}
