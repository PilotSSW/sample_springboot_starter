package ssw.springframework.repositories;

import ssw.springframework.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long>{
}
