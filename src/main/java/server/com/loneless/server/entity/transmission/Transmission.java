package com.loneless.server.entity.transmission;

import com.loneless.server.entity.user.UserData;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class Transmission implements Serializable {
    private static final long serialVersionUID=2L;
    private String command;
    private UserData userData;
    private ConcurrentHashMap<Integer,UserData> dataConcurrentHashMap;

    public Transmission(String command, ConcurrentHashMap<Integer,UserData> dataConcurrentHashMap) {
        this.command = command;
        this.dataConcurrentHashMap = dataConcurrentHashMap;
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

    public ConcurrentHashMap<Integer,UserData> getDataConcurrentHashMap() {
        return dataConcurrentHashMap;
    }

    public UserData getDataConcurrentHashMap(UserData userData){
        return dataConcurrentHashMap.get(userData.getId());
    }

    public void setDataConcurrentHashMap(ConcurrentHashMap<Integer,UserData> userDataConcurrentSkipListSetData) {
        this.dataConcurrentHashMap = userDataConcurrentSkipListSetData;
    }
    public void setDataConcurrentHashMap(UserData userData){
        dataConcurrentHashMap.put(userData.getId(),userData);
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
