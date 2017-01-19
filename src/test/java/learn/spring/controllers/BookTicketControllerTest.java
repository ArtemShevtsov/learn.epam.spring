package learn.spring.controllers;

import learn.spring.configuration.TestConfiguration;
import learn.spring.core.entity.*;
import learn.spring.core.services.AuditoriumService;
import learn.spring.core.services.BookingService;
import learn.spring.core.services.EventService;
import learn.spring.core.services.UserService;
import learn.spring.web.controllers.BookTicketController;
import learn.spring.web.controllers.UserController;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by artem_shevtsov on 1/19/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class BookTicketControllerTest {
    @Mock
    UserService userService;
    @Mock
    EventService eventService;
    @Mock
    AuditoriumService auditoriumService;
    @Mock
    BookingService bookingService;

    @InjectMocks
    BookTicketController bookTicketController;

    @Autowired
    List<User> userList;
    @Autowired
    Event testEvent;
    @Autowired
    Auditorium testAuditorium;

    private MockMvc mockMvc;
    private String modelName;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookTicketController).build();
    }

    @Test
    public void bookTickket__shouldRenderBookingPage__withAll_users_and_events_and_auditoriums() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        when(eventService.getAll()).thenReturn(Arrays.asList(testEvent));
        when(auditoriumService.getAuditoriums()).thenReturn(Arrays.asList(testAuditorium));

        mockMvc.perform(get("/book-ticket"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookTicket"))
                .andExpect(model().attribute("users", userList))
                .andExpect(model().attribute("events", Arrays.asList(testEvent)))
                .andExpect(model().attribute("auditoriums", Arrays.asList(testAuditorium)));

        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);

        verify(eventService, times(1)).getAll();
        verifyNoMoreInteractions(eventService);

        verify(auditoriumService, times(1)).getAuditoriums();
        verifyNoMoreInteractions(auditoriumService);
    }

    @Test
    public void bookTicket__shouldRenderSuccesPageAfterSubmiting() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date = new Date();
        Integer seat = 10;
        EventAuditorium ea = new EventAuditorium(testEvent, testAuditorium, date);
        Ticket ticket = new Ticket(ea, seat);

        when(userService.getUserById(userList.get(0).getId())).thenReturn(userList.get(0));
        when(eventService.getById(testEvent.getId())).thenReturn(testEvent);
        when(auditoriumService.getById(testAuditorium.getId())).thenReturn(testAuditorium);

        MockHttpServletRequestBuilder requestBuilder = post("/book-ticket")
                .param("userId", userList.get(0).getId().toString())
                .param("eventId", testEvent.getId().toString())
                .param("auditoriumId", testAuditorium.getId().toString())
                .param("seat", seat.toString())
                .param("date", simpleDateFormat.format(date));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("successBookedPage"));

        verify(userService, times(1)).getUserById(userList.get(0).getId());
        verifyNoMoreInteractions(userService);

        verify(eventService, times(1)).getById(testEvent.getId());

        verify(auditoriumService, times(1)).getById(testAuditorium.getId());
        verifyNoMoreInteractions(auditoriumService);

        verify(bookingService, times(1)).bookTicket(eq(userList.get(0)), anyObject());
        verify(bookingService, times(1)).isEventAuditoriumPresent(anyObject());
        verifyNoMoreInteractions(bookingService);
    }
}
