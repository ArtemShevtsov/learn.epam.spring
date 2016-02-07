package learn.spring.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;


public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDay;

    private Map<Integer, Ticket> bookedTickets = new HashMap<Integer, Ticket>();

    public User(Integer id, String email, String fName, String lName, Date birthDay) {
        this.id = id;
        this.email = email;
        this.firstName = fName;
        this.lastName = lName;
        this.birthDay = birthDay;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Map getBookedTickets() {
        return bookedTickets;
    }

    public void addTicket(Ticket ticket) {
        bookedTickets.put(bookedTickets.size() + 1, ticket);
    }

    @Override
    public String toString() {
        return getFullName() + "(" + getEmail() + ")";
    }
}
