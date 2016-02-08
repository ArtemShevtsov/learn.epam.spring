package learn.spring.configuration;

import learn.spring.strategy.BirthdayStrategy;
import learn.spring.strategy.DiscountStrategy;
import learn.spring.strategy.TenthTicketStrategy;
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
