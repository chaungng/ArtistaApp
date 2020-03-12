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

@Entity(tableName = "services",
        foreignKeys = @ForeignKey(entity = Salon.class,
        parentColumns = "salonID",
        childColumns = "salonID",
        onDelete = ForeignKey.CASCADE))

public class Service {
    @PrimaryKey
    private int serviceID;
    private String name;
    private String imgURL;
    private String description;
    private double price;
    private String time;
    private int salonID;

    public Service() {
    }
}
