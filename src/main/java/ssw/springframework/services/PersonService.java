package ssw.springframework.services;


import ssw.springframework.domain.Person;

import java.util.Date;

public interface PersonService {
    Iterable<Person> listAllPeople();

    Person getPersonById(Long id);

    Person getPersonByName(String name);

    Person getPersonByBirthday(Date birthday);

    Person getPersonByAge(int age);

    Person savePerson(Person person);

    void deletePerson(Long id);
}
