package com.nyp.microdelivery.enterprise.Entity;


import javax.inject.Named;
import javax.persistence.*;

@Entity
@Named
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "itemId")
    private int itemId;

    @Column(name = "quantity")
    private int quantity;


    @Column(name = "price")
    private double price;

    @Column(name = "orderId")
    private int orderId;

    @Transient
    private String itemInfo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public String getItemInfo() {
        return quantity+" * "+StoreDao.getItem(itemId).getName()+"    $ "+getTotal();
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public double getTotal(){
        return quantity*price;
    }
}
