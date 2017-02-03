package learn.spring.web.endpoints;

import learn.spring.core.entity.User;
import learn.spring.core.entity.ws.GetUserById;
import learn.spring.core.entity.ws.UserResponse;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by aftor on 29.01.17.
 */
@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8090/ws";

    @Autowired
    UserService userService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFirstUser")
    public UserResponse getFirstUser (){
        User user = userService.getAllUsers().get(0);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(user);
        return userResponse;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserById")
    public UserResponse getUserById (@RequestPayload GetUserById userRequest){
        Integer userId = userRequest.getUserId();
        User user = userService.getUserById(userId);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(user);
        return userResponse;
    }
}