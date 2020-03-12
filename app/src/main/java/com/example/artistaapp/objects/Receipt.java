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

@Entity(tableName = "receipts",
        foreignKeys = @ForeignKey(entity = Appointment.class,
                parentColumns = "appointmentID",
                childColumns = "appointmentID",
                onDelete = ForeignKey.CASCADE))
public class Receipt {
    @PrimaryKey(autoGenerate = true)
    private int receiptID;
    private double price;
    private double adjust;
    private double tax;
    private double total;
    private int appointmentID;

    public Receipt() {
    }
}
