package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class CustomerData implements Serializable {
    private static final long serialVersionUID=4L;
    private int id;
    private String name;
    private int numberOfOrders;
    private String location;
    private String email;
    private transient StringProperty nameString;
    private transient IntegerProperty numberOfOrdersInteger;

    public CustomerData(int id, String name, int numberOfOrders, String location, String email, StringProperty nameString, IntegerProperty numberOfOrdersInteger) {
        this.id = id;
        this.name = name;
        this.numberOfOrders = numberOfOrders;
        this.location = location;
        this.email = email;
        this.nameString = nameString;
        this.numberOfOrdersInteger = numberOfOrdersInteger;
    }

    public CustomerData() {
    }

    public CustomerData(int id, String name, int numberOfOrders, String location, String email) {
        this.id = id;
        this.name = name;
        this.numberOfOrders = numberOfOrders;
        this.location = location;
        this.email = email;
        nameString=new SimpleStringProperty(name);
        numberOfOrdersInteger=new SimpleIntegerProperty(numberOfOrders);
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
        nameString=new SimpleStringProperty(name);
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        numberOfOrdersInteger=new SimpleIntegerProperty(numberOfOrders);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameString() {
        return nameString.get();
    }

    public StringProperty nameStringProperty() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString.set(nameString);
    }

    public int getNumberOfOrdersInteger() {
        return numberOfOrdersInteger.get();
    }

    public IntegerProperty numberOfOrdersIntegerProperty() {
        return numberOfOrdersInteger;
    }

    public void setNumberOfOrdersInteger(int numberOfOrdersInteger) {
        this.numberOfOrdersInteger.set(numberOfOrdersInteger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerData customerData = (CustomerData) o;
        return id == customerData.id &&
                numberOfOrders == customerData.numberOfOrders &&
                name.equals(customerData.name) &&
                location.equals(customerData.location) &&
                email.equals(customerData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfOrders, location, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfOrders=" + numberOfOrders +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
