package learn.spring.configuration.beans;

import learn.spring.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
public class UsersConfig {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Bean
    public User user1() throws ParseException {
        return new User(1, "vasya@mail.com", "Valiliy", "Pupkin", dateFormat.parse("1989-01-18"));
    }

    @Bean
    public User user2() throws ParseException {
        return new User(2, "ivan@mail.com", "Ivan", "Petrov", dateFormat.parse("1972-07-08"));
    }

    @Bean
    public User user3() throws ParseException {
        return new User(3, "orest@mail.com", "Orest", "Lytui", dateFormat.parse("1992-02-07"));
    }

    @Bean
    public User user4() throws ParseException {
        return new User(4, "orest_next@mail.com", "Orest", "Melin", dateFormat.parse("1999-11-13"));
    }
}
