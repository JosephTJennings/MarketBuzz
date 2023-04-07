package _cw_6.marketbuzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import _cw_6.marketbuzz.repository.PersonRepository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    public void setCashValue(int cashValue) {
        this.cashValue = cashValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    private int cashValue;

    private int totalValue;

    private String username;
    private String password;

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

    public int getCashValue() {
        return cashValue;
    }

    public int getTotalValue() {
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

    public Person(String name){
        this.username = name;
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
