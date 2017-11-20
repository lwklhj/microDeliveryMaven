/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.posting;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Liu Woon Kit
 */

@Named("posting")
@Entity
@Table(name="Posting")
@ViewScoped
public class Posting implements Serializable {
    @Id
    private int id;
    
    @Column
    private byte[] image; 
    
    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;
    
    @Column(name="opID")
    private String opID;
    
    @Column(name="courierID")
    private String courierID;
    
    @Column(name="price")
    private double price;
    
    @Column(name="location")
    private String location;

    public Posting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public String getOpID() {
        return opID;
    }

    public void setOpID(String opID) {
        this.opID = opID;
    }

    public String getCourierID() {
        return courierID;
    }

    public void setCourierID(String courierID) {
        this.courierID = courierID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public void savePosting() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(this);
        tx.commit();
        session.close();
    }
    
    public static void createPosting(Posting p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
        session.close();
    }
    
//    public static List<Posting> getAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = session.beginTransaction();
//        List<Posting> list = session.createSQLQuery("SELECT * FROM posting").addEntity(Posting.class).list();
//        session.close();
//        return list;
//    }
    
    public static List<Posting> search(String search) {
        search = search.trim().replaceAll("[^a-zA-Z0-9]", " ");
        String[] keyword = search.split(" ");
        String search_items = null;
        
        for(String s : keyword) {
            search_items += "+" + s;
        }
        String query = "SELECT * FROM microdelivery.posting WHERE MATCH(title, description) against('"+search_items+"');";
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Posting> list = session.createSQLQuery(query).addEntity(Posting.class).list();
        session.close();
        return list;
    }
    
    public static Posting getDetails(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Posting p = (Posting) session.createSQLQuery("SELECT * FROM posting WHERE id = ?").addEntity(Posting.class).setParameter(0, id).uniqueResult();
        return p;
    }
    
    public static List<Posting> searchByPostalCd(String postal_cd) {
        int range = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Posting> list = session.createSQLQuery("SELECT SUBSTR(postalCd, 1, 2) FROM posting WHERE SUBSTR(postalCd, 1, 2) BETWEEN SUBSTR(postalCd, 1, 2)-"+range+" AND SUBSTR(postalCd, 1, 2)+"+range+"").addEntity(Posting.class).list();
        session.close();
        return list;
    }
    
    public void uploadHandler(FileUploadEvent event) {
        image = event.getFile().getContents();
    }
    
    public StreamedContent getStreamedContent() {
        return new DefaultStreamedContent(new ByteArrayInputStream(image));
    }
}