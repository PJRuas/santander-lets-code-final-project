package com.letscode.santander.users.services.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class HashMapConverter implements AttributeConverter<Map<Integer, Integer>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<Integer, Integer> entityMap){
        String entityJson = null;
        try {
            entityJson = objectMapper.writeValueAsString(entityMap);
        } catch (final JsonProcessingException e) {
            System.out.println(e);
        }
        return entityJson;
    }

    @Override
    public Map<Integer, Integer> convertToEntityAttribute(String entityJson) {
        Map<Integer, Integer> entityMap = new HashMap<>();
        try {
            Map<String, Integer> stringIntegerMap = objectMapper.readValue(entityJson, Map.class);
            for (String key : stringIntegerMap.keySet()) {
                entityMap.put(Integer.parseInt(key), stringIntegerMap.get(key));
            }
        } catch (final IOException e) {
            System.out.println(e);
        }
        return entityMap;
    }


}