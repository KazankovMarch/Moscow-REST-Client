package ru.adkazankov.moscow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class HouseLikeDto {

    private String area = "";
    private String street = "";
    private String building = "";

}
