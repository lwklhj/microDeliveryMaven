package com.nyp.microdelivery.enterprise;

import com.nyp.microdelivery.enterprise.Entity.DeliveryAddress;
import com.nyp.microdelivery.enterprise.Entity.Order;
import com.nyp.microdelivery.enterprise.Entity.OrderDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class CheckOut implements Serializable{

    @Inject
    private StoreDetail storeDetail;

    private int orderId;

    private int userId=1;
    private DeliveryAddress address;
    private DeliveryAddress addrForSuccess;

    public DeliveryAddress getAddress() {
        System.out.println("get address: "+this.address.getReceiveName());
        return address;
    }

    public void setAddress(DeliveryAddress address) {

        this.address = address;
    }

    private List<DeliveryAddress> addresses;

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }
    public void load(){
        addresses=OrderDao.getDeliveryAddress(userId);
        address=addresses.get(0);

    }

    public String checkOut(){
        Order order=new Order();

        Date current=new Date();
        order.setOrderDate(current);
        order.setUserId(userId);
        order.setDeliveryAddressId(address.getAddrId());
        order.setStoreId(storeDetail.getId());
        orderId=OrderDao.saveOrderItem(storeDetail.getCart().getOrderItems(),order);
        storeDetail.getCart().reset();
        return "checkOutSuccessful";
    }

    public List<DeliveryAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<DeliveryAddress> addresses) {
        this.addresses = addresses;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public void changeAddress(DeliveryAddress address){
        DeliveryAddress addr=new DeliveryAddress();
        addr.setAddr(address.getAddr());
        addr.setAddrId(address.getAddrId());
        addr.setPostalCode(address.getPostalCode());
        addr.setReceiveName(address.getReceiveName());
        addr.setUserId(address.getUserId());
        addrForSuccess=addr;
        System.out.println("change address: "+addrForSuccess.getReceiveName());
    }

    public DeliveryAddress getAddrForSuccess() {
        return addrForSuccess;
    }

    public void setAddrForSuccess(DeliveryAddress addrForSuccess) {
        this.addrForSuccess = addrForSuccess;
    }
}
