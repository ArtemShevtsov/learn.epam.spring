package learn.spring.aspects;

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
    Map<Class<? extends DiscountStrategy>, Integer> counterDiscountGiven = new HashMap<Class<? extends DiscountStrategy>, Integer>();

    @Pointcut("execution(* learn.spring.services.DiscountStrategy.getDiscountPercent(learn.spring.entity.User, .., ..))")
    private void discountGiven(){}

    @AfterReturning(
            pointcut = "discountGiven() && args(user)",
            returning = "percent"
    )
    public void afterDiscountGiven(User user, int percent, JoinPoint jp){
        if(percent > 0){
            Class clazz = jp.getTarget().getClass();
            if(!counterDiscountGiven.containsKey(clazz)){
                counterDiscountGiven.put(clazz, 0);
            }
            counterDiscountGiven.put(clazz, counterDiscountGiven.get(clazz) + 1);
        }
    }
}
