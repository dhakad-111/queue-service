package com.nimesa.tech.enums;

import com.nimesa.tech.exceptions.InvalidArgumentException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Topic {
    TOPIC_CHARACTER("TOPIC_CHARACTER","TOPIC-CHARACTER"),
    TOPIC_NUMBER("TOPIC_NUMBER","TOPIC-NUMBER");
    private String code;
    private String name;


    public static Topic findByName(String name){
        return Arrays.stream(values())
                .filter(topics -> topics.getName().equalsIgnoreCase(name) || topics.getCode().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(()-> new InvalidArgumentException("Invalid topic name : " + name));
    }
}
