package _cw_6.marketbuzz.model;

import jakarta.persistence.*;

@Entity
public class Owns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;


    @Column
    private String owns;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "sid")
    private Stock stock;

    public Owns(Person owner, Stock stock){
        this.owner = owner;
        this.stock = stock;
    }

    public Owns(){}

    public void setOwner(Person owner){this.owner = owner;}

    public void setOwnedStock(Stock stock) {this.stock = stock;}

    public String getOwner() {return this.owner.getUsername();}

}

