package com.loneliness.entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


public class ProductInStock implements Serializable {
    private static final long serialVersionUID=5L;
    private int id;
    @NotNull(message = "Должно быть задано имя. ")
    private String name;
    @NotNull(message = "Задайте количество товара. ")
    @Positive(message = "Число товаров должно быть положительно. ")
    private int quantity;
    @NotNull(message = "Задайте цену за единицу товара. ")
    @Positive(message = "Цена товаров должно быть положительно. ")
    private double unitPrice;
    @NotNull(message = "Задайте id поставщика товара. ")
    @Positive(message = "Id поставщика должн быть положителен. ")
    private int provider_ID;
    @NotNull(message = "Задайте дату поступления. ")
    @PastOrPresent(message = "Дата поступления не может быть в будующем. ")
    private LocalDate receipt_date;
    private Date DATA=new Date();
    private transient SimpleStringProperty nameString;
    private transient SimpleIntegerProperty quantityInteger;

    public ProductInStock(int id, @NotNull(message = "Должно быть задано имя. ") String name,
                          @NotNull(message = "Задайте количество товара. ")
                          @Positive(message = "Число товаров должно быть положительно. ") int quantity,
                          @NotNull(message = "Задайте цену за единицу товара. ")
                          @Positive(message = "Цена товаров должно быть положительно. ") double unitPrice,
                          @NotNull(message = "Задайте id поставщика товара. ")
                          @Positive(message = "Id поставщика должн быть положителен. ") int provider_ID,
                          @NotNull(message = "Задайте дату поступления. ")
                          @PastOrPresent(message = "Дата поступления не может быть в будующем. ") LocalDate receipt_date,
                          SimpleStringProperty nameString, SimpleIntegerProperty quantityInteger) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider_ID = provider_ID;
        this.receipt_date = receipt_date;
        this.nameString = nameString;
        this.quantityInteger = quantityInteger;
    }

    public ProductInStock(int id, @NotNull(message = "Должно быть задано имя. ") String name,
                          @NotNull(message = "Задайте количество товара. ")
                          @Positive(message = "Число товаров должно быть положительно. ") int quantity,
                          @NotNull(message = "Задайте цену за единицу товара. ")
                          @Positive(message = "Цена товаров должно быть положительно. ") double unitPrice,
                          @NotNull(message = "Задайте id поставщика товара. ")
                          @Positive(message = "Id поставщика должн быть положителен. ") int provider_ID,
                          @NotNull(message = "Задайте дату поступления. ")
                          @PastOrPresent(message = "Дата поступления не может быть в будующем. ") LocalDate receipt_date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.provider_ID = provider_ID;
        this.receipt_date = receipt_date;
        nameString=new SimpleStringProperty(name);
        quantityInteger =new SimpleIntegerProperty(quantity);
    }

    public ProductInStock() {
    }

    public Date getDATA() {
        return java.sql.Date.valueOf(receipt_date);
    }

    public void setDATA(Date DATA) {
        this.DATA = DATA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        nameString=new SimpleStringProperty(name);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        quantityInteger =new SimpleIntegerProperty(quantity);
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getProvider_ID() {
        return provider_ID;
    }

    public void setProvider_ID(int provider_ID) {
        this.provider_ID = provider_ID;
    }

    public LocalDate getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(LocalDate receipt_date) {
        this.receipt_date = receipt_date;
        DATA.setTime(receipt_date.toEpochDay());
    }

    public String getNameString() {
        return nameString.get();
    }

    public SimpleStringProperty nameStringProperty() {
        if(nameString==null) {
            nameString = new SimpleStringProperty(name);
        }
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString.set(nameString);
    }

    public double getQuantityInteger() {
        return quantityInteger.get();
    }

    public SimpleIntegerProperty quantityIntegerProperty() {
        if(quantityInteger==null) {
            quantityInteger = new SimpleIntegerProperty(quantity);
        }
        return quantityInteger;
    }

    public void setQuantityInteger(int quantityInteger) {
        this.quantityInteger.set(quantityInteger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInStock that = (ProductInStock) o;
        return id == that.id &&
                quantity == that.quantity &&
                Double.compare(that.unitPrice, unitPrice) == 0 &&
                provider_ID == that.provider_ID &&
                name.equals(that.name) &&
                receipt_date.equals(that.receipt_date) &&
                Objects.equals(nameString, that.nameString) &&
                Objects.equals(quantityInteger, that.quantityInteger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, unitPrice, provider_ID, receipt_date, nameString, quantityInteger);
    }

    @Override
    public String toString() {
        return "ProductInStock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", provider_ID=" + provider_ID +
                ", receipt_date=" + receipt_date +
                ", nameString=" + nameString +
                ", quantityInteger=" + quantityInteger +
                '}';
    }
}
