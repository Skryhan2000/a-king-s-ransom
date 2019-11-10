package com.loneliness.entity.transmission;


import com.loneliness.entity.CustomerData;
import com.loneliness.entity.OrderData;
import com.loneliness.entity.ProductInStock;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.user.UserData;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class Transmission implements Serializable {
    private static final long serialVersionUID=2L;
    private int firstIndex;
    private int lastIndex;
    private String command;
    private UserData userData;
    private ProviderData providerData;
    private OrderData orderData;
    private CustomerData customerData;
    private ProductInStock productInStock;
    private ConcurrentHashMap<Integer, ProductInStock> ProductInStockConcurrentHashMap;
    private ConcurrentHashMap<Integer,CustomerData> customerDataConcurrentHashMap;
    private ConcurrentHashMap<Integer, UserData> userDataConcurrentHashMap;
    private ConcurrentHashMap<Integer, ProviderData> providerConcurrentHashMap;
    private ConcurrentHashMap<Integer, OrderData> orderConcurrentHashMap;

    public ProductInStock getProductInStock() {
        return productInStock;
    }

    public void setProductInStock(ProductInStock productInStock) {
        this.productInStock = productInStock;
    }

    public ConcurrentHashMap<Integer, ProductInStock> getProductInStockConcurrentHashMap() {
        return ProductInStockConcurrentHashMap;
    }

    public void setProductInStockConcurrentHashMap(ConcurrentHashMap<Integer, ProductInStock> productInStockConcurrentHashMap) {
        ProductInStockConcurrentHashMap = productInStockConcurrentHashMap;
    }

    public OrderData getOrderData() {
        return orderData;
    }

    public void setOrderData(OrderData orderData) {
        this.orderData = orderData;
    }

    public ConcurrentHashMap<Integer, OrderData> getOrderConcurrentHashMap() {
        return orderConcurrentHashMap;
    }

    public void setOrderConcurrentHashMap(ConcurrentHashMap<Integer, OrderData> orderConcurrentHashMap) {
        this.orderConcurrentHashMap = orderConcurrentHashMap;
    }

    public Transmission(String command, ConcurrentHashMap<Integer, UserData> userDataConcurrentHashMap) {
        this.command = command;
        this.userDataConcurrentHashMap = userDataConcurrentHashMap;
    }

    public Transmission(String command) {
        this.command = command;
    }

    public Transmission(){}

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public ConcurrentHashMap<Integer, UserData> getUserDataConcurrentHashMap() {
        return userDataConcurrentHashMap;
    }

    public UserData getDataConcurrentHashMap(UserData userData){
        return userDataConcurrentHashMap.get(userData.getId());
    }

    public void setUserDataConcurrentHashMap(ConcurrentHashMap<Integer, UserData> userDataConcurrentSkipListSetData) {
        this.userDataConcurrentHashMap = userDataConcurrentSkipListSetData;
    }
    public void setDataConcurrentHashMap(UserData userData){
        userDataConcurrentHashMap.put(userData.getId(),userData);
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public ProviderData getProviderData() {
        return providerData;
    }

    public void setProviderData(ProviderData providerData) {
        this.providerData = providerData;
    }

    public ConcurrentHashMap<Integer, ProviderData> getProviderConcurrentHashMap() {
        return providerConcurrentHashMap;
    }

    public void setProviderConcurrentHashMap(ConcurrentHashMap<Integer, ProviderData> providerConcurrentHashMap) {
        this.providerConcurrentHashMap = providerConcurrentHashMap;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public ConcurrentHashMap<Integer, CustomerData> getCustomerDataConcurrentHashMap() {
        return customerDataConcurrentHashMap;
    }

    public void setCustomerDataConcurrentHashMap(ConcurrentHashMap<Integer, CustomerData> customerDataConcurrentHashMap) {
        this.customerDataConcurrentHashMap = customerDataConcurrentHashMap;
    }
}
