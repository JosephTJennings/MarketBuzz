package _cw_6.marketbuzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class StaticStock {

    @Id
    @GeneratedValue
    private int sid;
    private String ticker;
    private float currVal;

    private float diff;


    @OneToMany(mappedBy = "staticStock")
    private List<Owns> stocksOwnedList;

    public StaticStock() {
        System.out.println("Printing from the default static stock constructor");
    }

    public StaticStock(String s){
        System.out.println("Printing " + s + " from the static stock constructor");
        this.ticker = s;
    }

    public float getDiff() {
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

    public float getCurrVal() {
        return currVal;
    }

    public void setCurrVal(int currVal) {
        this.currVal = currVal;
    }


}
