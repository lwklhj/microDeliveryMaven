package enterprise.Entity;

import com.nyp.microdelivery.posting.HibernateUtil;
import enterprise.Entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnterpriseDao {
    private SessionFactory sessionFactory= HibernateUtil.getSessionFactory();

    public List<Item> getAllItem(){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<Item> list = session.createNativeQuery("SELECT * FROM enterpriseItem").addEntity(Item.class).list();

        session.close();
        return list;

    }

    public void update(Item i){

        String query="update  enterpriseItem set item_name='"+i.getName()+"',";
        query+="description='"+i.getDescrption()+"',";
        query+="price="+i.getPrice()+",";
        query+="item_type='"+i.getType()+"',";
        query+="pic='"+i.getPicture()+"'";
        query+=" where id="+i.getId();
        javax.naming.InitialContext ctx = null;
        ArrayList<Item> list=new ArrayList<>();
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
    public void deleteItem(Item i){


        Session session=sessionFactory.openSession();
        Query query=session.createQuery("delete Item where id=:itemid");
        query.setParameter("itemid",i.getId());
        session.beginTransaction();
        query.executeUpdate();

        session.close();

    }
    public void save(Item i){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(i);
        session.getTransaction().commit();


        session.close();

        /*String query="insert into enterpriseItem(item_name,description,price,item_type,pic) values('"+i.getName()+"','";
        query+=i.getDescrption()+"',";
        query+=i.getPrice()+",'";
        query+=i.getType()+"','";
        query+=i.getPicture()+"')";
        javax.naming.InitialContext ctx = null;
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
        }*/

    }
}
