package com.example.dvdmanagementsoftware.order;

import java.util.ArrayList;

public class Order {
    private int id;
    private String address;
    private int shoppingCardId;
    private int userId;
    private String state;
    private String creationDate;
    private String completedDate;
    private String dvdIds;

    public Order() {}

    public Order(int id, String address, int shoppingCardId, int userId, String state, String creationDate, String completedDate, String dvdIds) {
        this.id = id;
        this.address = address;
        this.shoppingCardId = shoppingCardId;
        this.userId = userId;
        this.state = state;
        this.creationDate = creationDate;
        this.completedDate = completedDate;
        this.dvdIds = dvdIds;
    }

    public Order(String address, int shoppingCardId, int userId, String state, String creationDate, String completedDate, String dvdIds) {
        this.address = address;
        this.shoppingCardId = shoppingCardId;
        this.userId = userId;
        this.state = state;
        this.creationDate = creationDate;
        this.completedDate = completedDate;
        this.dvdIds = dvdIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getShoppingCardId() {
        return shoppingCardId;
    }

    public void setShoppingCardId(int shoppingCardId) {
        this.shoppingCardId = shoppingCardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getDvdIds() {
        return dvdIds;
    }

    public void setDvdIds(String dvdIds) {
        this.dvdIds = dvdIds;
    }
}
