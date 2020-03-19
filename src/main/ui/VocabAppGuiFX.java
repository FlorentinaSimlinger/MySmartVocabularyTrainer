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

    Button loginToMainButton;
    Button mainToTestButton;
    Button mainToQuitButton;
    Button testToMainButton;
    Button databaseAddButton;
    Button databaseDeleteButton;
    Scene loginScene;
    Scene mainScene;
    Scene profileScene;
    Scene databaseScene;
    Scene searchScene;
    Scene testScene;
    Label loginLabel;
    Label mainLabel;
    Stage window;
    TextField loginInput;
    TextField descriptionInput;
    TextField meaningInput;
    TextField commentInput;
    TextField exampleSentenceInput;
    ComboBox<String> moreComboBox;
    TableView<SingleEntryExport> table;
    BorderPane mainLayout;
    BorderPane databaseLayout;
    HBox databaseHBox;
    VBox mainVBox;
    Menu moreMenu;
    MenuBar menuBar;

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

        //MAIN
        mainLayout = new BorderPane();

        //More Menu
        moreMenu = new Menu("MORE");

        //Menu items
        MenuItem databaseMenuItem = new MenuItem("DATABASE");
        MenuItem searchMenuItem = new MenuItem("SEARCH");
        MenuItem testMenuItem = new MenuItem("TEST");
        moreMenu.getItems().add(databaseMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(searchMenuItem);
        moreMenu.getItems().add(new SeparatorMenuItem());
        moreMenu.getItems().add(testMenuItem);
        databaseMenuItem.setOnAction(e -> {
            System.out.println("Implement database scene");
            window.setScene(databaseScene);
        });
        searchMenuItem.setOnAction(e -> {
            System.out.println("Implement search scene");
            window.setScene(searchScene);
        });
        testMenuItem.setOnAction(e -> {
            System.out.println("Implement test scene");
            window.setScene(testScene);
        });


        //Menu bar
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(moreMenu);

        //Main VBox
        mainVBox = new VBox();
        mainLabel = new Label("Welcome to the main page.");
        mainToTestButton = new Button("Go to test scene");
        mainToTestButton.setOnAction(this);
        mainToQuitButton = new Button("Quit");
        mainToQuitButton.setOnAction(this);
        mainVBox.getChildren().addAll(mainLabel, mainToTestButton, mainToQuitButton);

        mainLayout.setTop(menuBar);
        mainLayout.setCenter(mainVBox);

        mainScene = new Scene(mainLayout, 500, 250);

        //TEST
        testToMainButton = new Button("Go back to main scene");
        testToMainButton.setOnAction(this);

        VBox testLayout = new VBox(20);
        testLayout.getChildren().add(testToMainButton);
        testScene = new Scene(testLayout, 600, 600);

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
        //databaseLayout.setTop(menuBar);
        databaseLayout.setCenter(table);
        databaseLayout.setBottom(databaseHBox);

        databaseScene = new Scene(databaseLayout);

        //DISPLAY WINDOW
        window.setScene(mainScene);
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
            window.setScene(mainScene);
            System.out.println("implement result: " + result);
        } else if (event.getSource() == mainToTestButton) {
            window.setScene(testScene);
        } else if (event.getSource() == mainToQuitButton) {
            closeProgram();
        } else if (event.getSource() == testToMainButton) {
            window.setScene(mainScene);
        } else if (event.getSource() == databaseAddButton) {
            System.out.println("implement add to database");
            addButtonClicked();
        } else if (event.getSource() == databaseDeleteButton) {
            System.out.println("implement delete from database");
            deleteButtonClicked();
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