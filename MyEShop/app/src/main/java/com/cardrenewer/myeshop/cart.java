package com.cardrenewer.myeshop;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart",
        foreignKeys = {
                @ForeignKey(entity = customers.class,
                        parentColumns = "customer_id",
                        childColumns = "cust_id",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = products.class,
                        parentColumns = "product_id",
                        childColumns = "prod_id",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)})
public class cart {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    private int id;

    @ColumnInfo(name = "cust_id")
    private int cust_id;

    @ColumnInfo(name = "prod_id")
    private int prod_id;

    @ColumnInfo(name = "quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
