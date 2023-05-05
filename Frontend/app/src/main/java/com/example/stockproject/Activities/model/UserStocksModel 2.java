package com.example.stockproject.Activities.model;
/**
 * This class is a model for the RecyclerView to use.
 */
public class UserStocksModel {
    private String ticker, price, quantity, pos;

    /**
     * This constructor simply assigns the values of the user.
     * @param pos The position of the ticker in the list
     * @param ticker The name of the stock
     * @param price The value of the stock
     * @param quantity The number of stocks
     */
    public UserStocksModel(String pos, String ticker, String price, String quantity) {
        this.ticker = ticker;
        this.pos = pos;
        this.price = price;
        this.quantity = quantity;
    }
    /**
     * This methods returns the position in this model.
     * @return a String pos
     */
    public String getPos() {
        return this.pos;
    }
    /**
     * This methods returns the ticker in this model.
     * @return a String ticker
     */
    public String getTicker() {
        return this.ticker;
    }
    /**
     * This methods returns the price in this model.
     * @return a String price
     */
    public String getPrice() {
        return this.price;
    }
    /**
     * This methods returns the quantity in this model.
     * @return a String quantity
     */
    public String getQuantity() {
        return this.quantity;
    }
}
