package com.example.dvdmanagementsoftware.shoppingcard;

import com.example.dvdmanagementsoftware.dvd.DVD;
import com.example.dvdmanagementsoftware.users.User;

import java.util.HashMap;

public class ShoppingCard {
    private int id;
    private User user;
    private int amount;
    private HashMap<DVD,Integer> payments = new HashMap<DVD, Integer>();
    private String creationDate;
    private String state;

    public ShoppingCard() {}

    public ShoppingCard(int id, User user, int amount, String creationDate, String state) {
        this.id = id;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public HashMap<DVD, Integer> getPayments() {
        return payments;
    }

    public void setPayments(DVD dvd, int payment) {
        payments.put(dvd, payment);
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
