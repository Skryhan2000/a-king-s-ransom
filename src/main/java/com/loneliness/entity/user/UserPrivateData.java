package com.loneliness.entity.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class UserPrivateData implements Serializable {
    private String login;
    private String password;
    private static final long serialVersionUID=0L;
    private transient StringProperty loginProperty;

    public UserPrivateData() {
    }

    public UserPrivateData(String login, String password) {
        this.login = login;
        this.password = password;
        loginProperty=new SimpleStringProperty(login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        loginProperty=new SimpleStringProperty(login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginProperty() {
        return loginProperty.get();
    }

    public StringProperty loginPropertyProperty() {
        if(loginProperty==null){
            loginProperty=new SimpleStringProperty(login);
        }
        return loginProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrivateData that = (UserPrivateData) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "UserPrivateData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
