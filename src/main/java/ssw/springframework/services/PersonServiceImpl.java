package ssw.springframework.services;

import ssw.springframework.domain.Person;
import ssw.springframework.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;

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

    @Override
    public Person getPersonById(Long id) {
        return personRepository.findOne(id);
    }
    
    @Override
    public Person getPersonByName(String name) {
        boolean stillSearchingForPerson = true;
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();


        while(peopleIterator.hasNext() && stillSearchingForPerson){
            Person somebody = peopleIterator.next();
            if(somebody.getName() == name){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByBirthday(Date birthday){
        boolean stillSearchingForPerson = true;
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext() && stillSearchingForPerson){
            Person somebody = peopleIterator.next();
            if(somebody.getBirthday() == birthday){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person getPersonByAge(int age){
        boolean stillSearchingForPerson = true;
        Iterable<Person> people = personRepository.findAll();
        Iterator<Person> peopleIterator = people.iterator();

        while(peopleIterator.hasNext() && stillSearchingForPerson){
            Person somebody = peopleIterator.next();
            if(somebody.getAge() == age){
                return somebody;
            }
        }

        return null;
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.delete(id);
    }
}
