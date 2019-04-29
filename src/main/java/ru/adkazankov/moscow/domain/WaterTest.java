package ru.adkazankov.moscow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
public class WaterTest {

    private String name;

    private Area area;

    private String resVal;

    private Long id;

    public WaterTest(String name, Area area, String resVal) {
        this.name = name;
        this.area = area;
        this.resVal = resVal;
    }


    @Override
    public String toString() {
        return name +": " + resVal;
    }
}
