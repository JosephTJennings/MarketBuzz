package _cw_6.marketbuzz.repository;

import _cw_6.marketbuzz.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import _cw_6.marketbuzz.model.Person;
import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    @Query("SELECT username FROM Following WHERE username.pid = :#{user.username}") public List<Person> getUsersFollowings(Person user);
    //NO IDEA IF THIS WORKS
}