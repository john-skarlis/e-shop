package com.cardrenewer.myeshop;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {customers.class, products.class, sales.class, cart.class}, version = 1, exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
