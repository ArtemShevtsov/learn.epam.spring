package learn.spring.core.configuration.beans;

import learn.spring.core.strategy.BirthdayStrategy;
import learn.spring.core.strategy.DiscountStrategy;
import learn.spring.core.strategy.TenthTicketStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {
    @Bean
    public DiscountStrategy birthdayStrategy(){
        return new BirthdayStrategy();
    }

    @Bean
    public DiscountStrategy tenthStrategy(){
        return new TenthTicketStrategy();
    }
}
