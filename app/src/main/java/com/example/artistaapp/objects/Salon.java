package com.example.artistaapp.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder

@Entity(tableName = "salons",
        foreignKeys = @ForeignKey(entity = Owner.class,
                parentColumns = "ownerID",
                childColumns = "ownerID",
                onDelete = ForeignKey.CASCADE))
public class Salon {
    @PrimaryKey(autoGenerate = true)
    private int salonID;
    private String name;
    private String imgURL;
    private String address;
    private String phone;
    private String email;
    private String year;
    private int ownerID;

    public Salon() {}
}
