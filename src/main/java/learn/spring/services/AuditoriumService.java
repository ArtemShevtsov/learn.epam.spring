package learn.spring.services;

import learn.spring.dao.AuditoriumDAO;
import learn.spring.entity.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoriumService {
    @Autowired
    AuditoriumDAO auditoriumDAO;

    public List<Auditorium> getAuditoriums(){
        return auditoriumDAO.getAuditoriums();
    }

    // FIXME: 0.5% never used
    public Integer getSeatsNumber(Auditorium auditorium){
        return auditoriumDAO.getSeatsNumber(auditorium);
    }

    // FIXME: 0.5% never used
    public String getVipSeats(Auditorium auditorium){
        return auditoriumDAO.getVipSeats(auditorium);
    }
}
