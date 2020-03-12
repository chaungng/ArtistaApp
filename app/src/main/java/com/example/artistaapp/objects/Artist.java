package com.example.artistaapp.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder

//@Entity(tableName = "artists",
//        foreignKeys = @ForeignKey(entity = Salon.class,
//                parentColumns = "salonID",
//                childColumns = "salonID",
//                onDelete = ForeignKey.CASCADE))

public class Artist extends Person {
//    @PrimaryKey(autoGenerate = true)
    private int artistID;
    private String imgUrl;
    private String level;
    private int yearsOfExperience;
    private double adjustPercentage;
//    private int salonID;

    public Artist() {
    }
}
