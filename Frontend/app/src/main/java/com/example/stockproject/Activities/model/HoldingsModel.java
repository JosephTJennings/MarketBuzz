package com.example.stockproject.Activities.model;
/**
 * This class is a model for the RecyclerView to use.
 */
public class HoldingsModel {
    String ticker;
    double price;
    int quantity;

    int rank;
    double total;

    /**
     * This constructor simply assigns the values of the user.
     * @param rank The position the stock is in on the list
     * @param ticker The name of the stock
     * @param price The value of the stock
     * @param quantity The number of stocks
     * @param total The valuation of the stock
     */
    public HoldingsModel(int rank, String ticker, double price, int quantity, double total) {
        this.ticker = ticker;
        this.price = price;
        this.quantity = quantity;
        this.rank = rank;
        this.total = total;
    }
    /**
     * This methods returns the rank in this model.
     * @return an int rank
     */
    public int getRank() {
        return rank;
    }
    /**
     * This methods sets the rank in this model.
     * @param rank - the rank of the item
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    /**
     * This methods returns the total in this model.
     * @return an int total
     */
    public double getTotal() {
        return total;
    }
    /**
     * This methods sets the total in this model.
     * @param total the total for the item
     */
    public void setTotal(double total) {
        this.total = total;
    }
    /**
     * This methods returns the ticker in this model.
     * @return a String ticker
     */
    public String getTicker() {
        return ticker;
    }
    /**
     * This methods sets the ticker in this model.
     * @param ticker the ticker for the item
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    /**
     * This methods returns the price of one stock in this model.
     * @return an int price
     */
    public double getPrice() {
        return price;
    }
    /**
     * This methods sets the price of one stock in this model.
     * @param price the price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * This methods returns the quantity in this model.
     * @return an int quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * This methods sets the quantity in this model.
     * @param quantity the number of stocks
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
