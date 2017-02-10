package learn.spring.web.endpoints;

import learn.spring.core.entity.User;
import learn.spring.core.entity.ws.GetUserByEmail;
import learn.spring.core.entity.ws.GetUserById;
import learn.spring.core.entity.ws.UserResponse;
import learn.spring.core.entity.ws.UserCollectionResponse;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

/**
 * Created by aftor on 29.01.17.
 */
@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8090/ws";

    @Autowired
    UserService userService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsers")
    public UserCollectionResponse getAll (){
        List<User> allUsers = userService.getAllUsers();
        UserCollectionResponse userCollectionResponse = new UserCollectionResponse();
        userCollectionResponse.setUserCollection(allUsers);
        return userCollectionResponse;
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

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByEmail")
    public UserResponse getUserByEmail(@RequestPayload GetUserByEmail userRequest){
        String email = userRequest.getEmail();
        User userByEmail = userService.getUserByEmail(email);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(userByEmail);
        return userResponse;
    }
}