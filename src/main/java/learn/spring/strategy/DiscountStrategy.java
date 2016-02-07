package learn.spring.strategy;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;

import java.text.ParseException;

public interface DiscountStrategy {
    int getDiscountPercent(Ticket ticket, User user) throws ParseException;
}
