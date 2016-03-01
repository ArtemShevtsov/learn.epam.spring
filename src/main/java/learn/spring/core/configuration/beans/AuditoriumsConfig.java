package learn.spring.core.configuration.beans;

import learn.spring.core.entity.Auditorium;
import static learn.spring.utils.StringToArrayUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:properties/auditorium.properties")
public class AuditoriumsConfig {
    @Autowired
    Environment env;

    @Bean
    public Auditorium auditorium1(){
        return new Auditorium(
                1,
                env.getProperty("auditorium1.name"),
                Integer.valueOf(env.getProperty("auditorium1.numberOfSeats")),
                converVipSeats(env.getProperty("auditorium1.vipSeats")));
    }

    @Bean
    public Auditorium auditorium2(){
        return new Auditorium(
                2,
                env.getProperty("auditorium2.name"),
                Integer.valueOf(env.getProperty("auditorium2.numberOfSeats")),
                converVipSeats(env.getProperty("auditorium2.vipSeats")));
    }

    @Bean
    public Auditorium auditorium3(){
        return new Auditorium(
                3,
                env.getProperty("auditorium3.name"),
                Integer.valueOf(env.getProperty("auditorium3.numberOfSeats")),
                converVipSeats(env.getProperty("auditorium3.vipSeats")));
    }
}
