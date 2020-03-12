package com.example.artistaapp.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class Person {
    private String firstName;
    private String lastName;

    public Person(){}
}
