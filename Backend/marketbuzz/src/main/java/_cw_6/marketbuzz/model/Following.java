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
    private String followingUsername;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Person currentUsername;

    public Following(Person currUser, String followUser){
        this.currentUsername = currUser;
        this.followingUsername = followUser;
    }

    public void setUser(Person user) {
        this.currentUsername = user;
    }

    public void setFollowingUsername(String userToFollow) {
        this.followingUsername = userToFollow;
    }

    public String getFollowing() {
        return followingUsername;
    }
}
