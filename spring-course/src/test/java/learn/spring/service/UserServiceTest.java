package learn.spring.service;

import learn.spring.configuration.TestConfiguration;
import learn.spring.core.dao.UserDAO;
import learn.spring.core.entity.*;
import learn.spring.core.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class UserServiceTest {

    // TODO: +3% tests are partially implemented

    @Autowired
    User testUser_1;
    EventAuditorium testEventAuditorium;
    Ticket testTicket;


    @Autowired
    ApplicationContext appContext;

    @Mock
    UserDAO userDaoMock;
    @InjectMocks
    UserService userService;

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
        testUser_1.bookTicket(testTicket);
    }

    @Test
    public void testGetUserById(){
        assertNotNull(testUser_1);

        when(userDaoMock.getUserById(testUser_1.getId())).thenReturn(testUser_1);
        assertEquals(testUser_1, userService.getUserById(testUser_1.getId()));
        verify(userDaoMock, times(1)).getUserById(testUser_1.getId());
    }

    @Test
    public void testGetUserbyEmail(){
        assertNotNull(testUser_1);

        when(userDaoMock.getUserByEmail(testUser_1.getEmail())).thenReturn(testUser_1);
        assertEquals(testUser_1, userService.getUserByEmail(testUser_1.getEmail()));
    }

    @Test
    public void testGetUserbyName(){
        assertNotNull(testUser_1);
        List<User> users = new ArrayList<User>();
        users.add(testUser_1);
        when(userDaoMock.getUsersByName(testUser_1.getFirstName())).thenReturn(users);
        assertEquals(users, userService.getUsersByName(testUser_1.getFirstName()));
    }

    @Test
    public void testRegisterUser(){
        userService.register(testUser_1);
        verify(userDaoMock).register(testUser_1);
    }

    @Test
    public void testRemoveUser() {
        userService.remove(testUser_1);
        verify(userDaoMock).remove(testUser_1);
    }

//    @Test
//    public void testGetBookedTickets() {
//        assertNotNull(testUser_1);
//        when(userDaoMock.getUserById(testUser_1.getId())).thenReturn(testUser_1);
//        Set<Ticket> ticketSet= new HashSet<Ticket>();
//        ticketSet.add(testTicket);
//        assertEquals(ticketSet, userService.getBookedTicketsByUser(testUser_1));
//        verify(userDaoMock).getUserById(testUser_1.getId());
//    }
}
