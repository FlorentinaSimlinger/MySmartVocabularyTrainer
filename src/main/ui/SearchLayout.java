package ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.SingleEntry;

public class SearchLayout extends Layout {
    TextField searchInput;
    Label label1;

    public SearchLayout(String labelString, String buttonString1, String buttonString2) {
        super(labelString, buttonString1, buttonString2);
        VBox searchLayout = new VBox();
        label1 = addLabel("");
        searchInput = new TextField();
        searchInput.setPromptText("word or phrase");
        button1.setOnAction(e -> search(searchInput.getText()));
        searchLayout.getChildren().addAll(label, searchInput, button1, label1);
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
        label1.setText(searchFeedbackText);
    }
}

//TODO: implement
    //onSearch(e -> search(searchInput.getText()));
//TODO: call SearchLayout with "SEARCH" as buttonString1

