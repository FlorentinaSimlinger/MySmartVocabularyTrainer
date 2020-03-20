package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VocabAppGuiFX extends Application implements EventHandler<ActionEvent> {

    private Button loginToMainButton;
    private Button mainToTestButton;
    private Button mainToQuitButton;
    private Button testToMainButton;
    private Button databaseAddButton;
    private Button databaseDeleteButton;
    private Button searchButton;
    private Scene loginScene;
    private Scene rootScene;
    private Label loginLabel;
    private Label mainLabel;
    private Label aboutMenuLabel;
    private Label profileMenuLabel;
    private Label searchFoundLabel;
    private Label searchLabel;
    private Label searchNotFoundLabel;
    private Stage window;
    private TextField loginInput;
    private TextField descriptionInput;
    private TextField meaningInput;
    private TextField commentInput;
    private TextField exampleSentenceInput;
    private TextField searchInput;
    private TableView<SingleEntryExport> table;
    private BorderPane rootLayout;
    private BorderPane databaseLayout;
    private HBox databaseHBox;
    private VBox mainLayout;
    private VBox testLayout;
    private Menu moreMenu;
    private Menu aboutMenu;
    private Menu profileMenu;
    private MenuBar menuBar;
    private MenuItem databaseMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem testMenuItem;
    private VBox searchLayout;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //SET WINDOW
        window = primaryStage;
        primaryStage.setTitle("Welcome to MySmartVocabularyTrainer!");
        window.setOnCloseRequest(e -> closeProgram());

        //LOGIN
        GridPane loginLayout = new GridPane();
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        loginLayout.setVgap(8);
        loginLayout.setHgap(10);
        //login label
        loginLabel = new Label("Welcome to MySmartVocabularyTrainer! \nPlease enter your name to continue.");
        GridPane.setConstraints(loginLabel, 0, 0);

        //login text field
        loginInput = new TextField();
        loginInput.setPromptText("name");
        GridPane.setConstraints(loginInput, 0, 1);

        //login button
        loginToMainButton = new Button("Continue");
        GridPane.setConstraints(loginToMainButton, 1, 2);
        loginToMainButton.setOnAction(this);

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        loginScene = new Scene(loginLayout, 850, 500);

        //ROOT
        rootLayout = new BorderPane();

        //Menus
        moreMenu = new Menu("MORE");

        databaseMenuItem = new MenuItem("DATABASE");
        searchMenuItem = new MenuItem("SEARCH");
        testMenuItem = new MenuItem("TEST");
        moreMenu.getItems().add(databaseMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(searchMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(testMenuItem);
        databaseMenuItem.setOnAction(this);
        searchMenuItem.setOnAction(this);
        testMenuItem.setOnAction(this);

        //Menus without MenuItems don't fire, therefore workaround,
        //SOURCE: https://stackoverflow.com/questions/48017645/event-handler-in-javafx-for-menu
        aboutMenuLabel = new Label("ABOUT");
        profileMenuLabel = new Label("PROFILE");
        aboutMenu = new Menu("", aboutMenuLabel);
        profileMenu = new Menu("", profileMenuLabel);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(aboutMenu, profileMenu, moreMenu);

        rootLayout.setTop(menuBar);

        //Main Layout
        mainLayout = new VBox();
        mainLabel = new Label("Welcome to the main page.");
        mainToTestButton = new Button("Go to test scene");
        mainToTestButton.setOnAction(this);
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(this);
        mainLayout.getChildren().addAll(mainLabel, mainToTestButton, mainToQuitButton);

        rootLayout.setCenter(mainLayout);

        rootScene = new Scene(rootLayout, 900, 900);

        //TEST
        testToMainButton = new Button("Go back to main scene");
        testToMainButton.setOnAction(this);

        testLayout = new VBox(20);
        testLayout.getChildren().add(testToMainButton);

        //DATABASE
        //description column
        TableColumn<SingleEntryExport, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<SingleEntryExport, String>("description"));

        //meaning column
        TableColumn<SingleEntryExport, String> meaningColumn = new TableColumn<>("Meaning");
        meaningColumn.setMinWidth(200);
        meaningColumn.setCellValueFactory(new PropertyValueFactory<SingleEntryExport, String>("meaning"));

        //comment column
        TableColumn<SingleEntryExport, String> commentColumn = new TableColumn<>("Comment");
        commentColumn.setMinWidth(200);
        commentColumn.setCellValueFactory(new PropertyValueFactory<SingleEntryExport, String>("comment"));

        //sentence column
        TableColumn<SingleEntryExport, String> sentenceColumn = new TableColumn<>("Example Sentence");
        sentenceColumn.setMinWidth(200);
        sentenceColumn.setCellValueFactory(new PropertyValueFactory<SingleEntryExport, String>("exampleSentence"));

        //successRate column
        TableColumn<SingleEntryExport, Double> successRateColumn = new TableColumn<>("Success Rate");
        successRateColumn.setMinWidth(100);
        successRateColumn.setCellValueFactory(new PropertyValueFactory<SingleEntryExport, Double>("successRate"));

        //description input
        descriptionInput = new TextField();
        descriptionInput.setPromptText("description");
        descriptionInput.setMinWidth(100);

        //meaning input
        meaningInput = new TextField();
        meaningInput.setPromptText("meaning");
        meaningInput.setMinWidth(100);

        //comment input
        commentInput = new TextField();
        commentInput.setPromptText("comment");
        commentInput.setMinWidth(100);

        //example sentence input
        exampleSentenceInput = new TextField();
        exampleSentenceInput.setPromptText("example sentence");
        exampleSentenceInput.setMinWidth(100);

        //add and delete button
        databaseAddButton = new Button("Add");
        databaseAddButton.setOnAction(this);
        databaseDeleteButton = new Button("Delete");
        databaseDeleteButton.setOnAction(this);

        //HBox with add and delete option
        databaseHBox = new HBox();
        databaseHBox.setPadding(new Insets(10, 10, 10, 10));
        databaseHBox.setSpacing(10);
        databaseHBox.getChildren().addAll(descriptionInput, meaningInput, commentInput, exampleSentenceInput,
                databaseAddButton, databaseDeleteButton);

        table = new TableView<>();
        table.setItems(getSingleEntryExport());
        table.getColumns().addAll(descriptionColumn, meaningColumn, commentColumn, sentenceColumn, successRateColumn);

        databaseLayout = new BorderPane();
        databaseLayout.setCenter(table);
        databaseLayout.setBottom(databaseHBox);

        //SEARCH
        searchLayout = new VBox();
        searchLabel = new Label("SEARCH \nTo search your database, enter any word or phrase.");
        searchFoundLabel = new Label("We found the following entries for you.");
        searchNotFoundLabel = new Label("Oops, we could not find such entry. Would you like to add one?");
        searchButton = new Button("SEARCH");
        searchInput = new TextField();
        searchInput.setPromptText("word or phrase");
        searchLayout.getChildren().addAll(searchLabel, searchInput,
                searchFoundLabel, searchNotFoundLabel, searchButton);

        searchButton.setOnAction(this);

        //ABOUT
        VBox aboutLayout = new VBox();
        Label aboutLabel = new Label("This is my 210 project");
        aboutLayout.getChildren().add(aboutLabel);

        aboutMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(aboutLayout));


        //PROFILE
        VBox profileLayout = new VBox();
        Label profileLabel = new Label("MY PROFILE");
        Label profileSuccessRateLabel = new Label("Successrate");
        Label profileEntriesLabel = new Label("Entries");
        Label profileAchievementsLabel = new Label("Achievements");
        Label profileExportDataLabel = new Label("Export my data");
        Button profileDeleteProfileButton = new Button("DELETE MY PROFILE");

        profileLayout.getChildren().addAll(profileLabel, profileSuccessRateLabel, profileEntriesLabel,
                profileAchievementsLabel, profileExportDataLabel, profileDeleteProfileButton);
        profileMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(profileLayout));


        //DISPLAY WINDOW
        window.setScene(rootScene);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == loginToMainButton) {
            try {
                isString(loginInput, loginInput.getText());
            } catch (Exception e) {
                System.out.println("Do not enter an integer.");
            }
            boolean result = SignUpAlert.displaySignUpAlert();
            window.setScene(rootScene);
            System.out.println("implement result: " + result);
        } else if (event.getSource() == mainToTestButton) {
            rootLayout.setCenter(testLayout);
        } else if (event.getSource() == mainToQuitButton) {
            closeProgram();
        } else if (event.getSource() == testToMainButton) {
            rootLayout.setCenter(mainLayout);
        } else if (event.getSource() == databaseAddButton) {
            System.out.println("implement add to database");
            addButtonClicked();
        } else if (event.getSource() == databaseDeleteButton) {
            System.out.println("implement delete from database");
            deleteButtonClicked();
        } else if (event.getSource() == databaseMenuItem) {
            rootLayout.setCenter(databaseLayout);
        } else if (event.getSource() == searchMenuItem) {
            rootLayout.setCenter(searchLayout);
        } else if (event.getSource() == testMenuItem) {
            rootLayout.setCenter(testLayout);
        } else if (event.getSource() == searchButton) {
            System.out.println("Implement searching");
        }
    }

    private boolean isString(TextField input, String message) throws Exception {
        try {
            int name = Integer.parseInt(input.getText());
            throw new Exception("You entered an integer.");
        } catch (NumberFormatException e) {
            System.out.println("This is expected since you inputted a string");
            return true;
        }
    }

    //Add button clicked
    public void addButtonClicked() {
        SingleEntryExport singleEntryExport = new SingleEntryExport();
        singleEntryExport.setDescription(descriptionInput.getText());
        singleEntryExport.setMeaning(meaningInput.getText());
        singleEntryExport.setComment(commentInput.getText());
        singleEntryExport.setExampleSentence(exampleSentenceInput.getText());
        singleEntryExport.setSuccessRate(0);
        table.getItems().add(singleEntryExport);
        descriptionInput.clear();
        meaningInput.clear();
        commentInput.clear();
        exampleSentenceInput.clear();
    }

    //Delete button clicked
    public void deleteButtonClicked() {
        ObservableList<SingleEntryExport> selectedSingleEntryExports;
        ObservableList<SingleEntryExport> allSingleEntryExports;
        selectedSingleEntryExports = table.getSelectionModel().getSelectedItems();
        allSingleEntryExports = table.getItems();

        selectedSingleEntryExports.forEach(allSingleEntryExports::remove);
    }

    //Get all of the products
    public ObservableList<SingleEntryExport> getSingleEntryExport() {
        ObservableList<SingleEntryExport> singleEntryExports = FXCollections.observableArrayList();
        singleEntryExports.add(new SingleEntryExport("toboggan", "sled",
                "random comment", "random example sentence", 0.30));
        singleEntryExports.add(new SingleEntryExport("spinning wheels", "being exhausted",
                "another random comment", "another random example sentence", 0.40));
        return singleEntryExports;
    }

    private void closeProgram() {
        //add save here
        System.out.println("implement save here");
        window.close();
    }
}


//Notes: the stage is the entire window, the scene is the content in the window
//TODO: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of  loginLayout.setConstraints(loginLabel, 0, 0);?