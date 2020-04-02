package ui;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

//represents a layout to display profile data
public class ProfileLayout extends Layout {
    private LineChart<Number, Number> lineChart;
    private VBox profileLayout;
    private XYChart.Series series;
    public static final String EVENT_SUCCESSRATES = "success rates";

    //EFFECTS: constructs a profile layout
    public ProfileLayout() {
        this.profileLayout = new VBox();
        this.profileLayout.setAlignment(Pos.CENTER);

        Label profileLabel = new Label("PROFILE");
        Label profileEntriesLabel = new Label("Entries");
        Label profileAchievementsLabel = new Label("Achievements");
        Label profileExportDataLabel = new Label("Export my data");
        Button profileDeleteButton = new Button("Delete profile");
        addLineChart();

        this.profileLayout.getChildren().addAll(profileLabel, this.lineChart, profileEntriesLabel,
                profileAchievementsLabel, profileExportDataLabel, profileDeleteButton);
    }

    //EFFECTS: adds a line chart
    //MODIFIES: this
    //SOURCE: partly based on https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
    public void addLineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of sessions");
        yAxis.setLabel("Success rate in % per session");
        this.lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        this.lineChart.setTitle("My Success Rate");
        this.series = new XYChart.Series();
        series.setName("Success Rate");
        dispatchEvent(new Event(MouseEvent.MOUSE_PRESSED), EVENT_SUCCESSRATES);
    }

    public void updateLineChart(int numberOfSessions, Double successOfSession) {
        this.series.getData().add(new XYChart.Data(numberOfSessions, successOfSession));
        this.lineChart.getData().add(this.series);
    }

    @Override
    protected VBox getNode() {
        return this.profileLayout;
    }
}

