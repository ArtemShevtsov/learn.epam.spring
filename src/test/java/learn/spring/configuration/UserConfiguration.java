package learn.spring.configuration;

import learn.spring.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class UserConfiguration {
    @Bean
    public User testUser(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1988);
        return new User(1,"qqq@mail.com", "Vas", "Pup", c.getTime());
    }
}
