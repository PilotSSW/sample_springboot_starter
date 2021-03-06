package ssw.springframework.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import ssw.springframework.domain.Person;
import ssw.springframework.exception.ResourceNotFoundException;
import ssw.springframework.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public void setPersonService(ssw.springframework.services.PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "{name}/birthday", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getBirthdayFor(@PathVariable String name) {

        Person person = personService.getPersonByName(name);
        if(person != null) {
            return new ResponseEntity<>(person.getBirthday(), HttpStatus.OK);
        }

        return null;
    }

    @RequestMapping(value = "{name}/age", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAgeFor(@PathVariable String name) {

        Person person = personService.getPersonByName(name);
        if(person != null) {
            return new ResponseEntity<>(person.getAge(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResourceNotFoundException("Person named " + name + " not found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addNewUser(@RequestBody Person person) {

        personService.savePerson(person);

        Person checkForAddedPerson = personService.getPersonByName(person.getName());

        if(checkForAddedPerson.getAge() == person.getAge() && checkForAddedPerson.getBirthday() == person.getBirthday()) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(
                    new ResourceNotFoundException("User was not able to be created"),
                    HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @RequestMapping(value = "user/names", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsersNames() {

        List<String> names = new ArrayList<>();
        personService.listAllPeople().forEach(person -> names.add(person.getName()));

        return new ResponseEntity<>(names, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "user/ages", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsersAges() {

        List<Integer> ages = new ArrayList<>();
        personService.listAllPeople().forEach(person -> ages.add(person.getAge()));

        return new ResponseEntity<>(ages, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "user/birthdays", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUsers() {

        List<String> birthdays = new ArrayList<>();
        personService.listAllPeople().forEach(person -> birthdays.add(person.getBirthday().toString()));

        return new ResponseEntity<>(birthdays, HttpStatus.ACCEPTED);
    }
}
