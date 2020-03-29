package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

//represents the root layout
public class RootLayout extends Layout {
    private BorderPane rootLayout;
    private Menu moreMenu;

    //EFFECTS: constructs the root layout
    public RootLayout() {
        super("", "", "");
        rootLayout = new BorderPane();
        rootLayout.setPadding(new Insets(10, 10, 10, 10));
        addMoreMenu();
        addMenuBar();
    }

    //EFFECTS: adds the More menu
    //MODIFIES: this
    private void addMoreMenu() {
        moreMenu = new Menu("MORE");
        MenuItem databaseMenuItem = new MenuItem("DATABASE");
        MenuItem searchMenuItem = new MenuItem("SEARCH");
        MenuItem testMenuItem = new MenuItem("TEST");
        MenuItem quitMenuItem = new MenuItem("QUIT");
        moreMenu.getItems().addAll(databaseMenuItem, searchMenuItem, testMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(quitMenuItem);
        databaseMenuItem.setOnAction(e -> rootLayout.setCenter(new DatabaseLayout("DATABASE", "Add", "Delete")));
        searchMenuItem.setOnAction(e -> rootLayout.setCenter(searchLayout));
        testMenuItem.setOnAction(e -> rootLayout.setCenter(testLayout));
        quitMenuItem.setOnAction(e -> closeProgram());
    }

    //EFFECTS: sets Menu bar
    //MODIFIES: this
    private void addMenuBar() {
        //Menus without MenuItems don't fire, therefore workaround,
        //SOURCE: https://stackoverflow.com/questions/48017645/event-handler-in-javafx-for-menu
        Label mainMenuLabel = new Label("MAIN");
        Label aboutMenuLabel = new Label("ABOUT");
        Label profileMenuLabel = new Label("PROFILE");
        Menu mainMenu = new Menu("", mainMenuLabel);
        Menu aboutMenu = new Menu("", aboutMenuLabel);
        Menu profileMenu = new Menu("", profileMenuLabel);

        mainMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(mainLayout));
        aboutMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(aboutLayout));
        profileMenuLabel.setOnMouseClicked(mouseEvent -> {
            rootLayout.setCenter(profileLayout);
        });

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(mainMenu, aboutMenu, profileMenu, moreMenu);

        HBox menuBarHBox = new HBox();
        menuBarHBox.getChildren().add(menuBar);
        menuBarHBox.setAlignment(Pos.CENTER_RIGHT);

        rootLayout.setTop(menuBarHBox);
    }
}

//TODO: change layouts