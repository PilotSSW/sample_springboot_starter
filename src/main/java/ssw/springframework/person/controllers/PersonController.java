package ssw.springframework.person.controllers;

import org.springframework.web.bind.annotation.*;
import ssw.springframework.person.domain.Person;
import ssw.springframework.person.exception.PersonNotFoundException;
import ssw.springframework.person.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "{name}/birthday", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getBirthdayFor(@PathVariable String name) {

        Person person = personService.findPerson(name);
        if (person != null) {
            return new ResponseEntity<>(person.getBirthday(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new PersonNotFoundException("Person named " + name + " not found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{name}/age", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAgeFor(@PathVariable String name) {

        Person person = personService.findPerson(name);
        if (person != null) {
            return new ResponseEntity<>(person.getAge(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new PersonNotFoundException("Person named " + name + " not found"), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addNewUser(@RequestBody Person person) {

        personService.savePerson(person);

        Person checkForAddedPerson = personService.findPerson(person.getName());

        if (checkForAddedPerson != null &&
            checkForAddedPerson.getAge() == person.getAge() &&
            checkForAddedPerson.getBirthday() == person.getBirthday()) {
                return new ResponseEntity<>(person, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(
                    new PersonNotFoundException("User was not able to be created"),
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
