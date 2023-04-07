package com.example.stockproject.Activities;

public class UserStocksModel {
    private String ticker, price, quantity, pos;

    public UserStocksModel(String pos, String ticker, String price, String quantity) {
        this.ticker = ticker;
        this.pos = pos;
        this.price = price;
        this.quantity = quantity;
    }

    public String getPos() {
        return this.pos;
    }
    public String getTicker() {
        return this.ticker;
    }
    public String getPrice() {
        return this.price;
    }
    public String getQuantity() {
        return this.quantity;
    }
}
