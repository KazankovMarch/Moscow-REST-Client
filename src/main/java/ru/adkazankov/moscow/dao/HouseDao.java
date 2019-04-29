package ru.adkazankov.moscow.dao;

import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.dto.HouseLikeDto;

import java.util.List;
import java.util.Map;

public interface HouseDao extends Dao<House> {

    List<House> get10LikeThis(Map<String,String> map);

}
