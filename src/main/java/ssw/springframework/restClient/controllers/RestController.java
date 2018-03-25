package ssw.springframework.restClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ssw.springframework.exception.ResourceNotFoundException;
import ssw.springframework.person.domain.Person;
import ssw.springframework.person.services.PersonServiceImpl;
import ssw.springframework.restClient.services.RestServiceImpl;

@Controller
public class RestController {

    @Autowired
    private RestServiceImpl restService;

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String people(Model model){
        
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "/addNewPerson", method = RequestMethod.GET)
    public String addPerson(){
        return "addNewPerson";
    }

    @RequestMapping(value = "/personForm", method = RequestMethod.POST)
    public String person(Person person){

        personService.savePerson(person);
        return "personForm";
    }

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "something broke")
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFound(Model model, ResourceNotFoundException exception){

        model.addAttribute("error", exception);
        return "error";
    }
}
