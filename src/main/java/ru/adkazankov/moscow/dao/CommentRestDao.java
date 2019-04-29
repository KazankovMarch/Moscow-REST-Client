package ru.adkazankov.moscow.dao;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import ru.adkazankov.moscow.domain.Comment;
import ru.adkazankov.moscow.domain.House;
import ru.adkazankov.moscow.dto.CommentDto;

import java.util.List;
import java.util.Locale;

@Component
public class CommentRestDao extends RestDao<Comment> implements CommentDao{

    private String url = "http://localhost:8080/comment/";

    public CommentRestDao() {
        super(Comment.class);
    }

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    public void put(Comment o) {

    }

    @Override
    public Comment post(Comment o) {
        CommentDto commentDto = CommentDto.builder()
                .body(o.getBody())
                .grade(o.getGrade())
                .houseId(o.getHouse().getId())
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentLanguage(Locale.forLanguageTag("RU"));
        System.out.println(commentDto.toString());
        HttpEntity<String> entity = new HttpEntity<String>(commentDto.toString(), headers);
        o.setId(restTemplate.postForObject(url, entity, Long.class));
        return o;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Comment> getAll() {
        return null;
    }

}
