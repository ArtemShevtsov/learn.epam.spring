package learn.spring.web.endpoints;

import learn.spring.core.entity.User;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by aftor on 29.01.17.
 */
@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/booking-web-service";

    @Autowired
    UserService userService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsers")
    public List<User> getAllUsers (){
        return userService.getAllUsers();
    }
}
