package com.example.artistaapp.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder

@Entity(tableName = "owners")
public class Owner extends Person {
    @PrimaryKey(autoGenerate = true)
    private String ownerID;
    private String email;
    private String password;

    public Owner() {
    }
}
