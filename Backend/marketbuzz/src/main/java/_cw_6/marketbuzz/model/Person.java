package _cw_6.marketbuzz.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String username;
    private String password;
    @OneToMany(mappedBy = "following")
    private List<Following> followingList;

    public List<Following> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<Following> followingList) {
        this.followingList = followingList;
    }

    @ManyToMany
    @JoinTable(name = "sid")
    private Set<Stock> stocks = new HashSet<Stock>();

    public Person() {
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
        //stock.getSid().add();
    }

    public void removeStock(int sid){
        Stock stock = this.stocks.stream().filter(i -> i.getSid() == sid).findFirst().orElse(null);
        if (stock != null){
            this.stocks.remove(stock);
            //stock.getSid().remove(this);
        }
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFollowing(String userToFollow) {
        Following newFollowing = new Following(this, userToFollow);
        this.followingList.add(newFollowing);
    }
}
