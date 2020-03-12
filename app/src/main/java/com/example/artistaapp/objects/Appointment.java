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

//@Entity(tableName = "appointments",
//        foreignKeys = {
//                @ForeignKey(entity = User.class,
//                        parentColumns = "userID",
//                        childColumns = "userID",
//                        onDelete = ForeignKey.CASCADE),
//
//                @ForeignKey(entity = Artist.class,
//                        parentColumns = "artistID",
//                        childColumns = "artistID",
//                        onDelete = ForeignKey.CASCADE),
//
//                @ForeignKey(entity = Service.class,
//                        parentColumns = "serviceID",
//                        childColumns = "serviceID",
//                        onDelete = ForeignKey.CASCADE),
//        })

public class Appointment {
//    @PrimaryKey(autoGenerate = true)
    private String imgUrl;
    private String title;
    private String salon;
    private String location;
    private String date;
    private String time;
    private String artist;
    private String level;
    private double price;
    private double adjust;
    private double sum;
    private double tax;
    private double total;

//    private int appointmentID;
//    private String userID;
//    private String artistID;
//    private String serviceID;

    public Appointment() {
    }
}
