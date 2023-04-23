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
    private Stock stock;


    public Owns(Person owner, Stock stock, String quantity){
        this.owner = owner;
        this.stock = stock;
        this.quantity = Integer.valueOf(quantity);
    }

    public Owns(){}

    public void setOwner(Person owner){this.owner = owner;}

    public void setOwnedStock(Stock stock) {this.stock = stock;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public Stock getStock() {return this.stock;}

    public String getStockTicker() {return this.stock.getTicker();}

    public Person getOwner() {return this.owner;}

    public String getOwnerUsername() {return this.owner.getUsername();}

    public int getQuantity() {
        return quantity;
    }

}

