package com.nyp.microdelivery.bounty.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
public class BountyMessageDAO {
    private static Session session = new Configuration().configure().buildSessionFactory().openSession();
    private static Transaction tx = session.getTransaction();

    public static void saveMessage(BountyMessage m) {
        try {
            tx.begin();
            session.save(m);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            System.err.println(e);
            tx.rollback();
        }
    }

    public static List<BountyMessage> retrieveMessages(int bountyId) {
        return session.createNativeQuery("SELECT * FROM bounty_messages WHERE bountyId = '"+bountyId+"'", BountyMessage.class).getResultList();
    }
}
