package com.nobutnk.jsr310util.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nobutnk.jsr310util.json.deser.JsonLocalDateTimeDeserializer;
import com.nobutnk.jsr310util.json.ser.JsonLocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private String name;
    private Integer price;
    
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;
    
    private transient String test;

}
