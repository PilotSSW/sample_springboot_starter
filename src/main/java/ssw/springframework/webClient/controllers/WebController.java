package ssw.springframework.webClient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ssw.springframework.webClient.exception.ResourceNotFoundException;
import ssw.springframework.person.domain.Person;
import ssw.springframework.person.services.PersonServiceImpl;
import ssw.springframework.restClient.services.WebServiceImpl;

@Controller
public class WebController {

    @Autowired
    private WebServiceImpl restService;

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value = "/people", method = RequestMethod.GET)
    public String people(Model model){
        
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
    public String person(Model model, @PathVariable Integer personId) {

        model.addAttribute("person", personService.getPersonById(personId));
        return "personShow";
    }

    @RequestMapping(value = "/person/edit/{personId}", method = RequestMethod.GET)
    public String editPerson(Model model, @PathVariable Integer personId){

        model.addAttribute("person", personService.getPersonById(personId));
        return "personForm";
    }


    @RequestMapping(value = "/person/delete/{personId}", method = RequestMethod.GET)
    public String deletePerson(Model model, @PathVariable Integer personId) {
        personService.deletePerson(personId);

        // Just update the people page with the person removed
        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }


    @RequestMapping(value = "/personform", method = RequestMethod.GET)
    public String createPerson(Model model) {

        model.addAttribute("person", new Person());
        return "personform";
    }


    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(Model model, Person newPerson) {
        personService.savePerson(newPerson);

        model.addAttribute("people", personService.getAllPeople());
        return "people";
    }



    /* Exception Handler */

    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "something broke")
    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFound(Model model, ResourceNotFoundException exception){

        model.addAttribute("error", exception);
        model.addAttribute("resourceName", exception.getResourceName());
        model.addAttribute("fieldName", exception.getFieldName());
        model.addAttribute("fieldValue", exception.getFieldValue());
        model.addAttribute("stackTrace", exception.getStackTrace());

        return "error";
    }
}
