package com.nyp.microdelivery.enterprise.Entity;


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Named
@Entity
@Table(name="store")
@SessionScoped
public class Store implements Serializable{
    @Id
    @Column(name = "storeId")
    private int id;
    @Column(name = "storeName")
    private String companyName;
    @Column(name = "email")
    private String email;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "addr")
    private String addr;
    @Column(name = "postal")
    private String postalCode;
    @Column(name = "storeType")
    private String storeType;
    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name="pic")
    private byte[] picture;


    public Store(){}


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return companyName+"\t"+passwd;
    }
}
