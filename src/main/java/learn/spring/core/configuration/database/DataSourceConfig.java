package learn.spring.core.configuration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:properties/database-h2.properties")
public class DataSourceConfig {
    @Autowired
    Environment env;

    @Bean
    public DriverManagerDataSource propsH2DataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("jdbc.driver.class.name"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));

        return ds;
    }

    @Bean
    public DriverManagerDataSource mySqlDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        return ds;
    }

    @Bean
    public DataSource springH2DataSource(){
        EmbeddedDatabaseBuilder embeddedDBBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDB = embeddedDBBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sql/create-tables-h2.sql")
                .build();
        return embeddedDB;
    }


}
