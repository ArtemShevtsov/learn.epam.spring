package learn.spring.configuration.beans;


import learn.spring.entity.Event;
import learn.spring.entity.EventRating;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventsConfig {
    @Bean
    public Event theMartian(){
        return new Event(1, "The Martian", 120., EventRating.HIGHT, 144);
    }

    @Bean
    public Event interstellar(){
        return new Event(2, "Interstellar", 100.5, EventRating.HIGHT, 169);
    }

    @Bean
    public Event djangoUnchained(){
        return new Event(3, "Django Unchained", 80., EventRating.MIDDLE, 165);
    }

    @Bean
    Event badFilm(){
        return new Event(4, "Some Bad Film", 50.25, EventRating.LOW, 93);
    }
}
