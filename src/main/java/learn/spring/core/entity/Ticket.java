package learn.spring.core.entity;

public class Ticket {
    private EventAuditorium eventAuditorium;
    private Integer seat;

    public Ticket(){}

    public Ticket(EventAuditorium eventAuditorium, Integer seat) {
        this.eventAuditorium = eventAuditorium;
        this.seat = seat;
    }

    public EventAuditorium getEventAuditorium() {
        return eventAuditorium;
    }

    public void setEventAuditorium(EventAuditorium eventAuditorium) {
        this.eventAuditorium = eventAuditorium;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!eventAuditorium.equals(ticket.eventAuditorium)) return false;
        return seat.equals(ticket.seat);

    }

    @Override
    public int hashCode() {
        int result = eventAuditorium.hashCode();
        result = 31 * result + seat.hashCode();
        return result;
    }
}
