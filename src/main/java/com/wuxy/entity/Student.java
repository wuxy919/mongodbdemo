package com.wuxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author: wuxy
 * @create: 2019-04-28 18:44
 **/
@Data
@Document
public class Student {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private int age;
    @Field(value = "a_time")
    private Date addTime;
}
