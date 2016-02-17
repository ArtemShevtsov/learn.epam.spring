package learn.spring.configuration.beans;

import learn.spring.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
@PropertySource("properties/auditorium.properties")
public class AuditoriumsConfig {
    @Autowired
    Environment env;

    @Bean
    public Auditorium auditorium1(){
        return new Auditorium(
                env.getProperty("auditorium1.name"),
                Integer.valueOf(env.getProperty("auditorium1.numberOfSeats")),
                this.converVipSeats(env.getProperty("auditorium1.vipSeats")));
    }

    @Bean
    public Auditorium auditorium2(){
        return new Auditorium(
                env.getProperty("auditorium2.name"),
                Integer.valueOf(env.getProperty("auditorium2.numberOfSeats")),
                this.converVipSeats(env.getProperty("auditorium2.vipSeats")));
    }

    @Bean
    public Auditorium auditorium3(){
        return new Auditorium(
                env.getProperty("auditorium3.name"),
                Integer.valueOf(env.getProperty("auditorium3.numberOfSeats")),
                this.converVipSeats(env.getProperty("auditorium3.vipSeats")));
    }

    private Integer[] converVipSeats(String seats){
        String[] vipSeats = seats.split(",");
        Integer[] vipSeatsInt = new Integer[vipSeats.length];
        for(int i = 0; i< vipSeats.length; i++){
            vipSeatsInt[i] = Integer.valueOf(vipSeats[i]);
        }
        return vipSeatsInt;
    }

}
