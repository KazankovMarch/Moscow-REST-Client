package ru.adkazankov.moscow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
public class House {

    private String address;

    private String streetPrefix;

    private String streetName;

    private String building;

    private int year;

    private Long id;

    private List<Comment> comments;

    private Area area;


    public House(String address, String streetPrefix, String streetName, String building, Area area, int year) {
        this.address = address;
        this.streetPrefix = streetPrefix;
        this.streetName = streetName;
        this.building = building;
        this.area = area;
        this.year = year;
    }

    public House() {

    }

    @Override
    public String toString() {
        return area.getName() + ", " + address;
    }
}
