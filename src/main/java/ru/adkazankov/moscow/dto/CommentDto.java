package ru.adkazankov.moscow.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long houseId;
    private String body;
    private int grade;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"houseId\":\"" + houseId +
                "\", \"body\":\"" + body +
                "\", \"grade\":\"" + grade +
                "\"}";
    }
}
