package com.hanspal.cfservice1.broker.model;

import javax.persistence.AttributeConverter;

public class ObjectToStringConverter implements AttributeConverter<Object, String> {
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        return attribute.toString();
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        return dbData;
    }
}