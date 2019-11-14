package com.loneliness.client.view.fxml_controller;

import com.loneliness.entity.ProductInStock;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ProductChartController {
    @FXML private PieChart pieChart = new PieChart();
    public void setData(ObservableList<ProductInStock> productsInStockData ){
        PieChart.Data slice;
        for (ProductInStock product :
                productsInStockData) {
            slice=new PieChart.Data(product.getName(),product.getQuantity()*product.getUnitPrice());
            pieChart.getData().add(slice);
        }
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setStartAngle(30);
        pieChart.getData().forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ", data.pieValueProperty(), " $"
                        )
                )
        );
    }
}
