package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.SingleEntry;

public class SearchLayout extends Layout {
    VBox searchLayout;
    TextField searchInput;
    Label feedbackLabel;


    public SearchLayout() {
        this.searchLayout = new VBox();
        Label searchLabel = new Label("SEARCH");
        this.searchInput = new TextField();
        this.searchInput.setPromptText("word or phrase");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> search(searchInput.getText()));
        this.feedbackLabel = new Label("");
        searchLayout.getChildren().addAll(searchLabel, searchInput, searchButton, feedbackLabel);
    }

    //EFFECTS: searches for entry and sets label accordingly
    //MODIFIES: this
    private void search(String search) {
        String searchFeedbackText;
        SingleEntry entry = layoutProfile.getDatabase().getEntryBasedOnValue(search);
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
        feedbackLabel.setText(searchFeedbackText);
    }

    @Override
    protected VBox getNode() {
        return this.searchLayout;
    }
}



