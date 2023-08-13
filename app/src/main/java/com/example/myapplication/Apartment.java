package com.example.myapplication;

import java.io.Serializable;

public class Apartment  implements Serializable {

    private int Image;
    private String Location;
    private String Owner;


    public Apartment(int image, String location, String owner) {
        Image = image;
        Location = location;
        Owner = owner;

    }

    public int getImage() {
        return Image;
    }

    public String getLocation() {
        return Location;
    }
    public String getOwner() {
        return Owner;
    }

}