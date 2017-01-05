package learn.spring.core.strategy;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.User;

import java.util.Date;

public interface DiscountStrategy {
    int getDiscountPercent(User user, Event event, Date date);
}
