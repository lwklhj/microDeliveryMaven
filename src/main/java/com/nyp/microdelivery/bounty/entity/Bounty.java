/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.bounty.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */

@Entity
@Table(name="Posting")
public class Bounty implements Serializable {
    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = byte[].class)
    @Column(columnDefinition = "LONGBLOB")
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "Post_id", referencedColumnName = "id"))
    private List<byte[]> images = new ArrayList<>();
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "price")
    private BigDecimal price;

    @Column
    private String storeName;

    @Column
    private String storeLocation;

    @Column
    private String deliveryAddr;

    @Column(length = 6)
    private int deliveryPostalCd;

    @Column(name = "courier_id")
    private int courierID;

    @Column(name = "op_id")
    private int opID;

    @Column(name = "confirmation_cd", length = 10, updatable = false)
    private String confirmationCd = java.util.UUID.randomUUID().toString().substring(0, 10);

    @Column(name = "status")
    private String status = "open";

    public Bounty() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public int getDeliveryPostalCd() {
        return deliveryPostalCd;
    }

    public void setDeliveryPostalCd(int deliveryPostalCd) {
        this.deliveryPostalCd = deliveryPostalCd;
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public int getOpID() {
        return opID;
    }

    public void setOpID(int opID) {
        this.opID = opID;
    }

    public String getConfirmationCd() {
        return confirmationCd;
    }

    public void setConfirmationCd(String confirmationCd) {
        this.confirmationCd = confirmationCd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addImage(byte[] image) {
        images.add(image);
    }

    public void removeImage(byte[] img) {
        images.remove(img);
    }
}