package ssw.springframework.person.repositories;

import ssw.springframework.person.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long>{
}
