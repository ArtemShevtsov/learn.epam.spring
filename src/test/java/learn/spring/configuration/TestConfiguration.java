package learn.spring.configuration;

import learn.spring.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class TestConfiguration {

    @Bean
    public Event testEvent(){
        return new Event(1, "Film", 50., EventRating.HIGHT, 102);
    }

    @Bean
    public Auditorium testAuditorium(){
        return new Auditorium(1, "Kinozal", 50, new Integer[]{9,10});
    }

    @Bean
    public User testUser(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1988);
        return new User(1,"qqq@mail.com", "Vas", "Pup", c.getTime());
    }
}
