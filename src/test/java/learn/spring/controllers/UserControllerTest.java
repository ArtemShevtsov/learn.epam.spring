package learn.spring.controllers;

import learn.spring.configuration.TestConfiguration;
import learn.spring.core.entity.User;
import learn.spring.core.services.UserService;
import learn.spring.web.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by artem_shevtsov on 1/18/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Autowired
    List<User> userList;

    private MockMvc mockMvc;
    private String modelName;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        modelName = "usersByAttributeView";
    }

    @Test
    public void usersPage_users_shouldRenderAllUsersPage() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name(modelName))
                .andExpect(model().attribute("usersModel", userList))
                .andExpect(model().attributeDoesNotExist("attribute", "attributeName"));

        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void userPage_users_byName__shoulRender_usersByNamePage() throws Exception {
        User user = userList.get(0);
        String firstName = user.getFirstName();

        when(userService.getUsersByName(firstName)).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/users/by-name/" + firstName))
                .andExpect(status().isOk())
                .andExpect(view().name(modelName))
                .andExpect(model().attribute("usersModel", Arrays.asList(user)))
                .andExpect(model().attribute("attribute", "name"))
                .andExpect(model().attribute("attributeName", firstName));

        verify(userService, times(1)).getUsersByName(firstName);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void userPage_byId__shouldRender_userByIdPage() throws Exception {
        User user = userList.get(0);
        Integer id = user.getId();

        when(userService.getUserById(id)).thenReturn(user);

        mockMvc.perform(get("/users/by-id/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name(modelName))
                .andExpect(model().attribute("usersModel", Arrays.asList(user)))
                .andExpect(model().attribute("attribute", "ID"))
                .andExpect(model().attribute("attributeName", id));

        verify(userService, times(1)).getUserById(id);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void userPage_byEmail__shouldRendr_userByEmail() throws Exception {
        User user = userList.get(0);
        String email = user.getEmail();

        when(userService.getUserByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/users/by-email").param("mail", email))
                .andExpect(status().isOk())
                .andExpect(view().name(modelName))
                .andExpect(model().attribute("usersModel", Arrays.asList(user)))
                .andExpect(model().attribute("attribute", "e-mail"))
                .andExpect(model().attribute("attributeName", email));

        verify(userService, times(1)).getUserByEmail(email);
        verifyNoMoreInteractions(userService);
    }
}
