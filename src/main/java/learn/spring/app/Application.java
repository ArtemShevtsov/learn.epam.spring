package learn.spring.app;

import learn.spring.configuration.AppConfiguration;
import learn.spring.entity.Auditorium;
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

    public static void main(String[] args) {
        ConfigurableApplicationContext xmlCtx = new ClassPathXmlApplicationContext("spring/context.xml");
        AnnotationConfigApplicationContext annotationCtx = new AnnotationConfigApplicationContext();
        annotationCtx.register(AppConfiguration.class);
        annotationCtx.refresh();

        Application app = xmlCtx.getBean(Application.class);
        app.showUsersByName("oRest");
        app.showAuditoriums();
    }

    public void showUsersInMaps(){
        userService.showUsersInMaps();
    }

    public void showUsersByName(String name){
        List<User> users = userService.getUsersByName(name);
        System.out.printf("UserByName: %s\n", name);
        for (User u: users) {
            System.out.println(u);
        }
    }

    public void showAuditoriums(){
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();
        System.out.println("Auditoriums:");
        for (Auditorium a: auditoriums) {
            System.out.println(a);
        }
    }
}
