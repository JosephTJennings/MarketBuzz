package com.example.stockproject.Activities;

public class StocksModel {
    private String stockName, value;
    int image;


    public StocksModel(String stockName, String value, int image) {
        this.stockName = stockName;
        this.value = value;
        this.image = image;
    }

    public String getStockName() {
        return stockName;
    }
    public String getValue() { return value; }
    public int getImage() {
        return image;
    }
}
