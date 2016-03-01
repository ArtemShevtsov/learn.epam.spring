package learn.spring.core.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class represent many to many relation between event and auditorium.
 */
public class EventAuditorium {
    private Event event;
    private Auditorium auditorium;
    private Date dateAndTime;

    public EventAuditorium(Event event, Auditorium auditorium, Date dateAndTime) {
        this.event = event;
        this.auditorium = auditorium;
        this.dateAndTime = dateAndTime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        return String.format("Event: %s in Auditorium %s at %s", event, auditorium, df.format(dateAndTime));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventAuditorium that = (EventAuditorium) o;

        if (!event.equals(that.event)) return false;
        if (!auditorium.equals(that.auditorium)) return false;
        return dateAndTime.equals(that.dateAndTime);

    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + auditorium.hashCode();
        result = 31 * result + dateAndTime.hashCode();
        return result;
    }
}
