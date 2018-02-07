package com.nyp.microdelivery.enterprise.Entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

public class StoreDao {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    //company items
    public static Item getItem(int id){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Item.class).add(Restrictions.eq("id",id));
        Object result = criteria.uniqueResult();

        session.close();

        return (Item) result;

    }
    public static List<Item> getAllItem(int id){

        Session session=sessionFactory.openSession();
        session.beginTransaction();
        String query="select * from storeItem where storeId="+id;


        List<Item> list = session.createNativeQuery(query).addEntity(Item.class).list();

        session.close();
        return list;

    }

    public static void update(Item i){

        Session session=sessionFactory.openSession();
        Transaction traction=session.beginTransaction();
        session.update(i);
        traction.commit();
        session.close();


    }
    public static void deleteItem(Item i){



        Session session=sessionFactory.openSession();
        Query query=session.createQuery("delete Item where id=:itemId");
        query.setParameter("itemId",i.getId());
        session.beginTransaction();
        query.executeUpdate();

        session.close();

    }
    public static void save(Item i){




        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(i);
        session.getTransaction().commit();
        session.close();
    }

    //  store
    public static List<Store> getAllComany(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<Store> list = session.createNativeQuery("SELECT * FROM store").addEntity(Store.class).list();
        session.close();
        return list;

    }
    public static Store getStoreInfo(int id){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Store.class).add(Restrictions.eq("id",id));
        Object result = criteria.uniqueResult();
        session.close();

        return (Store) result;

    }
    public static void saveStore(Store store){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(store);
        session.getTransaction().commit();
        session.close();
    }


    public static Store storeLogging(String email,String passwd){
        Session session = sessionFactory.openSession();
        String query = "select * from store where email='" + email + "' and passwd='" + passwd + "'";
        System.out.print(query);
        Transaction transaction = session.beginTransaction();
        Object obj = session.createNativeQuery(query).addEntity(Store.class).uniqueResult();
        session.close();
        return (Store) obj;




    }




}
