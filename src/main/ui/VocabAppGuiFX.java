package ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Profile;
import model.SingleEntry;
import persistence.Reader;
import persistence.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Represents a vocabulary trainer application
public class VocabAppGuiFX extends Application implements EventHandler<ActionEvent> {
    private Profile profile;
    private ArrayList<Profile> profiles;
    private SingleEntry selected;
    private Reader reader;
    private String name;
    private XYChart.Series series;
    private LineChart<Number,Number> lineChart;
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
    private Label searchFeedbackLabel;
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
    private Label testFeedbackLabel;
    private Stage window;
    private TextField loginInput;
    private TextField databaseDescriptionInput;
    private TextField databaseMeaningInput;
    private TextField databaseCommentInput;
    private TextField databaseExampleInput;
    private TextField searchInput;
    private TextField mainDescriptionInput;
    private TextField mainMeaningInput;
    private TextField mainCommentInput;
    private TextField mainExampleInput;
    private TextField testInput;
    private TableView<SingleEntry> table;
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

    //EFFECTS: launches the app
    public static void main(String[] args) {
        launch(args);
    }

    //EFFECTS: starts the app
    //MODIFIES: this
    //SOURCE: large parts based on https://www.youtube.com/watch?v=FLkOX4Eez6o&list=PLkY8n-MZcmgRwjYDebUGDcf1PCxT3JA5z
    // and following videos
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadProfiles();

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

        loginToMainButton.setOnAction(e -> profile = findOrCreateProfile());

        loginLayout.getChildren().addAll(loginLabel, loginInput, loginToMainButton);
        loginScene = new Scene(loginLayout, 850, 500);

        //ROOT
        rootLayout = new BorderPane();
        rootLayout.setPadding(new Insets(10, 10, 10, 10));

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
        databaseMenuItem.setOnAction(e -> rootLayout.setCenter(databaseLayout));
        searchMenuItem.setOnAction(e -> rootLayout.setCenter(searchLayout));
        testMenuItem.setOnAction(e -> rootLayout.setCenter(testLayout));
        quitMenuItem.setOnAction(e -> closeProgram());

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
        mainLabel = new Label("Welcome " + name + ". To get started, enter a word or phrase you'd like to learn,"
                + "\nthe synonym you're familiar with, a comment and an example sentence if you'd like.");
        mainLabel.setMaxWidth(500);
        mainDescriptionInput = new TextField();
        mainDescriptionInput.setPromptText("description");
        mainDescriptionInput.setMaxWidth(500);
        mainMeaningInput = new TextField();
        mainMeaningInput.setPromptText("meaning");
        mainMeaningInput.setMaxWidth(500);
        mainCommentInput = new TextField();
        mainCommentInput.setPromptText("comment");
        mainCommentInput.setMaxWidth(500);
        mainExampleInput = new TextField();
        mainExampleInput.setPromptText("example sentence");
        mainExampleInput.setMaxWidth(500);

        mainAddButton = new Button("Add");
        mainAddButton.setOnMouseClicked(e -> mainAddButtonClicked());

        mainToTestButton = new Button("Test myself");
        mainToTestButton.setOnAction(e -> rootLayout.setCenter(testLayout));
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(e -> closeProgram());
        mainLayout.getChildren().addAll(mainLabel, mainDescriptionInput, mainMeaningInput, mainCommentInput,
                mainExampleInput, mainAddButton, mainToTestButton, mainToQuitButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20);

        rootLayout.setCenter(mainLayout);

        rootScene = new Scene(rootLayout, 920, 600);

