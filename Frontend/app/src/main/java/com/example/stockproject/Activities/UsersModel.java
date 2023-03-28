package com.example.stockproject.Activities;

public class UsersModel {
    private String username;
    private int position, valuation, change, image;


    public UsersModel(String username, int position, int valuation, int change, int image) {
        this.username = username;
        this.position = position;
        this.valuation = valuation;
        this.change = change;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }
    public int getPosition() { return position; }
    public int getValuation() { return valuation; }
    public int getChange() { return change; }
    public int getImage() { return image; }
}
