package com.loneless.server.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class UserData extends UserPrivateData implements Serializable {
    private static final long serialVersionUID=1L;
    private String type;
    private int id;

    public UserData() {
    }

    public UserData(String login, String password) {
        super(login, password);
    }

    public UserData(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public UserData(String login, String password, String type, int id) {
        super(login, password);
        this.type = type;
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(type, userData.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, id);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
