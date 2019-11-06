package com.loneliness.entity.transmission;


import com.loneliness.entity.Order;
import com.loneliness.entity.Provider;
import com.loneliness.entity.user.UserData;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class Transmission implements Serializable {
    private static final long serialVersionUID=2L;
    private int firstIndex;
    private int lastIndex;
    private String command;
    private UserData userData;
    private Provider provider;
    private Order order;
    private ConcurrentHashMap<Integer, UserData> userDataConcurrentHashMap;
    private ConcurrentHashMap<Integer,Provider> providerConcurrentHashMap;
    private ConcurrentHashMap<Integer,Order> orderConcurrentHashMap;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ConcurrentHashMap<Integer, Order> getOrderConcurrentHashMap() {
        return orderConcurrentHashMap;
    }

    public void setOrderConcurrentHashMap(ConcurrentHashMap<Integer, Order> orderConcurrentHashMap) {
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public ConcurrentHashMap<Integer, Provider> getProviderConcurrentHashMap() {
        return providerConcurrentHashMap;
    }

    public void setProviderConcurrentHashMap(ConcurrentHashMap<Integer, Provider> providerConcurrentHashMap) {
        this.providerConcurrentHashMap = providerConcurrentHashMap;
    }
}
