package _cw_6.marketbuzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
