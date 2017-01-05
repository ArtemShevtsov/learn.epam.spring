package learn.spring.service;

import learn.spring.configuration.TestConfiguration;
import learn.spring.core.dao.EventDAO;
import learn.spring.core.entity.Event;
import learn.spring.core.services.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class EventServiceTest {
    @Mock
    EventDAO eventDaoMock;
    @InjectMocks
    EventService eventService;

    @Autowired
    Event testEvent;

    @Before
    public void prepare(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByName(){
        assertNotNull(testEvent);

        when(eventDaoMock.getByName(testEvent.getName())).thenReturn(testEvent);
        assertEquals(testEvent, eventService.getByName(testEvent.getName()));
        verify(eventDaoMock).getByName(testEvent.getName());
    }

    @Test
    public void testCreate() {
        assertNotNull(testEvent);
        eventService.create(testEvent);
        verify(eventDaoMock).create(testEvent);
    }

    @Test
    public void testRemove() throws Exception {
        assertNotNull(testEvent);
        eventService.remove(testEvent);
        verify(eventDaoMock).remove(testEvent);
    }
}
