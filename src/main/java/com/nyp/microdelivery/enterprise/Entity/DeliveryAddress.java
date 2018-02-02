package com.nyp.microdelivery.enterprise.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deliveryAddress")
public class DeliveryAddress {
    @Id
    @Column(name = "addrId")
    private int addrId;

    @Column(name = "addr")
    private String addr;

    @Column(name = "postal")
    private String postalCode;

    @Column(name = "userId")
    private int userId;

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }


    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
