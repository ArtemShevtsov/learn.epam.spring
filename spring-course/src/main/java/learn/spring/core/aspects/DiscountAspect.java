package learn.spring.core.aspects;

import learn.spring.core.dao.aspects.DiscountAspectDAO;
import learn.spring.core.entity.Discount;
import learn.spring.core.entity.User;
import learn.spring.core.strategy.DiscountStrategy;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class DiscountAspect {
    @Autowired
    DiscountAspectDAO discountAspectDAO;

    private Map<Class<? extends DiscountStrategy>, Integer> counterDiscountGiven = new HashMap<>();
    private Map<User, Map<Class<? extends DiscountStrategy>, Integer>> counterDiscountGivenByUser = new HashMap<>();

    public Map<Class<? extends DiscountStrategy>, Integer> getCounterDiscountGiven() {
        return counterDiscountGiven;
    }

    public Map<User, Map<Class<? extends DiscountStrategy>, Integer>> getCounterDiscountGivenByUser() {
        return counterDiscountGivenByUser;
    }

    @Pointcut("execution(* learn.spring.core.services.DiscountService.getDiscount(learn.spring.core.entity.User, ..))")
    private void discountGiven(){}

    @AfterReturning(
            pointcut = "discountGiven() && args(user, ..)",
            returning = "discount"
    )
    public void afterDiscountGiven(User user, Discount discount){
        if(discount.getPercent() > 0){
            int countForUser = discountAspectDAO.getCounterByClassAndUser(discount.getDiscountClass(), user);
            int countAll = discountAspectDAO.getCounterByClass(discount.getDiscountClass());
            discountAspectDAO.setCounter(discount.getDiscountClass(), user, countForUser + 1);
            
//            Class clazz = discount.getDiscountClass();
//            counterDiscountGiven = increaseCounter(counterDiscountGiven, clazz);
//            if(!counterDiscountGivenByUser.containsKey(user)){
//                counterDiscountGivenByUser.put(user, new HashMap<>());
//            }
//            counterDiscountGivenByUser.put(user, increaseCounter(counterDiscountGivenByUser.get(user), clazz));
        }
    }

//    private Map<Class<? extends DiscountStrategy>, Integer> increaseCounter(Map map, Object key){
//        if(!map.containsKey(key)){
//            map.put(key, 0);
//        }
//        map.put(key, (Integer)map.get(key) + 1);
//        return map;
//    }
}
