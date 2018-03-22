package ssw.springframework.repositories;

/*import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApplication.class)
public class PersonRepositoryTest {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    public void testSavePerson(){
        //setup person
        Person person = new Person();
        person.setAge(25);
        person.setName("Tony Stark");
        person.setBirthday(new Date("04/11/1992"));

        //save person, verify has ID value after save
        assertNull(person.getPersonId()); //null before save
        personRepository.save(person);
        assertNotNull(person.getPersonId()); //not null after save

        //fetch from DB
        Person fetchedPerson = personRepository.findOne(person.getPersonId());

        //should not be null
        assertNotNull(fetchedPerson);

        //should equal
        assertEquals(person.getPersonId(), fetchedPerson.getPersonId());
        assertEquals(person.getName(), fetchedPerson.getName());

        //update description and save
        fetchedPerson.setBirthday(new Date("01/01/1000"));
        personRepository.save(fetchedPerson);

        //get from DB, should be updated
        Person fetchedUpdatedPerson = personRepository.findOne(fetchedPerson.getPersonId());
        assertEquals(fetchedPerson.getBirthday(), fetchedUpdatedPerson.getBirthday());

        //verify count of persons in DB
        long personCount = personRepository.count();
        assertEquals(personCount, 1);

        //get all persons, list should only have one
        Iterable<Person> persons = personRepository.findAll();

        int count = 0;

        for(Person p : persons){
            count++;
        }

        assertEquals(count, 1);
    }
}*/
