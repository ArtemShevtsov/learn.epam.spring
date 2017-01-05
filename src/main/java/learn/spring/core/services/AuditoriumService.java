package learn.spring.core.services;

import learn.spring.core.dao.AuditoriumDAO;
import learn.spring.core.entity.Auditorium;
import learn.spring.core.entity.Event;
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

    public Auditorium getById(Integer id){
        return  auditoriumDAO.getById(id);
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
