package com.loneliness.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class OrderCustomerData extends OrderData{
    private static final long serialVersionUID=6L;
    @NotNull(message = "Должен быть задан email меннеджера")
    @Email(message = "Должен быть задан валидный email. ")
    private String managerEmail;


    public OrderCustomerData(int id, @Positive(message = "Id клиента должно быть больше 0") @NotNull(message = "Должен быть задан сушествующий id клиента") int customerId, @NotNull(message = "Должен быть задано имя заказа. ") String orderName, @PastOrPresent(message = "Дата получения заказа должна быть в прошлом. ") @NotNull(message = "Должна быть задана дата получения заказа. ") LocalDate dateOfReceiving, @Future(message = "Дата выполнения должна быть в будующем. ") @NotNull(message = "Долна быть задана дата выполнения. ") LocalDate dateOfCompletion, @NotNull(message = "Должен быть задан статус. ") Status status, @NotNull(message = "Должен быть задан статус. ") Payment payment, StringProperty dateOfCompletionString, StringProperty statusString, @NotNull(message = "Должен быть задан email меннеджера") @Email(message = "Должен быть задан валидный email. ") String managerEmail) {
        super(id, customerId, orderName, dateOfReceiving, dateOfCompletion, status, payment, dateOfCompletionString, statusString);
        this.managerEmail = managerEmail;
    }

    public OrderCustomerData(int id, @Positive(message = "Id клиента должно быть больше 0") @NotNull(message = "Должен быть задан сушествующий id клиента") int customerId, @NotNull(message = "Должен быть задано имя заказа. ") String orderName, @PastOrPresent(message = "Дата получения заказа должна быть в прошлом. ") @NotNull(message = "Должна быть задана дата получения заказа. ") LocalDate dateOfReceiving, @Future(message = "Дата выполнения должна быть в будующем. ") @NotNull(message = "Долна быть задана дата выполнения. ") LocalDate dateOfCompletion, @NotNull(message = "Должен быть задан статус. ") Status status, @NotNull(message = "Должен быть задан статус. ") Payment payment, @NotNull(message = "Должен быть задан email меннеджера") @Email(message = "Должен быть задан валидный email. ") String managerEmail) {
        super(id, customerId, orderName, dateOfReceiving, dateOfCompletion, status, payment);
        this.managerEmail = managerEmail;
    }

    public OrderCustomerData(@NotNull(message = "Должен быть задан email меннеджера") @Email(message = "Должен быть задан валидный email. ") String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public OrderCustomerData() {
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
}
