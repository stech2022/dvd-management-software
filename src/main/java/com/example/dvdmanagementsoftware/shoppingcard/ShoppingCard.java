package com.example.dvdmanagementsoftware.shoppingcard;

import java.util.ArrayList;

public class ShoppingCard {
    private int id;
    private int user_id;
    private int dvd_id;
    private int amount;
    private ArrayList<Integer> dvds = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<>();
    private String creationDate;
    private String state;

    public ShoppingCard() {}

    public ShoppingCard(int id, int user_id, int dvd_id, int amount, String creationDate, String state) {
        this.id = id;
        this.user_id = user_id;
        this.dvd_id = dvd_id;
        this.amount = amount;
        this.creationDate = creationDate;
        this.state = state;
    }

    public ShoppingCard(int user_id, int dvd_id, int amount, String creationDate, String state) {
        this.user_id = user_id;
        this.dvd_id = dvd_id;
        this.amount = amount;
        this.creationDate = creationDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDvd_id() {
        return dvd_id;
    }

    public void setDvd_id(int dvd_id) {
        this.dvd_id = dvd_id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
