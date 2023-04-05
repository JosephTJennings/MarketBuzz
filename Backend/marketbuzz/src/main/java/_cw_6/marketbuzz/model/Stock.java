package _cw_6.marketbuzz.model;

import jakarta.persistence.*;


@Entity
public class Stock {

    @Id
    @GeneratedValue
    private int sid;
    private String ticker;
    private int currVal;

    private int diff;

    public Stock() {
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int newVal) {
        this.diff = newVal - this.currVal;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getCurrVal() {
        return currVal;
    }

    public void setCurrVal(int currVal) {
        this.currVal = currVal;
    }

}
