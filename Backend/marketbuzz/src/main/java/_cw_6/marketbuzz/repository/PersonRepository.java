package _cw_6.marketbuzz.repository;

import _cw_6.marketbuzz.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}