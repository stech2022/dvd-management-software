package com.example.dvdmanagementsoftware.users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Token {
    private String token;

    public Token() {}

    public Token(String username, String password) throws NoSuchAlgorithmException {
        createToken(username, password);
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void createToken(String username, String password) throws NoSuchAlgorithmException {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        String stringForHashing = username + password + now;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(stringForHashing.getBytes());
        this.setToken(this.convertToHex(messageDigest));
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}
