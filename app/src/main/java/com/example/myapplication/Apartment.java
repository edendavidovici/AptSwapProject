package com.example.myapplication;

import java.io.Serializable;

public class Apartment  implements Serializable {

    private int Image;
    private String Location;
    private String Contact;

    public Apartment(int image, String location, String contact) {
        Image = image;
        Location = location;
        Contact = contact;
    }

    public int getImage() {
        return Image;
    }

    public String getLocation() {
        return Location;
    }

    public String getContact() {
        return Contact;
    }
}