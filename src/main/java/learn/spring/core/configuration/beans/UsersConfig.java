package learn.spring.core.configuration.beans;

import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Configuration
public class UsersConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Bean
    public User user1() throws ParseException {
        User user = new User(1, "vasya@mail.com", "Valiliy", "Pupkin", dateFormat.parse("1989-01-18"));
        user.setPassword(passwordEncoder.encode("vasya"));
        return user;
    }

    @Bean
    public User user2() throws ParseException {
        User user = new User(2, "ivan@mail.com", "Ivan", "Petrov", dateFormat.parse("1972-07-08"));
        user.setPassword(passwordEncoder.encode("ivan"));
        return user;
    }

    @Bean
    public User user3() throws ParseException {
        User user = new User(3, "orest@mail.com", "Orest", "Lytui", dateFormat.parse("1992-02-07"));
        user.setPassword(passwordEncoder.encode("orest"));
        return user;
    }

    @Bean
    public User user4() throws ParseException {
        User user = new User(4, "orest_next@mail.com", "Orest", "Melin", dateFormat.parse("1999-11-13"));
        user.setPassword(passwordEncoder.encode("orest_next"));
        return user;
    }
}
