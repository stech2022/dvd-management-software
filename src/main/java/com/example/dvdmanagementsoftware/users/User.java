package com.example.dvdmanagementsoftware.users;

public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String address;
    private String cardType;
    private String cardNumber;
    private String cardExpirationDate;
    private String cardCVV;

    private String token;

    public User() {}

    public User(int id,
                String username,
                String password,
                String firstName,
                String lastName,
                String role,
                String address,
                String cardType,
                String cardNumber,
                String cardExpirationDate,
                String cardCVV,
                String token
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCVV = cardCVV;
        this.token = token;
    }

    public User(
                String username,
                String password,
                String firstName,
                String lastName,
                String role,
                String address,
                String cardType,
                String cardNumber,
                String cardExpirationDate,
                String cardCVV,
                String token
    ) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCVV = cardCVV;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return firstName;
    }

}