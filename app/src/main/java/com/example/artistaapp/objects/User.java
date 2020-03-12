package com.example.artistaapp.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder

@Entity(tableName = "users")
public class User extends Person {
    private String userID;
    private String imgURL;
    private String email;
    private String password;

    public User() {
    }
}
