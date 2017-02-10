package learn.spring.core.entity.ws;

import learn.spring.core.entity.Event;

import javax.xml.bind.annotation.*;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventResponse")
@XmlType(name = "")
public class EventResponse {

    @XmlElement(name = "eventValue")
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
