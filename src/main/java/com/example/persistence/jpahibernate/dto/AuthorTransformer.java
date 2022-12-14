package com.example.persistence.jpahibernate.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AuthorTransformer {

    public List<AuthorMapperDto> transform(List<Object[]> rs) {
        final Map<Long, AuthorMapperDto> authorDtoMap = new HashMap<>();

        for (Object[] o : rs) {
            Long authorId = ((Number) o[0]).longValue();
            AuthorMapperDto authorMapperDto = authorDtoMap.get(authorId);

            if (authorMapperDto == null) {
                authorMapperDto = new AuthorMapperDto();
                authorMapperDto.setAuthorId(((Number) o[0]).longValue());
                authorMapperDto.setName((String) o[1]);
                authorMapperDto.setGenre((String) o[2]);
            }

            BookMapperDto bookMapperDto = new BookMapperDto();
            bookMapperDto.setBookId(((Number) o[3]).longValue());
            bookMapperDto.setTitle((String) o[4]);
            authorMapperDto.getBooks().add(bookMapperDto);
            authorDtoMap.putIfAbsent(authorMapperDto.getAuthorId(), authorMapperDto);
        }
        return new ArrayList<>(authorDtoMap.values());
    }
}
