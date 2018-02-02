package com.nyp.microdelivery.enterprise.Entity;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Named
@Entity
@Table(name="storeItem")
public class Item {
    @Id
    @Column(name="itemId")
    private int id;
    @Column(name = "itemName")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "itemType")
    private String type;
    @Column(name = "pic")
    //private String picture;
    private byte[] picture;
    @Column(name = "storeId")
    private int storeId;




    public Item(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicture() {

        return picture;

    }

    public void setPicture(byte[] picture) {


        this.picture = picture;

    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
