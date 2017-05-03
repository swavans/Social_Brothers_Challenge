package com.developer.sascha.social_brothers_challenge.data;

import android.graphics.Bitmap;

/**
 * Class To Store Dummy Data so that it can be used later on
 */
public class Person {
    private String name, surname, phone;
    private Bitmap image;

    public Person(String name, String surname, String phone, Bitmap image) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
