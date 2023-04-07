package com.example.stockproject.Activities;

public class StocksModel {
    private String stockName, value;
    private int change;
    public StocksModel(String stockName, String value, int change) {
        this.stockName = stockName;
        this.value = value;
        this.change = change;
    }

    public String getStockName() {
        return stockName;
    }
    public String getValue() { return value; }
    public int getChange() { return change; }
}
