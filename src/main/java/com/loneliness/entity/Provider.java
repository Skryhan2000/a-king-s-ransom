package com.loneliness.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Objects;

public class Provider implements Serializable {
    private static final long serialVersionUID=2L;
    private int id;
    private String name;
    private int rating;
    private String location;
    private transient StringProperty locationString;
    private transient IntegerProperty ratingInteger;

    public int getRatingInteger() {
        return ratingInteger.get();
    }

    public IntegerProperty ratingIntegerProperty() {
        return ratingInteger;
    }

    public void setRatingInteger(int ratingInteger) {
        this.ratingInteger.set(ratingInteger);
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationString() {
        return locationString.get();
    }

    public StringProperty locationStringProperty() {
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString.set(locationString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return id == provider.id &&
                rating == provider.rating &&
                name.equals(provider.name) &&
                location.equals(provider.location) &&
                locationString.equals(provider.locationString) &&
                ratingInteger.equals(provider.ratingInteger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating, location, locationString, ratingInteger);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", location='" + location + '\'' +
                ", locationString=" + locationString +
                ", ratingInteger=" + ratingInteger +
                '}';
    }
}
