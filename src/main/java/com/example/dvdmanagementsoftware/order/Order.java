package com.example.dvdmanagementsoftware.order;

import com.example.dvdmanagementsoftware.shoppingcard.ShoppingCard;
import com.example.dvdmanagementsoftware.users.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Order {
    private int id;
    private String address;
    private ShoppingCard shoppingCard;
    private User user;
    private String state;
    private String creationDate;
    private String completedDate;
    private ArrayList<Integer> dvdIds = new ArrayList<Integer>();

    public Order() {}

    public Order(int id, String address, ShoppingCard shoppingCard, User user, String state, String creationDate, String completedDate, ArrayList<Integer> dvdIds) {
        this.id = id;
        this.address = address;
        this.shoppingCard = shoppingCard;
        this.user = user;
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

    public ShoppingCard getShoppingCard() {
        return shoppingCard;
    }

    public void setShoppingCard(ShoppingCard shoppingCard) {
        this.shoppingCard = shoppingCard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public ArrayList<Integer> getDvdIds() {
        return dvdIds;
    }

    public void setDvdIds(ArrayList<Integer> dvdIds) {
        this.dvdIds = dvdIds;
    }
}
