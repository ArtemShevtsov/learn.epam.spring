package learn.spring.core.configuration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Autowired
    DataSource propsH2DataSource;
    @Autowired
    DataSource springH2DataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(propsH2DataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplateEmbedded(){
        return new JdbcTemplate(springH2DataSource);
    }
}
