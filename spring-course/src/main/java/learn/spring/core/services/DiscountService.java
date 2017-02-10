package learn.spring.core.services;

import learn.spring.core.entity.Discount;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.User;
import learn.spring.core.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DiscountService {

    @Autowired
    List<DiscountStrategy> discountStrategyList;

    public Discount getDiscount(User user, Event event, Date date){
        List<Discount> discountList = new ArrayList<>();
        for(DiscountStrategy s: discountStrategyList){
            discountList.add(new Discount(s.getDiscountPercent(user, event, date), s.getClass()));
        }
        discountList.sort((Discount o1, Discount o2) -> o1.getPercent() - o2.getPercent());
        return discountList.get(discountList.size() - 1);
    }

}
