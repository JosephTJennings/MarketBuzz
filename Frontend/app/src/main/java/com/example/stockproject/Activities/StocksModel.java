package com.example.stockproject.Activities;

public class StocksModel {
    private String stockName;
    int image;


    public StocksModel(String stockName, int image) {
        this.stockName = stockName;
        this.image = image;
    }

    public String getFollowerName() {
        return stockName;
    }

    public int getImage() {
        return image;
    }
}
