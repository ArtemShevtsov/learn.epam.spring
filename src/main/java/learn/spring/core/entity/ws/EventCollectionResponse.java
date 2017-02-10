package learn.spring.core.entity.ws;

import learn.spring.core.entity.Event;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eventCollectionResponse")
@XmlType(name = "")
public class EventCollectionResponse {

    @XmlElement(name = "eventCollection")
    List<Event> eventCollection;

    public List<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(List<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }
}
