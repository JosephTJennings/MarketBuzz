package _cw_6.marketbuzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fid;

    @Column
    private String followingUser;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "username")
    private Person username;

    public Following(Person currUser, String followUser){
        this.username = currUser;
        this.followingUser = followUser;
    }

    public Following(){}

    public void setUsername(Person user) {
        this.username = user;
    }

    public void setFollowingUser(String userToFollow) {
        this.followingUser = userToFollow;
    }

    public Person getUsername() {
        return this.username;
    }

    public String getFollowingUser() {
        return followingUser;
    }
}
