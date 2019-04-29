package ru.adkazankov.moscow.dao;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public abstract class RestDao<T> implements Dao<T> {
    
    protected RestTemplate restTemplate = new RestTemplate();
    protected Class<T> type;

    public RestDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get(Long id) {
        return  restTemplate.getForObject(getUrl()+id, type);
    }



    protected abstract String getUrl();

}
