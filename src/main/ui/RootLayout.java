package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

//represents the root layout, all other layouts are displayed at center of this layout
public class RootLayout extends Layout {
    private BorderPane rootLayout;
    private MenuItem databaseMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem testMenuItem;
    private MenuItem quitMenuItem;
    private Label mainMenuLabel;
    private Label aboutMenuLabel;
    private Label profileMenuLabel;
    private Menu moreMenu;
    private Menu mainMenu;
    private Menu aboutMenu;
    private Menu profileMenu;

    //constructs a root layout
    public RootLayout() {
        this.rootLayout = new BorderPane();
        this.rootLayout.setPadding(new Insets(10, 10, 10, 10));

        createMoreMenu();
        createOtherMenus();

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(mainMenu, aboutMenu, profileMenu, moreMenu);

        HBox menuBarHBox = new HBox();
        menuBarHBox.getChildren().add(menuBar);
        menuBarHBox.setAlignment(Pos.CENTER_RIGHT);

        addEventTriggers();

        this.rootLayout.setTop(menuBarHBox);
    }

    //EFFECTS: creates main, about, and profile menu
    //MODIFIES: this
    //NOTE: Menus without MenuItems don't fire, therefore workaround,
    //SOURCE: https://stackoverflow.com/questions/48017645/event-handler-in-javafx-for-menu
    private void createOtherMenus() {
        this.mainMenuLabel = new Label("MAIN");
        this.aboutMenuLabel = new Label("ABOUT");
        this.profileMenuLabel = new Label("PROFILE");

        this.mainMenu = new Menu("", mainMenuLabel);
        this.aboutMenu = new Menu("", aboutMenuLabel);
        this.profileMenu = new Menu("", profileMenuLabel);
    }

    //EFFECTS: creates the more menu, holding the search, test and quit menus
    //MODIFIES: this
    private void createMoreMenu() {
        this.moreMenu = new Menu("MORE");
        this.databaseMenuItem = new MenuItem("DATABASE");
        this.searchMenuItem = new MenuItem("SEARCH");
        this.testMenuItem = new MenuItem("TEST");
        this.quitMenuItem = new MenuItem("QUIT");

        this.moreMenu.getItems().add(databaseMenuItem);
        this.moreMenu.getItems().add(searchMenuItem);
        this.moreMenu.getItems().add(testMenuItem);
        this.moreMenu.getItems().add(new SeparatorMenuItem());
        this.moreMenu.getItems().add(quitMenuItem);
    }

    //EFFECTS: adds triggers to the different menu items
    //MODIFIES: this
    private void addEventTriggers() {
        this.databaseMenuItem.setOnAction(e -> handleEvent(e, "database"));
        this.searchMenuItem.setOnAction(e -> handleEvent(e, "search"));
        this.testMenuItem.setOnAction(e -> handleEvent(e, "test"));
        this.quitMenuItem.setOnAction(e -> handleEvent(e, "quit"));
        this.mainMenuLabel.setOnMouseClicked(e -> handleEvent(e, "main"));
        this.aboutMenuLabel.setOnMouseClicked(e -> handleEvent(e, "about"));
        this.profileMenuLabel.setOnMouseClicked(e -> handleEvent(e, "profile"));
    }

    //EFFECTS: sets the center of the root layout
    //MODIFIES: this
    public void setChildPane(Layout layout) {
        rootLayout.setCenter(layout.getNode());
    }

    @Override
    protected BorderPane getNode() {
        return this.rootLayout;
    }
}
