package learn.spring.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;

    public User(Integer id, String email, String fName, String lName) {
        this.id = id;
        this.email = email;
        this.firstName = fName;
        this.lastName = lName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return getFullName() + "(" + getEmail() + ")";
    }
}
