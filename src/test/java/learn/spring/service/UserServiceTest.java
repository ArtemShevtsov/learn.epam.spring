package learn.spring.service;

import learn.spring.configuration.UserConfiguration;
import learn.spring.dao.UserDAO;
import learn.spring.entity.User;
import learn.spring.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserConfiguration.class})
public class UserServiceTest {
    @Autowired
    public User testUser;
    @Mock
    UserDAO userDaoMock;
    @InjectMocks
    UserService userService;

    @Before
    public void prepare(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById(){
        assertNotNull(testUser);

        when(userDaoMock.getUserById(testUser.getId())).thenReturn(testUser);
        assertEquals(userService.getUserById(testUser.getId()), testUser);
        verify(userDaoMock, times(1)).getUserById(testUser.getId());
    }

    @Test
    public void testGetUserbyEmail(){
        assertNotNull(testUser);

        when(userDaoMock.getUserByEmail(testUser.getEmail())).thenReturn(testUser);
        assertEquals(userService.getUserByEmail(testUser.getEmail()), testUser);
    }
}
