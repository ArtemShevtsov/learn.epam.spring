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
    private static final String NAMESPACE_URI = "http://localhost:8090/";

    @Autowired
    UserService userService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFirstUser")
    public User getFirstUser (){
        return userService.getAllUsers().get(0);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByEmail")
    public User getUserByEmail (){
        return userService.getAllUsers().get(1);
    }
}