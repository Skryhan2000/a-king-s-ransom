package com.loneliness.entity;

import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

public class CustomerRepresentative implements Serializable {
    private static final long serialVersionUID = 8L;
    @Positive(message = "id должно быть положительным")
    private int customerRepresentativeID;
    @Positive(message = "id клиента должно быть положительным")
    private int customerID;
    @Positive(message = "id менеджера должно быть положительным")
    private int userID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCustomerRepresentativeID() {
        return customerRepresentativeID;
    }

    public void setCustomerRepresentativeID(int customerRepresentativeID) {
        this.customerRepresentativeID = customerRepresentativeID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "CustomerRepresentative{" +
                "customerRepresentativeID=" + customerRepresentativeID +
                ", customerID=" + customerID +
                ", userID=" + userID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRepresentative that = (CustomerRepresentative) o;
        return customerRepresentativeID == that.customerRepresentativeID &&
                customerID == that.customerID &&
                userID == that.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerRepresentativeID, customerID, userID);
    }
}
