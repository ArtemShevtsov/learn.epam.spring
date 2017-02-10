package learn.spring.configuration;

import learn.spring.core.entity.Auditorium;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.EventRating;
import learn.spring.core.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;

@Configuration
public class TestConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Event testEvent(){
        return new Event(1, "Film", 50., EventRating.HIGHT, 102);
    }

    @Bean
    public Auditorium testAuditorium(){
        return new Auditorium(1, "Kinozal", 50, new Integer[]{9,10});
    }

    @Bean
    public User testUser_1(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1988);
        User user = new User(1, "qqq@mail.com", "Vas", "Pup", c.getTime());
        user.setPassword(passwordEncoder().encode("qqq"));
        return user;
    }

    @Bean
    public User testUser_2(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1990);
        User user = new User(2, "www@mail.com", "Iva", "Kar", c.getTime());
        user.setPassword(passwordEncoder().encode("www"));
        return user;
    }
}
