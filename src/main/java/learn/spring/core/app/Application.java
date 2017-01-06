package learn.spring.core.app;

import learn.spring.core.aspects.CounterAspect;
import learn.spring.core.aspects.DiscountAspect;
import learn.spring.core.dao.EventAuditoriumDAO;
import learn.spring.core.dao.aspects.CounterAspectDAO;
import learn.spring.core.dao.aspects.DiscountAspectDAO;
import learn.spring.core.entity.*;
import learn.spring.core.services.*;
import learn.spring.core.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@ComponentScan("learn.spring.core")
public class Application {
    @Autowired
    UserService userService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    EventService eventService;
    @Autowired
    BookingService bookingService;
    @Autowired
    DiscountService discountService;
    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;

//    @Autowired
//    CounterAspect counterAspect;
    @Autowired
    CounterAspectDAO counterAspectDAO;
    @Autowired
    DiscountAspectDAO discountAspectDAO;

    private Ticket t, t1,t2;
    private User u1,u2;

    /**
     * In Application I tried to use both XML and Annotation configurations
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationCtx = new AnnotationConfigApplicationContext();
        annotationCtx.register(Application.class);
        annotationCtx.refresh();
        Application app = annotationCtx.getBean(Application.class);

//        app.initializeData();
        app.showUsersByName("oRest");
        app.printAuditoriums();
        app.printEvents();
        app.showEventsAndAuditoriums();
        app.showCounterAspectWork();
        app.showDiscountAspectWork();
        app.eventService.remove(app.eventService.getByName("Interstellar"));
    }

    @PostConstruct
    private void initializeData(){
        t = new Ticket();
        t1 = new Ticket();
        t2 = new Ticket();
        u1 = userService.getUserById(2);
        u2 = userService.getUserById(3);

        eventService.getByName("The Martian").getBasePrice();
        eventService.getByName("Django Unchained");
        eventService.getByName("Django Unchained").getBasePrice();
        t.setEventAuditorium(eventAuditoriumDAO.getEventAuditoriumByEvent(eventService.getByName("The Martian")).iterator().next());
        t.setSeat(8);
        bookingService.bookTicket(u1, t);
        t1.setEventAuditorium(eventAuditoriumDAO.getEventAuditoriumByEvent(eventService.getByName("The Martian")).iterator().next());
        t1.setSeat(9);
        bookingService.bookTicket(u1, t1);
        t2.setEventAuditorium(eventAuditoriumDAO.getEventAuditoriumByAuditorium(auditoriumService.getById(2)).iterator().next());
        t2.setSeat(5);
        bookingService.bookTicket(u2, t2);
        discountService.getDiscount(u1, eventService.getByName("The Martian"), t.getEventAuditorium().getDateAndTime());
        discountService.getDiscount(u1, eventService.getByName("The Martian"), t1.getEventAuditorium().getDateAndTime());
        discountService.getDiscount(u2, eventService.getByName("Interstellar"), t2.getEventAuditorium().getDateAndTime());
    }

    private void showCounterAspectWork(){
        System.out.println("\nCounter Aspect Work:");
        System.out.println("\n\tGet Event By Name:");

        for(Event e: counterAspectDAO.getCountedEventsByName()){
            System.out.printf("\t\tKey: %s\n\t\tValue %s\n", e, counterAspectDAO.getCounterGetByNameVal(e));
        }

        System.out.println("\n\tEvent Price Accessed:");
        for (Event e: counterAspectDAO.getCountedEventsPriceRequested()) {
            System.out.printf("\t\tKey: %s\n\t\tValue %s\n", e, counterAspectDAO.getCounterPriceRequested(e));
        }

        System.out.println("\n\tBooking Event Counter:");
        for (Event e: counterAspectDAO.getCountedEventsBooked()) {
            System.out.printf("\t\tKey: %s\n\t\tValue %s\n", e, counterAspectDAO.getCounterBooked(e));
        }
    }

    private void showDiscountAspectWork(){
        System.out.println("\nDiscount Aspect Work:");
        System.out.println("\n\tGet Total Discounts:");
        for (Class<? extends DiscountStrategy> clazz: discountAspectDAO.getCountedDiscountStrategies()) {
            System.out.printf("\t\tKey: %s\n\t\tValue %s\n", clazz.getName(), discountAspectDAO.getCounterByClass(clazz));
        }

        System.out.println("\n\tGet Discounts for Users:");
        for (Class<? extends DiscountStrategy> clazz: discountAspectDAO.getCountedDiscountStrategies()) {
            System.out.printf("\t\tDiscount: %s\n", clazz.getName());
            for(User user: discountAspectDAO.getUsersByDiscountStrategy(clazz)){
                System.out.printf("\t\t\tUser: %s\n\t\t\tValue: %s\n", user, discountAspectDAO.getCounterByClassAndUser(clazz, user));
            }
        }
    }

    public void showUsersByName(String name){
        List<User> users = userService.getUsersByName(name);
        System.out.printf("\nUserByName: %s\n", name);
        for (User u: users) {
            System.out.println(u);
        }
    }

    public void printAuditoriums(){
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        System.out.println("\nAuditoriums:");
        for (Auditorium a: auditoriums) {
            System.out.println(a);
        }
    }

    public void printEvents(){
        List<Event> events = eventService.getAll();
        System.out.println("\nEvents:");
        for (Event e: events) {
            System.out.println(e);
        }
    }

    public void showEventsAndAuditoriums(){
        System.out.println("\nEventsAndAuditoriums:");
        for(EventAuditorium ea: eventAuditoriumDAO.getAll()){
            System.out.println(ea);
        }
    }
}
