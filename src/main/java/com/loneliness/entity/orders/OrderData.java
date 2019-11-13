package com.loneliness.entity.orders;

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
    private Status status;
    @NotNull(message = "Должен быть задан статус. ")
    private Payment payment;
    @Positive(message = "Id менеджера должно быть больше 0")
    @NotNull(message = "Должен быть задан сушествующий id менеджера")
    private int managerID;
    private transient StringProperty dateOfCompletionString;
    private transient StringProperty statusString;

    public enum Payment{
        ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛИЧНЫМИ_ДОСТАВШИКУ, ОПЛАТА_ПО_ПОЛУЧЕНИЮ_НАЛОЖЕННЫЙ_ПЛАТЕЖ,
        ОПЛАТА_ПО_ПОЛУЧЕНИЮ_БЕЗНАЛИЧНЫЙ_РАСЧЁТ,

        ПРЕДОПЛАТА_НАЛИЧНЫМИ,ПРЕДОПЛАТА_НАЛОЖЕННЫЙ_ПЛАТЕЖ,
        ПРЕДОПЛАТА_БЕЗНАЛИЧНЫЙ_РАСЧЁТ
    }

    public enum Status{
        ОФОРМЛЁН, СБОР_КОМПЛЕКТУЮЩИХ, ВЫПОЛНЕН, ДОСТАВЛЕН, ОТМЕНЕН, ОЖИДАНИЕ_ОПЛАТЫ, ВЫДАН, ВОЗВРАТ,
    }

    public OrderData(int id, @Positive(message = "Id клиента должно быть больше 0") @NotNull(message = "Должен быть задан сушествующий id клиента") int customerId, @NotNull(message = "Должен быть задано имя заказа. ") String orderName, @PastOrPresent(message = "Дата получения заказа должна быть в прошлом. ") @NotNull(message = "Должна быть задана дата получения заказа. ") LocalDate dateOfReceiving, @Future(message = "Дата выполнения должна быть в будующем. ") @NotNull(message = "Долна быть задана дата выполнения. ") LocalDate dateOfCompletion, @NotNull(message = "Должен быть задан статус. ") Status status, @NotNull(message = "Должен быть задан статус. ") Payment payment, StringProperty dateOfCompletionString, StringProperty statusString) {
        this.id = id;
        this.customerId = customerId;
        this.orderName = orderName;
        this.dateOfReceiving = dateOfReceiving;
        this.dateOfCompletion = dateOfCompletion;
        this.status = status;
        this.payment = payment;
        this.dateOfCompletionString = dateOfCompletionString;
        this.statusString = statusString;
    }

    public OrderData(int id, @Positive(message = "Id клиента должно быть больше 0") @NotNull(message = "Должен быть задан сушествующий id клиента") int customerId, @NotNull(message = "Должен быть задано имя заказа. ") String orderName, @PastOrPresent(message = "Дата получения заказа должна быть в прошлом. ") @NotNull(message = "Должна быть задана дата получения заказа. ") LocalDate dateOfReceiving, @Future(message = "Дата выполнения должна быть в будующем. ") @NotNull(message = "Долна быть задана дата выполнения. ") LocalDate dateOfCompletion, @NotNull(message = "Должен быть задан статус. ") Status status, @NotNull(message = "Должен быть задан статус. ") Payment payment) {
        this.id = id;
        this.customerId = customerId;
        this.orderName = orderName;
        this.dateOfReceiving = dateOfReceiving;
        this.dateOfCompletion = dateOfCompletion;
        this.status = status;
        this.payment = payment;
        dateOfCompletionString =new SimpleStringProperty(dateOfCompletion.toString());
        statusString=new SimpleStringProperty(status.toString());
    }

    public OrderData() { }

    public void setPayment(String payment){
        this.payment=Payment.valueOf(payment);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Payment getPayment() {
        return payment;
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
        dateOfCompletionString =new SimpleStringProperty(dateOfCompletion.toString());
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
        statusString=new SimpleStringProperty(status);
    }

    public String getDateOfCompletionString() {
        return dateOfCompletionString.get();
    }

    public StringProperty dateOfCompletionStringProperty() {
        if(dateOfCompletionString==null){
            dateOfCompletionString =new SimpleStringProperty(dateOfCompletion.toString());
        }
        return dateOfCompletionString;
    }

    public void setDateOfCompletionString(String dateOfCompletionString) {
        this.dateOfCompletionString.set(dateOfCompletionString);
    }

    public String getStatusString() {
        return statusString.get();
    }

    public StringProperty statusStringProperty() {
        if(statusString==null){
            statusString=new SimpleStringProperty(status.toString());
        }
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString.set(statusString);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
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
                status == orderData.status &&
                payment == orderData.payment &&
                Objects.equals(dateOfCompletionString, orderData.dateOfCompletionString) &&
                Objects.equals(statusString, orderData.statusString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderName, dateOfReceiving, dateOfCompletion, status, payment, dateOfCompletionString, statusString);
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderName='" + orderName + '\'' +
                ", dateOfReceiving=" + dateOfReceiving +
                ", dateOfCompletion=" + dateOfCompletion +
                ", status=" + status +
                ", payment=" + payment +
                ", dateOfCompletionString=" + dateOfCompletionString +
                ", statusString=" + statusString +
                '}';
    }
}
