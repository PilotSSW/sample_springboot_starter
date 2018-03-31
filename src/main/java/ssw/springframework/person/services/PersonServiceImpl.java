package ssw.springframework.person.services;

import ssw.springframework.person.domain.Person;
import ssw.springframework.person.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Iterable<Person> listAllPeople() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findOne(id.longValue());
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.delete(id.longValue());
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        Iterator personIterator = listAllPeople().iterator();

        while(personIterator.hasNext()){
            people.add((Person) personIterator.next());
        }

        return people;
    }

    @Override
    public Person getPersonByFullName(String name) {
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext()){
            Person somebody = peopleIterator.next();
            if(somebody.getName().equals(name)){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByFirstName(String firstName) {
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext()){
            Person somebody = peopleIterator.next();
            if(somebody.getFirstName().equals(firstName)){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByLastName(String lastName) {
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext()){
            Person somebody = peopleIterator.next();
            if(somebody.getLastName().equals(lastName)){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByBirthday(Date birthday){
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext()){
            Person somebody = peopleIterator.next();
            if (somebody.getBirthday().equals(birthday)){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByAge(int age){
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext()){
            Person somebody = peopleIterator.next();
            if(somebody.getAge().equals(age)){
                return somebody;
            }
        }

        return null;
    }

    /**
     * Will find a person first by full name, then first name and then last name. If one isn't found, null is returned.
     * @param name
     * @return
     */
    @Override
    public Person findPerson(String name){
        Person person = getPersonByFullName(name);

        person = (person == null) ? getPersonByFirstName(name) : person;

        person = (person == null) ? getPersonByFullName(name) : person;

        return person;
    }
}
