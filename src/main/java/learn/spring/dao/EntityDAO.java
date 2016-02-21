package learn.spring.dao;


import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EntityDAO <T> {
    RowMapper<? extends T> entityRowMapper();

    /**
     * Tried to implement it with Abstract class
     * for inject beans from configuration to list<T>
     *     to avoid List object in each DAO
     * but there I need default constructor
     *
     * Problem: How to get Class Object of T?
     */
/*
    private final Class<T> typeOfClass;
    @Autowired
    ApplicationContext appContext;
    List<T> entityList = new ArrayList<>();

    EntityDAO(Class<T> typeOfClass){
        this.typeOfClass = typeOfClass;
    }

    @PostConstruct
    public void init(){
        Map<String, T> map = appContext.getBeansOfType(typeOfClass);
        for(Map.Entry<String, T> entity: map.entrySet()){
            entityList.add(entity.getValue());
        }
    }
*/
}
