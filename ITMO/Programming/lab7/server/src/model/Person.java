package model;

import enums.Color;
import enums.Country;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = 45416695L;
    private String name;
    private java.sql.Date birthday; //Поле не может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    public Person() {}

    public Person(String name, java.sql.Date birthday, Color eyeColor, Color hairColor, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public int compareTo(Person o) {
        if (o == null) {
            return 1;
        }
        if (this.name != null) {
            return this.name.compareTo(o.name);
        }
        return 0;
    }
}

//package model;
//
//import enums.Color;
//import enums.Country;
//
//import java.io.Serializable;
//
//public class Person implements Serializable {
//
//    private String name;
//
//    private java.util.Date birthday; //Поле не может быть null
//
//    private Color eyeColor; //Поле может быть null
//
//    private Color hairColor; //Поле не может быть null
//
//    private Country nationality; //Поле может быть null
//
//    public Person() {}
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public java.util.Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(java.util.Date birthday) {
//        this.birthday = birthday;
//    }
//
//    public Color getEyeColor() {
//        return eyeColor;
//    }
//
//    public void setEyeColor(Color eyeColor) {
//        this.eyeColor = eyeColor;
//    }
//
//    public Color getHairColor() {
//        return hairColor;
//    }
//
//    public void setHairColor(Color hairColor) {
//        this.hairColor = hairColor;
//    }
//
//    public Country getNationality() {
//        return nationality;
//    }
//
//    public void setNationality(Country nationality) {
//        this.nationality = nationality;
//    }
//
//    public int compareTo(Person o) {
//        if (o == null) {
//            return 1;
//        }
//        if (this.name != null) {
//            return this.name.compareTo(o.name);
//        }
//        return 0;
//    }
//}