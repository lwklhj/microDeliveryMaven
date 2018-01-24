/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.posting;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liu Woon Kit
 */

@Named("post")
@SessionScoped
@Entity
@Table(name="Posting")
public class Post implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(targetClass = byte[].class)
    @CollectionTable(name = "post_images", joinColumns = @JoinColumn(name = "Post_id", referencedColumnName = "id"))
    private List<byte[]> images = new ArrayList<>();
    
    @Column(name="title")
    private String title;
    
    @Column(name="description")
    private String description;
    
    @Column(name="datetime")
    private Date dateTime;
    
    @Column(name="op_id")
    private String opID;
    
    @Column(name="courier_id")
    private String courierID;
    
    @Column(name="price")
    private double price;
    
    @Column(name="location")
    private String location;

    @Column(name="category")
    private String category;

    @Column(name="postal_cd")
    private String postal_cd;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<byte[]> getImages() {
        return images;
    }

    public void setImages(ArrayList<byte[]> images) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPostal_cd() {
        return postal_cd;
    }

    public void setPostal_cd(String postal_cd) {
        this.postal_cd = postal_cd;
    }

    public void savePosting() {
        try {
            //Set to temporary number first
            this.opID = "1";
        } catch (Exception e) {
            System.out.println("ERROR: Unable to set asker's ID");
        }
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        session.update(this);
        tx.commit();
        session.close();
    }
    
    public static void createPosting(Post p) {
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
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


    public static List<Post> search(String search) {
        //search = search.trim().replaceAll("[^a-zA-Z0-9]", " ");
        String[] keyword = search.split(" ");
        String search_items = null;
        
        for(String s : keyword) {
            search_items += "+" + s;
        }
        String query = "SELECT * FROM microdelivery.posting WHERE MATCH(title, description) against('"+search_items+"');";
        
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        List<Post> list = session.createSQLQuery(query).addEntity(Post.class).list();
        session.close();
        return list;
    }

    public static Post getDetails(String id) {
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Post p = session.get(Post.class, Integer.parseInt(id));
        // Fix for lazy initialization
        Hibernate.initialize(p.getImages());
        session.close();
        return p;
    }

    public static List<Post> getAllUserBounties() {
        int userID = 1;//;
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        List<Post> list = session.createNativeQuery("SELECT * FROM posting WHERE opID = '"+userID+"'").addEntity(Post.class).list();
        for(Post p : list) {
            Hibernate.initialize(p.getImages());
        }
        tx.commit();
        session.close();
        return list;
    }
    
    public static List<Post> searchByPostalCd(String postal_cd) {
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        List<Post> list = session.createNativeQuery("SELECT * FROM posting WHERE substr(postal_cd, 1, 2) = '"+postal_cd.substring(0, 2)+"';").addEntity(Post.class).list();
        for(Post p : list) {
            Hibernate.initialize(p.getImages());
        }
        tx.commit();
        session.close();
        return list;
    }

    public static List<Post> searchByCategory(String x) {
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        List<Post> list = session.createNativeQuery("SELECT * FROM posting WHERE category='"+x+"'").addEntity(Post.class).list();
        for(Post p : list) {
            Hibernate.initialize(p.getImages());
        }
        session.close();
        return list;
    }
    
    public void accept() {
        int userID = 11111;//User.getCurrentID;
        Session session = HibernateUtil.getSESSION_FACTORY().openSession();
        Transaction tx = session.beginTransaction();
        session.createNativeQuery("INSERT INTO postingcourier VALUES("+ id +", userID)").executeUpdate();
        tx.commit();
        session.close();
    }
    
    public boolean isAccepted() {
        return false;
    }

    public void uploadImage(FileUploadEvent event) {
        try {
            byte[] image = IOUtils.toByteArray(event.getFile().getInputstream());
            images.add(image);
        } catch (IOException ex) {
            Logger.getLogger(Post.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("UPLOADED");
    }
}