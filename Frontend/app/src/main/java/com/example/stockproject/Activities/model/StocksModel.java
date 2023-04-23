package com.example.stockproject.Activities.model;
/**
 * This class is a model for the RecyclerView to use.
 */
public class StocksModel {
    private String stockName, value;
    private int change;

    /**
     * This constructor simply assigns the values of the user.
     * @param stockName The ticker of the stock
     * @param value The value of the stock at this current time
     * @param change The change in value of the stock from its last value to its current value
     */
    public StocksModel(String stockName, String value, int change) {
        this.stockName = stockName;
        this.value = value;
        this.change = change;
    }

    /**
     * This methods returns the stock name in this model.
     * @return a String stockName
     */
    public String getStockName() {
        return stockName;
    }
    /**
     * This methods returns the value of the stock in this model.
     * @return a String value
     */
    public String getValue() { return value; }
    /**
     * This methods returns the change of the stock in this model.
     * @return an int change
     */
    public int getChange() { return change; }
}
