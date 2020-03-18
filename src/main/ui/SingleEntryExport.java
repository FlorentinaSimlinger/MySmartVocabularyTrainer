package ui;

public class SingleEntryExport {

    private String description;
    private String meaning;
    private String comment;
    private String exampleSentence;
    private double successRate;

    public SingleEntryExport() {
        this.description = "";
        this.meaning = "";
        this.comment = "";
        this.exampleSentence = "";
        this.successRate = 0;
    }

    //Overload constructor to either set values right away or set later
    public SingleEntryExport(String description, String meaning, String comment,
                             String exampleSentence, double successRate) {
        this.description = description;
        this.meaning = meaning;
        this.comment = comment;
        this.exampleSentence = exampleSentence;
        this.successRate = successRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getExampleSentence() {
        return exampleSentence;
    }

    public void setExampleSentence(String exampleSentence) {
        this.exampleSentence = exampleSentence;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }
}
