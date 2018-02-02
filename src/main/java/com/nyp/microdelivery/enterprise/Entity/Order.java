package com.nyp.microdelivery.enterprise.Entity;

import javax.inject.Named;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Named
@Entity
@Table(name = "orders")
public class Order {
    @Transient
    private List<OrderItem> orderItems;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;

    @Column(name = "orderDateTime", columnDefinition="DATETIME")
    private Date orderDate;

    @Column(name = "userId")
    private int userId;

    @Column(name = "addrId")
    private int deliveryAddressId;

    @Column(name="complete")
    private int isComplete;

    @Column(name = "storeId")
    private int storeId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(int deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public DeliveryAddress getAddress(){
        return OrderDao.getAddress(deliveryAddressId);
    }
    public String getOrderItemsString(){
        String s="";
        for(OrderItem orderItem:orderItems) s+=orderItem.toString()+"\n";
        return s;
    }
    public String getTotalInString(){
        double total=0;
        for(OrderItem orderItem:orderItems) total+=orderItem.getTotal();
        return total+"";
    }

}
