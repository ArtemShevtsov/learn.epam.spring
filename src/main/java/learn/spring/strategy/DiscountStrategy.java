package learn.spring.strategy;

import learn.spring.entity.Event;
import learn.spring.entity.User;

import java.text.ParseException;
import java.util.Date;

public interface DiscountStrategy {
    int getDiscountPercent(User user, Event event, Date date);
}
