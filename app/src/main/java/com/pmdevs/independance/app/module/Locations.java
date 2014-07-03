package com.pmdevs.independance.app.module;

/**
 * Created by Administrator on 7/2/2014.
 */
public class Locations {
    private long id;
    private String city;
    private String state;
    private	String location;
    private String description;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {

        String all = getCity()+getState()+getLocation();
        return all;

    }
}
