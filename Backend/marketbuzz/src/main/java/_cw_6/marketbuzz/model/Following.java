package _cw_6.marketbuzz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Following {
    @ManyToOne
    @JoinColumn(name = "pid")
    private Person following;

    public Person getFollowing() {
        return following;
    }
}
