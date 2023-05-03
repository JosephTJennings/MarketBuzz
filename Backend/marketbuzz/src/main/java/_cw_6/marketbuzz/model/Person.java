package _cw_6.marketbuzz.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

import javax.persistence.OneToMany;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    public void setCashValue(float cashValue) {
        this.cashValue = cashValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    private float cashValue;

    private float totalValue;

    private String username;
    private String password;

    private String type;

    @JsonManagedReference
    @OneToMany(mappedBy = "username")
    private List<Following> followingList;

    public List<Owns> getOwnsList() {
        return ownsList;
    }

    public void setOwnsList(List<Owns> ownsList) {
        this.ownsList = ownsList;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "owner")
    private List<Owns> ownsList;

    public float getCashValue() {
        return cashValue;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public List<Following> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<Following> followingList) {
        this.followingList = followingList;
    }

    public Person() {
    }
    public Person(String username) {
        this.username = username;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void addFollowing(Following userToFollow) {
        this.followingList.add(userToFollow);
    }
    public Person findPerson(String username) {
        return new Person();
    }

    public void addStock(Owns owns){
        this.ownsList.add(owns);
    }
}
