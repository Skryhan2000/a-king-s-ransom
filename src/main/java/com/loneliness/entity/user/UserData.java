package com.loneliness.entity.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class UserData extends UserPrivateData implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    @NotNull(message = "Тип должен быть задан. ")
    private Type type;
    @NotNull(message = "Секретный ответ должен быть задан. ")
    private String secretAnswer;
    @NotNull(message = "Секретный вопрос должен быть задан. ")
    private String secretQuestion;
    private transient StringProperty typeProperty;

    public enum Type{
        ADMIN,CLIENT,MANAGER, NO_TYPE;
        private Type(){}
        private Type(int companyID){
        this.companyID=companyID;
        }
        private int companyID;

        public int getCompanyID() {
            return companyID;
        }

        public void setCompanyID(int companyID) {
            this.companyID = companyID;
        }
    }

    public Type getType(String type){
        type=type.replace(" ","_");
        return Type.valueOf(type.toUpperCase());
    }

    public UserData() {
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty.set(typeProperty);
    }

    public UserData(String login, String password) {
        super(login, password);
    }

    public UserData(String type, int id) {
        this.type = Type.valueOf(type);
        this.id = id;
        typeProperty =new SimpleStringProperty(type);
    }

    public UserData(String login, String password, String type, int id) {
        super(login, password);
        this.type = Type.valueOf(type);
        this.id = id;
        typeProperty =new SimpleStringProperty(type);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Type getType() {
        return type;
    }

    public void setType(String type) {
        type=type.replace(" ","_");
        this.type = Type.valueOf(type);
        typeProperty =new SimpleStringProperty(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getTypeProperty() {
        return typeProperty.get();
    }

    public StringProperty typePropertyProperty() {
        if(typeProperty==null){
            typeProperty =new SimpleStringProperty(type.toString());
        }
        return typeProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                type.equals(userData.type) &&
                secretAnswer.equals(userData.secretAnswer) &&
                secretQuestion.equals(userData.secretQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, id, secretAnswer, secretQuestion);
    }

    @Override
    public String toString() {
        return "UserData{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", secretAnswer='" + secretAnswer + '\'' +
                ", secretQuestion='" + secretQuestion + '\'' +
                '}';
    }
}
