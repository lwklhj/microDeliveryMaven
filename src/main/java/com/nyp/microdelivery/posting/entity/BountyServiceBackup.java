package com.nyp.microdelivery.posting.entity;

import com.nyp.microdelivery.posting.Bounty;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

public class BountyServiceBackup {
    private Session session = new Configuration().configure().buildSessionFactory().openSession();
    private Transaction tx = session.getTransaction();

    @PostConstruct
    public void init() {
        tx.begin();
    }

    @PreDestroy
    public void terminate() {
        session.flush();
        tx.commit();
    }


    public void saveOrUpdatePost(Bounty p) {
        try {
            session.saveOrUpdate(p);
        } catch (Exception e) {
            System.err.println(e);
            tx.rollback();
        }
    }

    public Bounty getPostById(int id) {
        return session.get(Bounty.class, id);
    }

    public List<Bounty> getPostsByUser(String userID) {
        userID = User.userID;
        return session.createNativeQuery("SELECT * FROM posting WHERE opID = '"+userID+"'", Bounty.class).getResultList();
    }

    public List<Bounty> getPostsByPostalCd(String postal_cd) {
        return session.createNativeQuery("SELECT * FROM posting WHERE courier_id IS NULL AND opID != '"+User.userID+"' AND substr(postal_cd, 1, 2) = '"+postal_cd.substring(0, 2)+"';", Bounty.class).getResultList();
    }

    public List<Bounty> getPostsByCategory(String x) {
        return session.createNativeQuery("SELECT * FROM posting WHERE category='"+x+"'", Bounty.class).getResultList();
    }

    public List<Bounty> getPostsByKeywords(String search) {
        //search = search.trim().replaceAll("[^a-zA-Z0-9]", " ");
        String[] keyword = search.split(" ");
        String search_items = null;

        for(String s : keyword) {
            search_items += "+" + s;
        }

        return session.createNativeQuery("SELECT * FROM microdelivery.posting WHERE MATCH(title, description) against('"+search_items+"');", Bounty.class).getResultList();
    }

    public void updatePost(Bounty post) {
        session.update(post);
    }

    public void deletePost(Bounty post) {
        session.delete(post);
    }
}