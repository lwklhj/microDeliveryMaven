package com.nyp.microdelivery.enterprise.Entity;

import com.nyp.microdelivery.posting.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnterpriseDao {
    //private static SessionFactory sessionFactory=

    public List<Item> getAllItem(){




        SessionFactory sessionFactory= HibernateUtil.getSESSION_FACTORY();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<Item> list = session.createNativeQuery("SELECT * FROM enterpriseItem").addEntity(Item.class).list();
        /*String query="select * from enterpriseItem";
        javax.naming.InitialContext ctx = null;
        ArrayList<Item> list=new ArrayList<>();
        try {
            ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/microDelivery");
            java.sql.Connection conn = ds.getConnection();
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery(query);
            while(rs.next()){
                Item i=new Item();
                i.setId(rs.getInt("id"));
                i.setDescrption(rs.getString("description"));
                i.setName(rs.getString("item_name"));
                i.setPrice(rs.getDouble("price"));
                i.setPicture(rs.getString("pic"));
                i.setType(rs.getString("item_type"));
                list.add(i);


            }
            s.close();
            conn.close();

        } catch (NamingException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }*/









       session.close();
        return list;

    }
    public void deleteItem(Item i){
        String query="delete * from enterpriseItem where id="+i.getId();
        javax.naming.InitialContext ctx = null;
        ArrayList<Item> list=new ArrayList<>();
        try {
            ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/microDelivery");
            java.sql.Connection conn = ds.getConnection();
            Statement s=conn.createStatement();
            //s.execute(query);

            s.close();
            conn.close();

        } catch (NamingException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
    public void save(Item i){
        String query="insert into enterpriseItem(item_name,description,price,item_type,pic) values('"+i.getName()+"','";
        query+=i.getDescrption()+"',";
        query+=i.getPrice()+",'";
        query+=i.getType()+"','";
        query+=i.getPicture()+"')";
        javax.naming.InitialContext ctx = null;
        Logger log=Logger.getLogger("xx");
        log.info("test");
        log.log(Level.INFO,query);
        try {
            ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/microDelivery");
            java.sql.Connection conn = ds.getConnection();
            Statement s=conn.createStatement();
            s.execute(query);
            s.close();
            conn.close();



        } catch (NamingException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
