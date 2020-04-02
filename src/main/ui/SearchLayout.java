package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//represents a search layout where entries can be searched
public class SearchLayout extends Layout {
    VBox searchLayout;
    TextField searchInput;
    Label feedbackLabel;
    public static final String EVENT_SEARCHENTRY = "search entry";

    //EFFECTS: constructs a search layout
    public SearchLayout() {
        this.searchLayout = new VBox();
        Label searchLabel = new Label("SEARCH");
        this.searchInput = new TextField();
        this.searchInput.setPromptText("word or phrase");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> dispatchEvent(e, EVENT_SEARCHENTRY));
        this.feedbackLabel = new Label("");
        this.searchLayout.getChildren().addAll(searchLabel, this.searchInput, searchButton, this.feedbackLabel);
    }

    //EFFECTS: searches for entry and sets label accordingly
    //MODIFIES: this
    public void setSearchFeedback(boolean found, boolean entryAttempted, String description, String meaning,
                                  String comment, String example, int successRate) {
        String searchFeedbackText;
        if (!found) {
            searchFeedbackText = "Oops, we could not find such entry.";
        } else {
            String introText = "The entry you're looking for has the following properties: ";
            String descriptionText = "Description: " + description;
            String meaningText = "Meaning: " + meaning;
            String commentText = "Comment: " + comment;
            String exampleText = "Example: " + example;
            String successRateText;
            if (!entryAttempted) {
                successRateText = "You have no tests recorded for this entry.";
            } else {
                successRateText = "Your success rate for this entry is "
                        + successRate + "%.";
            }
            searchFeedbackText = introText + "\n" + descriptionText + "\n" + meaningText + "\n"
                    + commentText + "\n" + exampleText + "\n" + successRateText;
        }
        this.feedbackLabel.setText(searchFeedbackText);
    }

    @Override
    protected VBox getNode() {
        return this.searchLayout;
    }

    public String getSearchInput() {
        return this.searchInput.getText();
    }
}



