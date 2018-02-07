package com.nyp.microdelivery.bounty.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
public class BountyDAO {
    private static Session session = new Configuration().configure().buildSessionFactory().openSession();
    private static Transaction tx = session.getTransaction();

    public static void saveOrUpdatePost(Bounty b) {
        try {
            tx.begin();
            session.saveOrUpdate(b);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            System.err.println(e);
            tx.rollback();
        }
    }

    public static Bounty getPostById(int id) {
        return session.get(Bounty.class, id);
    }

    public static List<Bounty> getPostsByUser(int userID) {
        return session.createNativeQuery("SELECT * FROM posting WHERE op_ID = '"+userID+"'", Bounty.class).getResultList();
    }

    public static List<Bounty> getPostsByPostalCd(int userID, String postal_cd) {
        return session.createNativeQuery("SELECT * FROM posting WHERE courier_id = 0 AND op_id != '"+userID+"' AND substr(deliveryPostalCd, 1, 2) = '"+postal_cd.substring(0, 2)+"';", Bounty.class).getResultList();
    }

    public static List<Bounty> getBountiesHuntedByUser(int userID) {
        return session.createNativeQuery("SELECT * FROM posting WHERE courier_id = '"+userID+"' AND status != 'completed'", Bounty.class).getResultList();
    }

//    public static List<Bounty> getPostsByCategory(String x) {
//        return session.createNativeQuery("SELECT * FROM bounty WHERE category='"+x+"'", Bounty.class).getResultList();
//    }

//    public static List<Bounty> getPostsByKeywords(String search) {
//        //search = search.trim().replaceAll("[^a-zA-Z0-9]", " ");
//        String[] keyword = search.split(" ");
//        String search_items = null;
//
//        for(String s : keyword) {
//            search_items += "+" + s;
//        }
//
//        return session.createNativeQuery("SELECT * FROM microdelivery.bounty WHERE MATCH(title, description) against('"+search_items+"');", Bounty.class).getResultList();
//    }

//    public static void updatePost(Bounty post) {
//        session.update(post);
//    }

    public static void deleteBounty(Bounty bounty) {
        try {
            tx.begin();
            session.delete(bounty);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            System.err.println(e);
            tx.rollback();
        }
    }
}