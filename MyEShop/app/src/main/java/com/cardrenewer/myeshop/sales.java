package com.cardrenewer.myeshop;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "sale",
        foreignKeys = {
                @ForeignKey(entity = customers.class,
                        parentColumns = "customer_id",
                        childColumns = "scid",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = products.class,
                        parentColumns = "product_id",
                        childColumns = "spid",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)})
public class sales {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "scid")
    @NonNull
    private int cid;

    @ColumnInfo(name = "spid")
    @NonNull
    private int pid;

    @ColumnInfo(name = "squantity")
    private int sale_quantity;

    @ColumnInfo(name = "sdate")
    @NonNull
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSale_quantity() {
        return sale_quantity;
    }

    public void setSale_quantity(int sale_quantity) {
        this.sale_quantity = sale_quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
