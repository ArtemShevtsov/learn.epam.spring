package learn.spring.dao;

import learn.spring.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class AuditoriumDAO {
    @Autowired
    public List<Auditorium> auditoriums;

    public List<Auditorium> getAuditoriums(){
        return auditoriums;
    }

    public Integer getSeatsNumber(Auditorium auditorium){
        return auditorium.getNumberOfSeats();
    }

    public String getVipSeats(Auditorium auditorium){
        return Arrays.deepToString(auditorium.getVipSeats());
    }
}
