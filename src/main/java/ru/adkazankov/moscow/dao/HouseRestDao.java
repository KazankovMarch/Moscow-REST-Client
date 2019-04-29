package ru.adkazankov.moscow.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.dto.HouseLikeDto;

import java.util.*;

@Component
public class HouseRestDao extends RestDao<House> implements  HouseDao{

    public HouseRestDao() {
        super(House.class);
    }

    private String url = "http://localhost:8080/house/";

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<House> get10LikeThis(Map<String, String> uriVariables) {
        uriVariables.putIfAbsent("area","");
        uriVariables.putIfAbsent("street","");
        uriVariables.putIfAbsent("building","");
        return  Arrays.asList(Objects.requireNonNull(
                restTemplate.getForObject(url + "like?area={area}&street={street}&building={building}", House[].class, uriVariables)
        ));
    }

    @Override
    public void put(House o) {
        restTemplate.put(getUrl(), o);
    }

    @Override
    public House post(House o) {
        return restTemplate.postForObject(getUrl(), o, type);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(getUrl() + id);
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    public List<House> getAll() {
        return Arrays.asList(restTemplate.getForObject(url, House[].class));
    }
}
