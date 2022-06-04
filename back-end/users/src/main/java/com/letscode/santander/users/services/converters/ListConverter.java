package com.letscode.santander.users.services.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class ListConverter implements AttributeConverter<List<Integer>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Integer> entityMap){
        return entityMap != null ? entityMap.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR)) : "";
    }

    @Override
    public List<Integer> convertToEntityAttribute(String entityJson) {
        return (entityJson != null && !entityJson.equals(""))? Arrays.stream(entityJson.split(SPLIT_CHAR)).map(Integer::parseInt).collect(Collectors.toList()) : Collections.emptyList();
    }
}
