package learn.spring.services;

import learn.spring.entity.Event;
import learn.spring.entity.User;
import learn.spring.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class DiscountService {
    @Autowired
    ApplicationContext applicationContext;

    List<DiscountStrategy> discountStrategyList;

    @PostConstruct
    public void init(){
        Map<String, DiscountStrategy> beans = applicationContext.getBeansOfType(DiscountStrategy.class);
        discountStrategyList = new ArrayList<DiscountStrategy>();
        for(Map.Entry strategy: beans.entrySet()){
            discountStrategyList.add((DiscountStrategy)strategy.getValue());
        }
    }

    public int getDiscount(User user, Event event, Date date){
        int[] discounts = new int[discountStrategyList.size()];
        int i = 0;
        for(DiscountStrategy s: discountStrategyList){
            discounts[i] = s.getDiscountPercent(user, event, date);
        }
        Arrays.sort(discounts);
        return discounts[0];
    }

}
