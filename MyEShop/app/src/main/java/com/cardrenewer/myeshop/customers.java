package com.cardrenewer.myeshop;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customer")
public class customers {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customer_id")
    private int id;

    @ColumnInfo(name = "customer_name")
    private String name;

    @ColumnInfo(name = "customer_surname")
    private String surname;

    @ColumnInfo(name = "customer_city")
    private String city;

    @ColumnInfo(name = "customer_phone_number")
    private String phone_number;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " " + surname;
    }
}
