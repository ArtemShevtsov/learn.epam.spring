package learn.spring.aspects;

import learn.spring.entity.Discount;
import learn.spring.entity.User;
import learn.spring.strategy.DiscountStrategy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DiscountAspect {
    private Map<Class<? extends DiscountStrategy>, Integer> counterDiscountGiven = new HashMap<>();
    private Map<User, Map<Class<? extends DiscountStrategy>, Integer>> counterDiscountGivenByUser = new HashMap<>();

    public Map<Class<? extends DiscountStrategy>, Integer> getCounterDiscountGiven() {
        return counterDiscountGiven;
    }

    public Map<User, Map<Class<? extends DiscountStrategy>, Integer>> getCounterDiscountGivenByUser() {
        return counterDiscountGivenByUser;
    }

    @Pointcut("execution(* learn.spring.services.DiscountService.getDiscount(learn.spring.entity.User, ..))")
    private void discountGiven(){}

    @AfterReturning(
            pointcut = "discountGiven() && args(user, ..)",
            returning = "discount"
    )
    public void afterDiscountGiven(User user, Discount discount){
        if(discount.getPercent() > 0){
            Class clazz = discount.getDiscountClass();
            counterDiscountGiven = increaseCounter(counterDiscountGiven, clazz);
            if(!counterDiscountGivenByUser.containsKey(user)){
                counterDiscountGivenByUser.put(user, new HashMap<>());
            }
            counterDiscountGivenByUser.put(user, increaseCounter(counterDiscountGivenByUser.get(user), clazz));
        }
    }

    private Map<Class<? extends DiscountStrategy>, Integer> increaseCounter(Map map, Object key){
        if(!map.containsKey(key)){
            map.put(key, 0);
        }
        map.put(key, (Integer)map.get(key) + 1);
        return map;
    }
}
