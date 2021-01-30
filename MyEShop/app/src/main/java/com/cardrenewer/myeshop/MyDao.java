package com.cardrenewer.myeshop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void add_customer(customers customer);

    @Insert
    void add_product(products product);

    @Insert
    void add_to_cart(cart cart);

    @Insert
    void add_sales(sales sale);

    @Delete
    void delete_customer(customers customer);

    @Delete
    void delete_product(products product);

    @Delete
    void delete_sale(sales sale);

    @Update
    void update_customer(customers customer);

    @Update
    void update_product(products product);

    @Update
    void update_sale(sales sale);

    @Query("UPDATE product SET product_quantity=:quantity WHERE product_id= :proid")
    void update_quantity(int quantity,int proid);

    @Query("SELECT * FROM CUSTOMER")
    public List<customers> get_customers();

    @Query("SELECT * FROM product")
    public List<products> get_products();

    @Query("SELECT * FROM sale")
    List<sales> get_sales();

    @Query("SELECT * " +
            "FROM customer c WHERE  c.customer_id= :cid")
    public List<customers> get_update_customer(int cid);

    @Query("SELECT * " +
            "FROM customer c WHERE  c.customer_id= :cid")
    public customers get_customer(int cid);

    @Query("SELECT * " +
            "FROM SALE s WHERE  id= :sid")
    public sales get_update_sale(int sid);

    @Query("SELECT * " +
            "FROM product p WHERE  p.product_id= :prodid")
    public List<products> get_update_product(int prodid);

    @Query("SELECT * " +
            "FROM product p WHERE  p.product_id= :prodid")
    public products get_product(int prodid);

    @Query("SELECT * " +
            "FROM product p WHERE  p.product_cat_name= :category")
    public List<products> get_category_products(String category);

    @Query("SELECT c.cust_id as cart_cust_id ,c.prod_id as cart_prod_id,c.quantity as cart_quantity,p.product_name as product_name," +
            "p.product_price as product_price,s.customer_name as customer_name,s.customer_surname as customer_surname " +
            "FROM CART as c INNER JOIN product as p on c.prod_id=p.product_id INNER JOIN customer as s on c.cust_id = s.customer_id ORDER BY c.cust_id")
    List<cart_item_result> get_cart();

    @Query("DELETE FROM cart")
    void clear_cart();

    @Query("SELECT count(*) FROM sale ")
    int get_total_sales();

    @Query("SELECT p.product_name as product_name,SUM(s.squantity) as product_sales FROM SALE s INNER JOIN PRODUCT p ON p.product_id=s.spid GROUP BY p.product_id")
    List<query3> get_product_sale();

    @Query("SELECT c.customer_name as name,c.customer_surname as surname,c.customer_city as city,COUNT(*) as orders FROM SALE s INNER JOIN CUSTOMER c ON s.scid=c.customer_id GROUP BY c.customer_id")
    List<query4> get_customers_orders();

    @Query("SELECT p.product_name as name,p.product_cat_name as cat_name,p.product_price*SUM(s.squantity) as price,SUM(s.squantity) as quantity FROM sale s INNER JOIN product p ON s.spid=p.product_id GROUP BY p.product_id")
    List<query5> get_products_info();


}
