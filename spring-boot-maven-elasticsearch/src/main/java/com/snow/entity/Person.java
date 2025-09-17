package com.snow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;

@TypeAlias("human")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    String id;
    String firstname;
    String lastname;

}
