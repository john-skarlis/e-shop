package com.cardrenewer.myeshop;

public class cart_item_result {

    int cart_cust_id;
    int cart_prod_id;
    int cart_quantity;

    String product_name;
    Double product_price;
    String customer_name;
    String customer_surname;

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public int getCart_cust_id() {
        return cart_cust_id;
    }

    public void setCart_cust_id(int cart_cust_id) {
        this.cart_cust_id = cart_cust_id;
    }

    public int getCart_prod_id() {
        return cart_prod_id;
    }

    public void setCart_prod_id(int cart_prod_id) {
        this.cart_prod_id = cart_prod_id;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_surname() {
        return customer_surname;
    }

    public void setCustomer_surname(String customer_surname) {
        this.customer_surname = customer_surname;
    }
}
