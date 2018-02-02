package com.nyp.microdelivery.enterprise;


import com.nyp.microdelivery.enterprise.Entity.Order;
import com.nyp.microdelivery.enterprise.Entity.OrderDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class CheckOut implements Serializable{

    @Inject
    private StoreDetail storeDetail;

    private int orderId;
    private Order order;

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }

    public String checkOut(){
        Order order=new Order();

        Date current=new Date();
        order.setOrderDate(current);
        order.setUserId(1);
        order.setDeliveryAddressId(1);
        order.setStoreId(storeDetail.getId());
        orderId= OrderDao.saveOrderItem(storeDetail.getCart().getOrderItems(),order);
        storeDetail.getCart().reset();
        return "checkOutSuccess";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void loadOrderInfo(){
        order=OrderDao.getOrderInformation(orderId);

    }
}
