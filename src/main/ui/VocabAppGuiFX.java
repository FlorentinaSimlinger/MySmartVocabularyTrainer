package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    private Button profileDeleteProfileButton;
    private Button mainAddButton;
    private Scene loginScene;
    private Scene rootScene;
    private Label loginLabel;
    private Label mainLabel;
    private Label aboutMenuLabel;
    private Label profileMenuLabel;
    private Label searchFoundLabel;
    private Label searchLabel;
    private Label searchNotFoundLabel;
    private Label aboutLabel;
    private Label profileLabel;
    private Label profileSuccessRateLabel;
    private Label profileEntriesLabel;
    private Label profileAchievementsLabel;
    private Label profileExportDataLabel;
    private Label mainMenuLabel;
    private Label testLabel;
    private Label testQuestionLabel;
    private Label testCorrectLabel;
    private Label testIncorrectLabel;
    private Stage window;
    private TextField loginInput;
    private TextField databaseDescriptionInput;
    private TextField databaseMeaningInput;
    private TextField databaseCommentInput;
    private TextField databaseExampleSentenceInput;
    private TextField searchInput;
    private TextField mainDescriptionInput;
    private TextField mainMeaningInput;
    private TextField mainCommentInput;
    private TextField mainExampleSentenceInput;
    private TableView<SingleEntryExport> table;
    private BorderPane rootLayout;
    private BorderPane databaseLayout;
    private HBox databaseHBox;
    private HBox menuBarHBox;
    private VBox mainLayout;
    private VBox testLayout;
    private VBox aboutLayout;
    private VBox profileLayout;
    private VBox searchLayout;
    private Menu moreMenu;
    private Menu aboutMenu;
    private Menu profileMenu;
    private Menu mainMenu;
    private MenuBar menuBar;
    private MenuItem databaseMenuItem;
    private MenuItem searchMenuItem;
    private MenuItem testMenuItem;
    private MenuItem quitMenuItem;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //SET WINDOW
        window = primaryStage;
        primaryStage.setTitle("MySmartVocabularyTrainer");
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
        quitMenuItem = new MenuItem("QUIT");
        moreMenu.getItems().add(databaseMenuItem);
        moreMenu.getItems().add(searchMenuItem);
        moreMenu.getItems().add(testMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(quitMenuItem);
        databaseMenuItem.setOnAction(this);
        searchMenuItem.setOnAction(this);
        testMenuItem.setOnAction(this);
        quitMenuItem.setOnAction(this);

        //Menus without MenuItems don't fire, therefore workaround,
        //SOURCE: https://stackoverflow.com/questions/48017645/event-handler-in-javafx-for-menu
        mainMenuLabel = new Label("MAIN");
        aboutMenuLabel = new Label("ABOUT");
        profileMenuLabel = new Label("PROFILE");
        mainMenu = new Menu("", mainMenuLabel);
        aboutMenu = new Menu("", aboutMenuLabel);
        profileMenu = new Menu("", profileMenuLabel);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(mainMenu, aboutMenu, profileMenu, moreMenu);

        menuBarHBox = new HBox();
        menuBarHBox.getChildren().add(menuBar);
        menuBarHBox.setAlignment(Pos.CENTER_RIGHT);

        rootLayout.setTop(menuBarHBox);

        //MAIN
        mainLayout = new VBox();
        mainLabel = new Label("To get started, enter a word or phrase you'd like to learn, the synonym you're"
                + " familiar with, a comment and an example sentence if you'd like.");
        mainDescriptionInput = new TextField();
        mainDescriptionInput.setPromptText("description");
        mainMeaningInput = new TextField();
        mainMeaningInput.setPromptText("meaning");
        mainCommentInput = new TextField();
        mainCommentInput.setPromptText("comment");
        mainExampleSentenceInput = new TextField();
        mainExampleSentenceInput.setPromptText("example sentence");
        mainAddButton = new Button("Add");
        mainToTestButton = new Button("Test myself");
        mainToTestButton.setOnAction(this);
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(this);
        mainLayout.getChildren().addAll(mainLabel, mainDescriptionInput, mainMeaningInput, mainCommentInput,
                mainExampleSentenceInput, mainAddButton, mainToTestButton, mainToQuitButton);
        mainLayout.setAlignment(Pos.CENTER);

        rootLayout.setCenter(mainLayout);

        rootScene = new Scene(rootLayout, 900, 900);

        //TEST
        testLabel = new Label("TEST");
        testQuestionLabel = new Label("What's the synonym for ");
        testCorrectLabel = new Label("Correct!");
        testIncorrectLabel = new Label("Incorrect, the correct word or sentence is ");
        testToMainButton = new Button("Return to main");
        testToMainButton.setOnAction(this);

        testLayout = new VBox(20);
        testLayout.getChildren().addAll(testLabel, testQuestionLabel, testCorrectLabel,
                testIncorrectLabel, testToMainButton);

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
        databaseDescriptionInput = new TextField();
        databaseDescriptionInput.setPromptText("description");
        databaseDescriptionInput.setMinWidth(100);

        //meaning input
        databaseMeaningInput = new TextField();
        databaseMeaningInput.setPromptText("meaning");
        databaseMeaningInput.setMinWidth(100);

        //comment input
        databaseCommentInput = new TextField();
        databaseCommentInput.setPromptText("comment");
        databaseCommentInput.setMinWidth(100);

        //example sentence input
        databaseExampleSentenceInput = new TextField();
        databaseExampleSentenceInput.setPromptText("example sentence");
        databaseExampleSentenceInput.setMinWidth(100);

        //add and delete button
        databaseAddButton = new Button("Add");
        databaseAddButton.setOnAction(this);
        databaseDeleteButton = new Button("Delete");
        databaseDeleteButton.setOnAction(this);

        //HBox with add and delete option
        databaseHBox = new HBox();
        databaseHBox.setPadding(new Insets(10, 10, 10, 10));
        databaseHBox.setSpacing(10);
        databaseHBox.getChildren().addAll(databaseDescriptionInput, databaseMeaningInput, databaseCommentInput,
                databaseExampleSentenceInput, databaseAddButton, databaseDeleteButton);

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
        searchNotFoundLabel = new Label("Oops, we could not find such entry.");
        searchButton = new Button("SEARCH");
        searchInput = new TextField();
        searchInput.setPromptText("word or phrase");
        searchLayout.getChildren().addAll(searchLabel, searchButton, searchInput,
                searchFoundLabel, searchNotFoundLabel);

        searchButton.setOnAction(this);

        //MAIN
        mainMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(mainLayout));

        //ABOUT
        aboutLayout = new VBox();
        aboutLabel = new Label("This is my 210 project");

        aboutLayout.getChildren().add(aboutLabel);
        aboutLayout.setAlignment(Pos.CENTER);

        aboutMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(aboutLayout));

        //PROFILE
        profileLayout = new VBox();
        profileLabel = new Label("MY PROFILE");
        profileSuccessRateLabel = new Label("Successrate");
        profileEntriesLabel = new Label("Entries");
        profileAchievementsLabel = new Label("Achievements");
        profileExportDataLabel = new Label("Export my data");
        profileDeleteProfileButton = new Button("DELETE MY PROFILE");

        profileLayout.getChildren().addAll(profileLabel, profileSuccessRateLabel, profileEntriesLabel,
                profileAchievementsLabel, profileExportDataLabel, profileDeleteProfileButton);
        profileLayout.setAlignment(Pos.CENTER);
        profileMenuLabel.setOnMouseClicked(mouseEvent -> rootLayout.setCenter(profileLayout));


        //DISPLAY WINDOW
        window.setScene(loginScene);
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
        } else if (event.getSource() == quitMenuItem) {
            closeProgram();
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
        singleEntryExport.setDescription(databaseDescriptionInput.getText());
        singleEntryExport.setMeaning(databaseMeaningInput.getText());
        singleEntryExport.setComment(databaseCommentInput.getText());
        singleEntryExport.setExampleSentence(databaseExampleSentenceInput.getText());
        singleEntryExport.setSuccessRate(0);
        table.getItems().add(singleEntryExport);
        databaseDescriptionInput.clear();
        databaseMeaningInput.clear();
        databaseCommentInput.clear();
        databaseExampleSentenceInput.clear();
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
//TODO: make sure user has to enter something for description and meaning, but does not have to enter for comment and
// example sentence both in main and in database
//TODO: provide user with example input