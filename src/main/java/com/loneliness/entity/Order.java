package com.loneliness.entity;

import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Order implements Serializable {
    private static final long serialVersionUID=3L;
    private int id;
    private int customerId;
    private String orderName;
    private LocalDate dateOfReceiving;
    private LocalDate dateOfCompletion;
    private String status;
    private transient StringProperty orderNameString;
    private transient StringProperty statusString;

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
        Order order = (Order) o;
        return id == order.id &&
                customerId == order.customerId &&
                orderName.equals(order.orderName) &&
                dateOfReceiving.equals(order.dateOfReceiving) &&
                dateOfCompletion.equals(order.dateOfCompletion) &&
                status.equals(order.status) &&
                orderNameString.equals(order.orderNameString) &&
                statusString.equals(order.statusString);
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
