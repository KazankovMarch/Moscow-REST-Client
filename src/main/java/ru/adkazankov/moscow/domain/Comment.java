package ru.adkazankov.moscow.domain;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Comment {

    private Long id;

    private House house;

    private int grade;

    private String body;


    @Override
    public String toString() {
        return "grade: " + grade +
                "\n " + body;
    }
}
