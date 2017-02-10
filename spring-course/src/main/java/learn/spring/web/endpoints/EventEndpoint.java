package learn.spring.web.endpoints;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.ws.EventCollectionResponse;
import learn.spring.core.entity.ws.EventResponse;
import learn.spring.core.entity.ws.GetEventByName;
import learn.spring.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8090/ws";

    @Autowired
    private EventService eventService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEvents")
    public EventCollectionResponse getAll (){
        List<Event> allEvents = eventService.getAll();
        EventCollectionResponse eventCollectionResponse = new EventCollectionResponse();
        eventCollectionResponse.setEventCollection(allEvents);
        return eventCollectionResponse;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventByName")
    public EventResponse getEventByName(@RequestPayload GetEventByName eventRequest){
        Event event = eventService.getByName(eventRequest.getEventName());
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvent(event);
        return eventResponse;
    }
}
