package ssw.springframework.person.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "birthday")
    public Date birthday;

    @Column(name = "age")
    public Integer age;

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getFirstName() { return this.firstName; }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getLastName() { return this.lastName; }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date day) {
        this.birthday = day;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Long getPersonId() {
        return id;
    }
}
