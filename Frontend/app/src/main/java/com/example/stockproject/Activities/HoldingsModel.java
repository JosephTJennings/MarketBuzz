package com.example.stockproject.Activities;

public class HoldingsModel {
    String ticker;
    int price;
    int quantity;

    int rank;
    int total;

    public HoldingsModel(int rank, String ticker, int price, int quantity, int total) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.rank = rank;
        this.total = total;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
