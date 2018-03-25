package ssw.springframework.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
    private String PersonName;
    private String fieldName;
    private Object fieldValue;

    public PersonNotFoundException(String message){
        super(message);
    }

    public PersonNotFoundException( String PersonName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", PersonName, fieldName, fieldValue));
        this.PersonName = PersonName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getPersonName() {
        return PersonName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}