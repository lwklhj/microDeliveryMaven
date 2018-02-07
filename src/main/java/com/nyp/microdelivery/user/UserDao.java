package com.nyp.microdelivery.user;

import com.nyp.microdelivery.enterprise.Entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class UserDao {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    //private static InitialContext ic = null;
    //private static DataSource ds;
    public UserDao() {
        /*try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/delivery");
        } catch (NamingException e) {
            e.printStackTrace();
        }*/


    }

    public static void registerStore(Store store) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(store);
        transaction.commit();
        session.close();
    }

    public static User logging(String userName, String passwd) {
        Session session = sessionFactory.openSession();
        String query = "select * from user where userName='" + userName + "' and password='" + passwd + "'";
        System.out.print(query);
        Transaction transaction = session.beginTransaction();
        Object obj = session.createNativeQuery(query).addEntity(User.class).uniqueResult();
        session.close();
        return (User) obj;
    }

    public static void registerUser(User user) {


       /* String query="INSERT into user values ('"+user.getEmail()+"','"+user.getAddr()+"','"+user.getPhoneNo()+"','"+user.getUserName()+"','"+user.getPassword()+"');";



        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("jdbc/delivery");

            Connection conn = ds.getConnection();
            Statement stat = conn.createStatement();
            System.out.println(user.getUserName());

            stat.execute(query);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NamingException e){e.printStackTrace();}*/

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();
        session.close();


    }

    public static User getUser(int id) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "select * from user where id=" + id;

        User user = (User) session.createNativeQuery(query).addEntity(User.class).uniqueResult();

        session.close();
        return user;
    }

    public static void update(User user) {

        Session session = sessionFactory.openSession();
        Transaction traction = session.beginTransaction();
        session.update(user);
        traction.commit();
        session.close();


    }

    public static boolean checkDuplicateLogin(String userName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "select * from user  where userName='" + userName+"'";

        User user = (User) session.createNativeQuery(query).addEntity(User.class).uniqueResult();

        session.close();
        if(user!=null){
            return true;
        }else{
            return false;
        }


    }
}
