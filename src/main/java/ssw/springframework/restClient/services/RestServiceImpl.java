package ssw.springframework.restClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ssw.springframework.person.domain.Person;
import ssw.springframework.person.services.PersonServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestServiceImpl {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PersonServiceImpl personService;

    @Value("${resource.endpoint}")
    private String endPoint;

    
    public String getUserBirthday(String name){
        return restTemplate.getForObject(endPoint + "/" + name + "/birthday", String.class);
    }

    public String getUserAge(String name){
        return restTemplate.getForObject(endPoint + "/" + name + "/age", String.class);
    }

    public void addNewUser(Person person){
        restTemplate.postForObject(endPoint + "/user", person, Person.class);
    }

    public List<String> getUserNames() {
        return Arrays
                .stream(restTemplate.getForObject(endPoint + "/user/names", String[].class))
                .collect(Collectors.toList());
    }

    public List<String> getUserAges() {
        return Arrays
                .stream(restTemplate.getForObject(endPoint + "/user/ages", String[].class))
                .collect(Collectors.toList());
    }

    public List<String> getUserBirthdays() {
        return Arrays
                .stream(restTemplate.getForObject(endPoint + "/user/birthdays", String[].class))
                .collect(Collectors.toList());
    }
}
