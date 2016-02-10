package learn.spring.service;

import com.sun.deploy.util.ArrayUtil;
import learn.spring.configuration.TestConfiguration;
import learn.spring.dao.UserDAO;
import learn.spring.entity.*;
import learn.spring.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class UserServiceTest {
    @Autowired
    User testUser;
    EventAuditorium testEventAuditorium;
    Ticket testTicket;


    @Autowired
    ApplicationContext appContext;

    @Mock
    UserDAO userDaoMock;
    @InjectMocks
    UserService userService = new UserService();

    @Before
    public void prepare(){
        MockitoAnnotations.initMocks(this);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 15);
        c.set(Calendar.HOUR, 12);
        c.set(Calendar.MINUTE, 50);
        c.set(Calendar.MILLISECOND, 0);
        testEventAuditorium = new EventAuditorium((Event) appContext.getBean("testEvent"), (Auditorium) appContext.getBean("testAuditorium"), c.getTime());
        testTicket = new Ticket();
        testTicket.setEventAuditorium(testEventAuditorium);
        testTicket.setSeat(9);
        testUser.bookTicket(testTicket);
    }

    @Test
    public void testGetUserById(){
        assertNotNull(testUser);

        when(userDaoMock.getUserById(testUser.getId())).thenReturn(testUser);
        assertEquals(testUser, userService.getUserById(testUser.getId()));
        verify(userDaoMock, times(1)).getUserById(testUser.getId());
    }

    @Test
    public void testGetUserbyEmail(){
        assertNotNull(testUser);

        when(userDaoMock.getUserByEmail(testUser.getEmail())).thenReturn(testUser);
        assertEquals(testUser, userService.getUserByEmail(testUser.getEmail()));
    }

    @Test
    public void testGetUserbyName(){
        assertNotNull(testUser);
        List<User> users = new ArrayList<User>();
        users.add(testUser);
        when(userDaoMock.getUsersByName(testUser.getFirstName())).thenReturn(users);
        assertEquals(users, userService.getUsersByName(testUser.getFirstName()));
    }

    @Test
    public void testRegisterUser(){
        userService.register(testUser);
        verify(userDaoMock).register(testUser);
    }

    @Test
    public void testRemoveUser() {
        userService.remove(testUser);
        verify(userDaoMock).remove(testUser);
    }

    @Test
    public void testGetBookedTickets() {
        assertNotNull(testUser);
        when(userDaoMock.getUserById(testUser.getId())).thenReturn(testUser);
        Set<Ticket> ticketSet= new HashSet<Ticket>();
        ticketSet.add(testTicket);
        assertEquals(ticketSet, userService.getBookedTickets(testUser));
        verify(userDaoMock).getUserById(testUser.getId());
    }
}
