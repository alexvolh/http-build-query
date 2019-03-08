package com.apnot.hquery.model;

public class Details {

    private String user;
    private String password;
    private Integer profileID;
    private Integer customerID;
    private String customerTransID;

    public Details() {
    }

    public Details(String user, String password, Integer profileID, Integer customerID, String customerTransID) {
        this.user = user;
        this.password = password;
        this.profileID = profileID;
        this.customerID = customerID;
        this.customerTransID = customerTransID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getProfileID() {
        return profileID;
    }

    public void setProfileID(Integer profileID) {
        this.profileID = profileID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerTransID() {
        return customerTransID;
    }

    public void setCustomerTransID(String customerTransID) {
        this.customerTransID = customerTransID;
    }

    @Override
    public String toString() {
        return "Details{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", profileID=" + profileID +
                ", customerID=" + customerID +
                ", customerTransID='" + customerTransID + '\'' +
                '}';
    }
}
