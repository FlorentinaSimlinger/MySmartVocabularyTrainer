package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private MenuBar menuBar;
    private HBox menuBarHBox;
    public static final String EVENT_MAIN = "main";
    public static final String EVENT_ABOUT = "about";
    public static final String EVENT_PROFILE = "profile";
    public static final String EVENT_TEST = "test";
    public static final String EVENT_SEARCH = "search";
    public static final String EVENT_QUIT = "quit";
    public static final String EVENT_DATABASE = "database";

    //constructs a root layout
    public RootLayout() {
        this.rootLayout = new BorderPane();
        this.rootLayout.setPadding(new Insets(10, 0, 10, 0));

        createMoreMenu();
        createOtherMenus();

        this.menuBar = new MenuBar();
        this.menuBar.getMenus().addAll(this.mainMenu, this.aboutMenu, this.profileMenu, this.moreMenu);

        this.menuBarHBox = new HBox();
        this.menuBarHBox.getChildren().add(menuBar);
        this.menuBarHBox.setAlignment(Pos.CENTER_RIGHT);

        addEventTriggers();
        showMoreMenu();
        this.rootLayout.getStylesheets().add("ui/RootStyle.css");

        this.rootLayout.setTop(this.menuBarHBox);
    }

    //EFFECTS: shows more menu when mouse is hovering over it
    //SOURCE: https://stackoverflow.com/questions/47585211/a-menu-that-triggers-purely-on-hover-in-javafx
    //TODO: fix that more menu doesn't show when mouse between other menus
    private void showMoreMenu() {
        Node parentNode = this.menuBarHBox.getChildren().get(0);
        Menu menu = this.menuBar.getMenus().get(3);
        parentNode.setOnMouseMoved(e -> menu.show());
    }

    //EFFECTS: creates the more menu, holding the search, test and quit menus
    //MODIFIES: this
    private void createMoreMenu() {
        this.moreMenu = new Menu("MORE");
        this.databaseMenuItem = new MenuItem("DATABASE");
        this.searchMenuItem = new MenuItem("SEARCH");
        this.testMenuItem = new MenuItem("TEST");
        this.quitMenuItem = new MenuItem("QUIT");

        this.moreMenu.getItems().add(this.databaseMenuItem);
        this.moreMenu.getItems().add(this.searchMenuItem);
        this.moreMenu.getItems().add(this.testMenuItem);
        this.moreMenu.getItems().add(new SeparatorMenuItem());
        this.moreMenu.getItems().add(this.quitMenuItem);
    }

    //EFFECTS: creates main, about, and profile menu
    //MODIFIES: this
    //NOTE: Menus without MenuItems don't fire, therefore workaround
    //SOURCE: https://stackoverflow.com/questions/48017645/event-handler-in-javafx-for-menu
    private void createOtherMenus() {
        this.mainMenuLabel = new Label("MAIN");
        this.aboutMenuLabel = new Label("ABOUT");
        this.profileMenuLabel = new Label("PROFILE");

        this.mainMenu = new Menu("", this.mainMenuLabel);
        this.aboutMenu = new Menu("", this.aboutMenuLabel);
        this.profileMenu = new Menu("", this.profileMenuLabel);
    }

    //EFFECTS: adds triggers to the different menu items
    //MODIFIES: this
    private void addEventTriggers() {
        this.databaseMenuItem.setOnAction(e -> dispatchEvent(e, EVENT_DATABASE));
        this.searchMenuItem.setOnAction(e -> dispatchEvent(e, EVENT_SEARCH));
        this.testMenuItem.setOnAction(e -> dispatchEvent(e, EVENT_TEST));
        this.quitMenuItem.setOnAction(e -> dispatchEvent(e, EVENT_QUIT));
        this.mainMenuLabel.setOnMouseClicked(e -> dispatchEvent(e, EVENT_MAIN));
        this.aboutMenuLabel.setOnMouseClicked(e -> dispatchEvent(e, EVENT_ABOUT));
        this.profileMenuLabel.setOnMouseClicked(e -> dispatchEvent(e, EVENT_PROFILE));
    }

    //EFFECTS: sets the center of the root layout
    //MODIFIES: this
    public void setChildPane(Layout layout) {
        this.rootLayout.setCenter(layout.getNode());
    }

    @Override
    protected BorderPane getNode() {
        return this.rootLayout;
    }
}
