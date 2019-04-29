package ru.adkazankov.moscow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "area")

public class NoiseReport {

    private String source;
    private String results;
    private String address;
    private String checkdate;
    private int requestsCount;
    private String changes;

    private Long id;

    private Area area;


}
