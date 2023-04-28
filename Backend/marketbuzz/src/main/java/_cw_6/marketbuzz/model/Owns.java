package _cw_6.marketbuzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
@Entity
public class Owns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;


    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "username")
    private Person owner;


    @ManyToOne
    @JoinColumn(name = "ticker")
    private StaticStock staticStock;


    public Owns(Person owner, StaticStock staticStock, String quantity){
        this.owner = owner;
        this.staticStock = staticStock;
        this.quantity = Integer.valueOf(quantity);
    }

    public Owns(){}

    public void setOwner(Person owner){this.owner = owner;}

    public void setOwnedStock(StaticStock staticStock) {this.staticStock = staticStock;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public StaticStock getStock() {return this.staticStock;}

    public String getStockTicker() {return this.staticStock.getTicker();}

    public Person getOwner() {return this.owner;}

    public String getOwnerUsername() {return this.owner.getUsername();}

    public int getQuantity() {
        return quantity;
    }

}

