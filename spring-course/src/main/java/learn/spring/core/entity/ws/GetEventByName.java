package learn.spring.core.entity.ws;

import javax.xml.bind.annotation.*;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getEventByName")
@XmlType(name = "")
public class GetEventByName {

    @XmlElement(name = "eventName")
    String eventName;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
