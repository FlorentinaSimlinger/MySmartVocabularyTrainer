package ui;

import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ProfileLayout extends Layout {
    LineChart<Number, Number> lineChart;

    public ProfileLayout(String labelString, String buttonString1) {
        super(labelString, buttonString1, "");
        VBox profileLayout = new VBox();
        Label profileEntriesLabel = addLabel("Entries");
        Label profileAchievementsLabel = addLabel("Achievements");
        Label profileExportDataLabel = addLabel("Export my data");
        addLineChart();

        profileLayout.getChildren().addAll(label, lineChart, profileEntriesLabel,
                profileAchievementsLabel, profileExportDataLabel, button1);
        profileLayout.setAlignment(Pos.CENTER);

    }

    //EFFECTS: adds a line chart
    //MODIFIES: this
    //SOURCE: partly based on https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
    private void addLineChart() {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of sessions");
        yAxis.setLabel("Success rate in % per session");
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("My Success Rate");
        XYChart.Series series = new XYChart.Series();
        series.setName("Success Rate");
        ArrayList<Double> successRates = profile.getSuccessRates();
        for (int i = 0, k = 0; i < successRates.size(); i++, k++) {
            series.getData().add(new XYChart.Data(k, profile.getSuccessRates().get(i)));
        }
        lineChart.getData().add(series);
    }
}

