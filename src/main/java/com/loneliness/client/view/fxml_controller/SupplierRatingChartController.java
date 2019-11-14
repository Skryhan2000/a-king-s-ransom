package com.loneliness.client.view.fxml_controller;

import com.loneliness.entity.ProviderData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SupplierRatingChartController {
    @FXML
    private CategoryAxis supplierNameAxis=new CategoryAxis();;
    @FXML
    private NumberAxis ratingAxis=new NumberAxis();;


    @FXML
    private BarChart<String, Number> barChart = new BarChart<>(supplierNameAxis, ratingAxis);
    private XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();


    public void setData(ObservableList<ProviderData> providersData){
        dataSeries.setName("Текущий отчётный период");
        for (ProviderData data:providersData){
            dataSeries.getData().add(new XYChart.Data<String, Number>(data.getName(),data.getRating()));
        }
        barChart.getData().add(dataSeries);
        barChart.setTitle("Рейтинг поставщиков");
        barChart.setTitle("Рейтинг поставщиков");

    }
}
