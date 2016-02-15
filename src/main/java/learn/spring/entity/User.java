package learn.spring.entity;

import java.util.*;


public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDay;

    private Map<Integer, Ticket> ticketMap = new HashMap<Integer, Ticket>();
    private Set<Ticket> ticketSet = new HashSet<Ticket>();

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

    public Map getTicketMap() {
        return ticketMap;
    }

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    public void bookTicket(Ticket ticket) {
        ticketMap.put(ticketMap.size() + 1, ticket);
        ticketSet.add(ticket);
    }

    @Override
    public String toString() {
        return getFullName() + "(" + getEmail() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (!birthDay.equals(user.birthDay)) return false;
        return (!id.equals(user.id));
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + birthDay.hashCode();
        result = 31 * result + (ticketMap != null ? ticketMap.hashCode() : 0);
        result = 31 * result + (ticketSet != null ? ticketSet.hashCode() : 0);
        return result;
    }
}
