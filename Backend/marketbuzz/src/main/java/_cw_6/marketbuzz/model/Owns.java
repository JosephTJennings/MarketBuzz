package _cw_6.marketbuzz.model;

import _cw_6.marketbuzz.repository.OwnsRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import _cw_6.marketbuzz.controller.PersonController;
import _cw_6.marketbuzz.repository.PersonRepository;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

import java.util.List;

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

