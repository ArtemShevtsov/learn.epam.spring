package learn.spring.configuration;

import learn.spring.entity.Auditorium;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public Auditorium auditorium1(){
        Integer[] vipSeats = {8,9,10};
        return new Auditorium("Forum", 20, vipSeats);
    }

    @Bean
    public Auditorium auditorium2(){
        Integer[] vipSeats = {3,4,5,6,7,8};
        return new Auditorium("ElitPlaza", 10, vipSeats);
    }

    @Bean
    public Auditorium auditorium3(){
        Integer[] vipSeats = {5};
        return new Auditorium("Regular", 9, vipSeats);
    }

}
