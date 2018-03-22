package ssw.springframework.person.services;

import ssw.springframework.person.domain.Person;

import java.util.Date;
import java.util.List;

public interface PersonService {
    Iterable<Person> listAllPeople();

    Person findPerson(String name);
    Person getPersonById(Long id);
    Person getPersonByFullName(String name);
    Person getPersonByFirstName(String name);
    Person getPersonByLastName(String name);
    Person getPersonByBirthday(Date birthday);
    Person getPersonByAge(int age);
    Person savePerson(Person person);
    List<Person> getAllPeople();

    void deletePerson(Long id);
}
