package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.SingleEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//represents a database layout
public class DatabaseLayout extends Layout {
    private BorderPane databaseLayout;
    private HBox databaseHBox;
    private ArrayList<TextField> textFields = new ArrayList<>();

    //EFFECTS: constructs database layout
    public DatabaseLayout(String labelString) {
        super(labelString, "", "");
        databaseLayout = new BorderPane();
        databaseLayout.setTop(label);
        addHBox();
        addTable();
    }

    //EFFECTS: adds HBox to layout
    //MODIFIES: this
    public void addHBox() {
        databaseHBox = new HBox();
        databaseHBox.setPadding(new Insets(10, 10, 10, 10));
        databaseHBox.setSpacing(10);
        addUserInputFields();
        getAddAndDeleteButtons();
        databaseLayout.setBottom(databaseHBox);
    }

    //EFFECTS: adds table to layout
    //MODIFIES: this
    public void addTable() {
        table = new TableView<>();
        getTableColumns();
        table.setItems(getTableItems());
        databaseLayout.setCenter(table);
    }

    //EFFECTS: creates tables columns and adds them to the table
    //MODIFIES: this
    public void getTableColumns() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Description", "description");
        map.put("Meaning", "meaning");
        map.put("Comment", "comment");
        map.put("Example Sentence", "example");
        map.put("Success Rate", "successRate");
        for (String key : map.keySet()) {
            String value = map.get(key);
            TableColumn<SingleEntry, String> column = new TableColumn<>(key);
            column.setMinWidth(200);
            column.setCellValueFactory(new PropertyValueFactory<SingleEntry, String>(value));
            table.getColumns().add(column);
        }
    }

    //EFFECTS: creates table with database
    //MODIFIES: this
    public ObservableList<SingleEntry> getTableItems() {
        ObservableList<SingleEntry> databaseTable = FXCollections.observableArrayList();
        if (profile != null) {
            for (SingleEntry entry : profile.getDatabase().getEntries()) {
                databaseTable.add(entry);
            }
        }
        return databaseTable;
    }

    //EFFECTS: adds Add and Delete Buttons to Database Layout, changes Database Layout and actual database
    //MODIFIES: this
    private void getAddAndDeleteButtons() {
        //add and delete button
        button1.setOnMouseClicked(e -> addButtonClicked());
        button2.setOnMouseClicked(e -> databaseDeleteButtonClicked());
        databaseHBox.getChildren().addAll(button1, button2);
    }

    //EFFECTS: creates fields for user input
    public void addUserInputFields() {
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add("description");
        fieldNames.add("meaning");
        fieldNames.add("comment");
        fieldNames.add("example sentence");
        for (String fieldName : fieldNames) {
            TextField textField = new TextField();
            textField.setPromptText(fieldName);
            textField.setMinWidth(100);
            databaseHBox.getChildren().add(textField);
            textFields.add(textField);
        }
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
}

//TODO: need to pass in a profile