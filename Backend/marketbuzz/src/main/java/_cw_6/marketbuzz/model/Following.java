package _cw_6.marketbuzz.model;

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
    private String following;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Person username;

    public Following(Person currUser, String followUser){
        this.username = currUser;
        this.following = followUser;
    }

    public Following(){}

    public void setUser(Person user) {
        this.username = user;
    }

    public void setFollowingUsername(String userToFollow) {
        this.following = userToFollow;
    }

    public String getFollowing() {
        return following;
    }
}