        //TEST
        testLabel = new Label("TEST\n To start testing, press 'Start!'. Hit Enter to get next question. Press"
                + "'Return to main' to return to main page.");
        Button testStartButton = new Button("Start!");
        testStartButton.setOnAction(e -> showTestQuestion());
        testQuestionLabel = new Label("");
        testFeedbackLabel = new Label("");
        testInput = new TextField();
        testInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                showTestFeedback();
                testInput.clear();
            }
        });

        testToMainButton = new Button("Return to main");
        testToMainButton.setOnAction(e -> rootLayout.setCenter(mainLayout));

        testLayout = new VBox(20);
        testLayout.getChildren().addAll(testLabel, testStartButton, testQuestionLabel, testInput,
                testFeedbackLabel, testToMainButton);

        //DATABASE
        //description column
        TableColumn<SingleEntry, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(200);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<SingleEntry, String>("description"));

        //meaning column
        TableColumn<SingleEntry, String> meaningColumn = new TableColumn<>("Meaning");
        meaningColumn.setMinWidth(200);
        meaningColumn.setCellValueFactory(new PropertyValueFactory<SingleEntry, String>("meaning"));

        //comment column
        TableColumn<SingleEntry, String> commentColumn = new TableColumn<>("Comment");
        commentColumn.setMinWidth(200);
        commentColumn.setCellValueFactory(new PropertyValueFactory<SingleEntry, String>("comment"));

        //sentence column
        TableColumn<SingleEntry, String> sentenceColumn = new TableColumn<>("Example Sentence");
        sentenceColumn.setMinWidth(200);
        sentenceColumn.setCellValueFactory(new PropertyValueFactory<SingleEntry, String>("example"));

        //successRate column
        TableColumn<SingleEntry, Double> successRateColumn = new TableColumn<>("Success Rate");
        successRateColumn.setMinWidth(100);
        successRateColumn.setCellValueFactory(new PropertyValueFactory<SingleEntry, Double>("successRate"));

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
        databaseExampleInput = new TextField();
        databaseExampleInput.setPromptText("example sentence");
        databaseExampleInput.setMinWidth(100);

        //add and delete button
        databaseAddButton = new Button("Add");
        databaseAddButton.setOnMouseClicked(e -> databaseAddButtonClicked());
        databaseDeleteButton = new Button("Delete");
        databaseDeleteButton.setOnMouseClicked(e -> databaseDeleteButtonClicked());

        //HBox with add and delete option
        databaseHBox = new HBox();
        databaseHBox.setPadding(new Insets(10, 10, 10, 10));
        databaseHBox.setSpacing(10);
        databaseHBox.getChildren().addAll(databaseDescriptionInput, databaseMeaningInput, databaseCommentInput,
                databaseExampleInput, databaseAddButton, databaseDeleteButton);

        table = new TableView<>();
        table.setItems(getDatabaseTable());
        table.getColumns().addAll(descriptionColumn, meaningColumn, commentColumn, sentenceColumn, successRateColumn);

        databaseLayout = new BorderPane();
        databaseLayout.setCenter(table);
        databaseLayout.setBottom(databaseHBox);

        //SEARCH
        searchLayout = new VBox();
        searchLabel = new Label("SEARCH \nTo search your database, enter any word or phrase.");
        searchInput = new TextField();
        searchInput.setPromptText("word or phrase");
        searchFeedbackLabel = new Label("");
        searchButton = new Button("SEARCH");
        searchButton.setOnMouseClicked(e -> search(searchInput.getText()));
        searchLayout.getChildren().addAll(searchLabel, searchButton, searchInput,
                searchFeedbackLabel);

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
        profileEntriesLabel = new Label("Entries");
        profileAchievementsLabel = new Label("Achievements");
        profileExportDataLabel = new Label("Export my data");
        profileDeleteProfileButton = new Button("DELETE MY PROFILE");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of sessions");
        yAxis.setLabel("Success rate in % per session");
        //creating the chart
        lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        profileLayout.getChildren().addAll(profileLabel, lineChart, profileEntriesLabel,
                profileAchievementsLabel, profileExportDataLabel, profileDeleteProfileButton);
        profileLayout.setAlignment(Pos.CENTER);
        profileMenuLabel.setOnMouseClicked(mouseEvent -> {
            rootLayout.setCenter(profileLayout);
            createSignChart();
        });

        //DISPLAY WINDOW
        window.setScene(loginScene);
        window.show();
    }

    //EFFECTS: updates the sign chart
    //MODIFIES: this
    //SOURCE: partly based on https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
    private void createSignChart() {
        lineChart.setTitle("My Success Rate");
        //defining a series
        series = new XYChart.Series();
        series.setName("Success Rate");
        //populating the series with data
        ArrayList<Double> successRates = profile.getSuccessRates();
        for (int i = 0, k = 0; i < successRates.size(); i++, k++) {
            series.getData().add(new XYChart.Data(profile.getSuccessRates().get(i), k));
        }
        lineChart.getData().add(series);
    }

    //EFFECTS: searches for entry and sets label accordingly
    //MODIFIES: this
    private void search(String search) {
        String searchFeedbackText;
        SingleEntry entry = profile.getDatabase().getEntryBasedOnValue(search);
        if (entry == null) {
            searchFeedbackText = "Oops, we could not find such entry.";
        } else {
            String introText = "The entry you're looking for has the following properties: ";
            String descriptionText = "Description: " + entry.getDescription();
            String meaningText = "Meaning: " + entry.getMeaning();
            String commentText = "Comment: " + entry.getComment();
            String exampleText = "Example: " + entry.getExample();
            String successRate;
            if (entry.getAttempts() == 1) {
                successRate = "You have no tests recorded for this entry.";
            } else {
                successRate = "Your success rate for this entry is "
                        + entry.getSuccessRate() + "%.";
            }
            searchFeedbackText = introText + "\n" + descriptionText + "\n" + meaningText + "\n"
                    + commentText + "\n" + exampleText + "\n" + successRate;
        }
        searchFeedbackLabel.setText(searchFeedbackText);
    }

    //EFFECTS: reads the profiles
    //MODIFIES: this
    private void loadProfiles() {
        try {
            reader = new Reader();
            profiles = new ArrayList<>(Arrays.asList(reader.getProfiles()));
        } catch (IOException e) {
            System.out.println("Something went wrong with loading the profiles.");
        }
    }

    //EFFECTS: sets the profile to the profile of user or creates new if not found
    //MODIFIES: this
    private Profile findOrCreateProfile() {
        name = loginInput.getText().trim(); //Note: trim excludes any space at end
        profile = new Profile();
        if (reader.findProfile(name) == null) {
            profile.setName(name);
            profiles.add(profile);
            if (SignUpAlert.displaySignUpAlert(name)) {
                loadExampleDatabase();
            }
        } else {
            profile = reader.findProfile(name);
        }
        window.setScene(rootScene);
        return profile;
    }

    //EFFECTS: loads example data base
    //MODIFIES: this
    private void loadExampleDatabase() {
        SingleEntry entry1 = new SingleEntry("toboggan", "sled",
                "verb is 'to toboggan'", "riding down a hill with a sled");
        SingleEntry entry2 = new SingleEntry("eh?", "'right?'",
                "does not have to be used as question", "it's cold today, eh?");
        SingleEntry entry3 = new SingleEntry("ubiquitous", "everywhere",
                "yü-ˈbi-kwə-təs", "");
        profile.getDatabase().addEntry(entry1);
        profile.getDatabase().addEntry(entry2);
        profile.getDatabase().addEntry(entry3);
    }

    //EFFECTS: sets question label to randomly chosen question
    //MODIFIES: this
    private void showTestQuestion() {
        double random = profile.getDatabase().getRandomFromSumOfFailureRates();
        selected = profile.getDatabase().getEntryBasedOnRandom(random);
        String questionPart = selected.getMeaning();
        String questionBody = "What's another word for ";
        String question = questionBody + questionPart + "?";
        testQuestionLabel.setText(question);
    }

    //EFFECTS: sets feedback label according to user input
    //MODIFIES: this
    private void showTestFeedback() {
        String feedback;
        if (selected.getDescription().equals(testInput.getText())) {
            feedback = "Correct!";
        } else {
            feedback = "Unfortunately that is wrong. The right answer to " + selected.getMeaning() + " is "
                    + selected.getDescription() + ".";
        }
        selected.adjustDistribution(testInput.getText());
        testFeedbackLabel.setText(feedback);
        showTestQuestion();
    }

    //EFFECTS: handles events
    @Override
    public void handle(ActionEvent event) {
    }


    //EFFECTS: adds user input to table and to database
    //MODIFIES: this
    public void databaseAddButtonClicked() {
        SingleEntry singleEntry = new SingleEntry();
        singleEntry.setDescription(databaseDescriptionInput.getText());
        singleEntry.setMeaning(databaseMeaningInput.getText());
        singleEntry.setComment(databaseCommentInput.getText());
        singleEntry.setExample(databaseExampleInput.getText());
        table.getItems().add(singleEntry);
        profile.getDatabase().addEntry(singleEntry);
        databaseDescriptionInput.clear();
        databaseMeaningInput.clear();
        databaseCommentInput.clear();
        databaseExampleInput.clear();
    }

    //EFFECTS: deletes selected entry from table and from database
    //MODIFIES: this
    public void databaseDeleteButtonClicked() {
        ObservableList<SingleEntry> selectedSingleEntries;
        ObservableList<SingleEntry> allSingleEntries;
        selectedSingleEntries = table.getSelectionModel().getSelectedItems();
        allSingleEntries = table.getItems();
        selectedSingleEntries.forEach(allSingleEntries::remove);
        for (SingleEntry entry : selectedSingleEntries) {
            profile.getDatabase().removeEntry(entry.getDescription());
        }
    }

    //EFFECTS: creates table with database
    //MODIFIES: this
    public ObservableList<SingleEntry> getDatabaseTable() {
        ObservableList<SingleEntry> databaseTable = FXCollections.observableArrayList();
        for (SingleEntry entry : profile.getDatabase().getEntries()) {
            databaseTable.add(entry);
        }
//        databaseTable.add(new SingleEntry("toboggan", "sled",
//                "random comment", "random example sentence"));
//        databaseTable.add(new SingleEntry("spinning wheels", "being exhausted",
//                "another random comment", "another random example sentence"));
        return databaseTable;
    }

    //EFFECTS: adds current session to records, closes program and writes everything into Json File
    //MODIFIES: this
    private void closeProgram() {
        profile.addSuccessRateOfSession();
        try {
            Writer.write(profiles);
        } catch (IOException e) {
            System.out.println("Unfortunately an error occurred.");
        }
        window.close();
    }

    //EFFECTS: adds entry to database table and database
    //MODIFIES: this
    private void mainAddButtonClicked() {
        SingleEntry singleEntry;
        singleEntry = new SingleEntry(mainDescriptionInput.getText(), mainMeaningInput.getText(),
                mainCommentInput.getText(), mainExampleInput.getText());
        profile.getDatabase().addEntry(singleEntry);
        table.getItems().add(singleEntry);
        mainDescriptionInput.clear();
        mainMeaningInput.clear();
        mainCommentInput.clear();
        mainExampleInput.clear();
    }
}




//Notes: the stage is the entire window, the scene is the content in the window
//TODO: why is it  GridPane.setConstraints(loginLabel, 0, 0); instead of  loginLayout.setConstraints(loginLabel, 0, 0);?
//TODO: make sure user has to enter something for description and meaning, but does not have to enter for comment and
// example sentence both in main and in database
//TODO: provide user with example input