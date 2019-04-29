package ru.adkazankov.moscow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Area {

    private String name;

    private Long id;

    private List<WaterTest> tests;

    private List<NoiseReport> reports;

}
