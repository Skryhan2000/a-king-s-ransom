package com.loneliness.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class OrderData implements Serializable {
    private static final long serialVersionUID=3L;
    private int id;
    @Positive(message = "Id клиента должно быть больше 0")
    @NotNull(message = "Должен быть задан сушествующий id клиента")
    private int customerId;
    @NotNull(message = "Должен быть задано имя заказа. ")
    private String orderName;
    @PastOrPresent(message = "Дата получения заказа должна быть в прошлом. ")
    @NotNull(message = "Должна быть задана дата получения заказа. ")
    private LocalDate dateOfReceiving;
    @Future(message = "Дата выполнения должна быть в будующем. ")
    @NotNull(message = "Долна быть задана дата выполнения. ")
    private LocalDate dateOfCompletion;
    @NotNull(message = "Должен быть задан статус. ")
    private String status;

    private transient StringProperty orderNameString;
    private transient StringProperty statusString;

    public OrderData(int id, int customerId, String orderName, LocalDate dateOfReceiving, LocalDate dateOfCompletion, String status, StringProperty orderNameString, StringProperty statusString) {
        this.id = id;
        this.customerId = customerId;
        this.orderName = orderName;
        this.dateOfReceiving = dateOfReceiving;
        this.dateOfCompletion = dateOfCompletion;
        this.status = status;
        this.orderNameString = orderNameString;
        this.statusString = statusString;
    }

    public OrderData(int id, int customerId, String orderName, LocalDate dateOfReceiving, LocalDate dateOfCompletion, String status) {
        this.id = id;
        this.customerId = customerId;
        this.orderName = orderName;
        this.dateOfReceiving = dateOfReceiving;
        this.dateOfCompletion = dateOfCompletion;
        this.status = status;
        orderNameString=new SimpleStringProperty(orderName);
        statusString=new SimpleStringProperty(status);
    }

    public OrderData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
        orderNameString=new SimpleStringProperty(orderName);
    }

    public LocalDate getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(LocalDate dateOfReceiving) {
        this.dateOfReceiving = dateOfReceiving;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        statusString=new SimpleStringProperty(status);
    }

    public String getOrderNameString() {
        return orderNameString.get();
    }

    public StringProperty orderNameStringProperty() {
        return orderNameString;
    }

    public void setOrderNameString(String orderNameString) {
        this.orderNameString.set(orderNameString);
    }

    public String getStatusString() {
        return statusString.get();
    }

    public StringProperty statusStringProperty() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString.set(statusString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderData orderData = (OrderData) o;
        return id == orderData.id &&
                customerId == orderData.customerId &&
                orderName.equals(orderData.orderName) &&
                dateOfReceiving.equals(orderData.dateOfReceiving) &&
                dateOfCompletion.equals(orderData.dateOfCompletion) &&
                status.equals(orderData.status) &&
                orderNameString.equals(orderData.orderNameString) &&
                statusString.equals(orderData.statusString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderName, dateOfReceiving, dateOfCompletion, status, orderNameString, statusString);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderName='" + orderName + '\'' +
                ", dateOfReceiving=" + dateOfReceiving +
                ", dateOfCompletion=" + dateOfCompletion +
                ", status='" + status + '\'' +
                ", orderNameString=" + orderNameString +
                ", statusString=" + statusString +
                '}';
    }
}
