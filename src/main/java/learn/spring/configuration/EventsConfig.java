package learn.spring.configuration;


import learn.spring.entity.Event;
import learn.spring.entity.EventRating;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsConfig {
    @Bean
    public Event theMartian(){
        return new Event("The Martian", 120., EventRating.HIGHT, 144);
    }

    @Bean
    public Event interstellar(){
        return new Event("Interstellar", 100.5, EventRating.HIGHT, 169);
    }

    @Bean
    public Event djangoUnchained(){
        return new Event("Django Unchained", 80., EventRating.MIDDLE, 165);
    }

    @Bean
    Event badFilm(){
        return new Event("Some Bad Film", 50.25, EventRating.LOW, 93);
    }
}