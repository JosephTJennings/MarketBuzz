package com.example.stockproject.Activities;

public class UsersModel {
    private String username, valuation, pos;
    private int image;


    public UsersModel(String username, String pos, String valuation, int image) {
        this.username = username;
        this.pos = pos;
        this.valuation = valuation;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }
    public String getPos() { return pos; }
    public String getValuation() { return valuation; }
    public int getImage() { return image; }
}
