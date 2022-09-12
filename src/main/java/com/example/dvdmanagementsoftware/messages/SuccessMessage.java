package com.example.dvdmanagementsoftware.messages;

public class SuccessMessage {
    private String message;

    public SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
