package com.cardrenewer.myeshop;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class products {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private int id;

    @ColumnInfo(name = "product_name")
    private String name;

    @ColumnInfo(name = "product_description")
    private String description;

    @ColumnInfo(name = "product_quantity")
    private int quantity;

    @ColumnInfo(name = "product_cat_name")
    private String category_name;

    @ColumnInfo(name = "product_price")
    private double price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
