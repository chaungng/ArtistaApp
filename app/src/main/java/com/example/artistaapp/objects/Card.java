package com.example.artistaapp.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Card {
    private int id;
    private String imgUrl;
    private String title;
    private double price;
    private String salon;
    private String location;
    private String description;

    public Card() {
    }
}