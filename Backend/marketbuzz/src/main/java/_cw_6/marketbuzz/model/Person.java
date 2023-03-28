package _cw_6.marketbuzz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import jakarta.persistence.OneToMany;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String username;
    private String password;
    @OneToMany(mappedBy = "stock")
    private List<Stock> stockList;
    @OneToMany(mappedBy = "following")
    private List<Following> followingList;

    public List<Following> getFollowingList() {
        return followingList;
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void setFollowingList(List<Following> followingList) {
        this.followingList = followingList;
    }

    public Person() {
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
