package learn.spring.app;

import learn.spring.configuration.AuditoriumsConfig;
import learn.spring.dao.EventAuditoriumDAO;
import learn.spring.entity.Auditorium;
import learn.spring.entity.EventAuditorium;
import learn.spring.entity.User;
import learn.spring.services.AuditoriumService;
import learn.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Application {
    @Autowired
    UserService userService;
    @Autowired
    AuditoriumService auditoriumService;

    /**
     * In Application I tried to use both XML and Annotation configurations
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext xmlCtx = new ClassPathXmlApplicationContext("spring/context.xml");
        AnnotationConfigApplicationContext annotationCtx = new AnnotationConfigApplicationContext();
        annotationCtx.register(AuditoriumsConfig.class);
        annotationCtx.refresh();

        Application app = xmlCtx.getBean(Application.class);
        app.showUsersByName("oRest");
        app.showAuditoriums();
        app.showEventsAndAuditoriums();
    }

    public void showUsersInMaps(){
        userService.showUsersInMaps();
    }

    public void showUsersByName(String name){
        List<User> users = userService.getUsersByName(name);
        System.out.printf("\nUserByName: %s\n", name);
        for (User u: users) {
            System.out.println(u);
        }
    }

    public void showAuditoriums(){
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        System.out.println("\nAuditoriums:");
        for (Auditorium a: auditoriums) {
            System.out.println(a);
        }
    }

    public void showEventsAndAuditoriums(){
        System.out.println("\nEventsAndAuditoriums:");
        for(EventAuditorium ea: EventAuditoriumDAO.EventAuditoriumSet){
            System.out.println(ea);
        }
    }
}
