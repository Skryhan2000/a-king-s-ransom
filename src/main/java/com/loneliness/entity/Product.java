package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class Product {
    private static final long serialVersionUID = 7L;
    private int id;
    @NotNull(message = "Должно быть задано имя")
    private String name;
    @Positive(message = " Должно быть задано положительное кол-во")
    private int quantity;
    @Positive(message = "Должно быть задано положительная цена")
    private double unit_price;
    private int orderId;
    private transient IntegerProperty idInteger;
    private transient StringProperty nameString;

    public Product(int id,
                   @NotNull(message = "Должно быть задано имя") String name,
                   @Positive(message = " Должно быть задано положительное кол-во") int quantity,
                   @Positive(message = "Должно быть задано положительная цена") double unit_price,
                   IntegerProperty integerId, StringProperty nameString) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.idInteger = integerId;
        this.nameString = nameString;
    }

    public Product(@NotNull(message = "Должно быть задано имя") String name,
                   @Positive(message = " Должно быть задано положительное кол-во") int quantity,
                   @Positive(message = "Должно быть задано положительная цена") double unit_price) {
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        idInteger = new SimpleIntegerProperty(quantity);
        nameString = new SimpleStringProperty(name);
    }

    public Product() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getIdInteger() {

        return idInteger.get();
    }

    public IntegerProperty idIntegerProperty() {
        if (idInteger == null)
            idInteger = new SimpleIntegerProperty(quantity);
        return idInteger;
    }

    public void setIdInteger(int idInteger) {
        this.idInteger.set(idInteger);
    }

    public String getNameString() {
        return nameString.get();
    }

    public StringProperty nameStringProperty() {
        if (nameString == null)
            nameString = new SimpleStringProperty(name);
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString.set(nameString);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                quantity == product.quantity &&
                Double.compare(product.unit_price, unit_price) == 0 &&
                name.equals(product.name) &&
                Objects.equals(idInteger, product.idInteger) &&
                Objects.equals(nameString, product.nameString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, unit_price, idInteger, nameString);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", idInteger=" + idInteger +
                ", nameString=" + nameString +
                '}';
    }
}
