package ssw.springframework.person.bootstrap;

import ssw.springframework.person.domain.Person;
import ssw.springframework.person.repositories.PersonRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class PersonLoader implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;

    private Logger log = Logger.getLogger(PersonLoader.class);

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        long personCount = personRepository.count();

        log.info("Person Count is:" + personCount);

        if(personCount == 0){
            Person creator = new Person();

            creator.setFirstName("Tony");
            creator.setLastName("Stark");
            creator.setBirthday(new Date("04/11/1992"));
            creator.setAge(25);

            personRepository.save(creator);
            log.info("Saved creator - id:" + creator.getPersonId());
        }
    }
}
