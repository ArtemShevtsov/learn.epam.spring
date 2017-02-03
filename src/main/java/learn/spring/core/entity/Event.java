package learn.spring.core.entity;

import javax.xml.bind.annotation.*;

public class Event {
    private Integer id;
    private String name;
    private Double basePrice;
    private EventRating rating;
    private Integer minutesLength;

    public Event(){}

    public Event(Integer id, String name, Double basePrice, EventRating rating, Integer minutesLength) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
        this.minutesLength = minutesLength;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public Integer getMinutesLength() {
        return minutesLength;
    }

    public void setMinutesLength(Integer minutesLength) {
        this.minutesLength = minutesLength;
    }

    @Override
    public String toString() {
        return getName() + " (" + String.valueOf(getMinutesLength()) + "min.)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false; /*Because of Aspects*/
        if (o == null) return false;

        Event event = (Event) o;

        if (!id.equals(event.getId())) return false;
        if (!name.equals(event.getName())) return false;
        if (basePrice != null ? !basePrice.equals(event.getBasePrice()) : event.getBasePrice() != null) return false;
        return minutesLength.equals(event.getMinutesLength());

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        result = 31 * result + minutesLength.hashCode();
        return result;
    }
}
