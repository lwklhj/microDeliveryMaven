package com.nyp.microdelivery.enterprise.Entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class OrderDao {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static int saveOrderItem(List<CartItem> cartItems,Order order){
        Logger log=Logger.getLogger("xxxx");

        //date time
        //userid
        //addressId

        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();


        session.save(order);
        int orderId=order.getOrderId();
        for(CartItem cartItem:cartItems){
            OrderItem orderItem=convertCartItemToOrderItem(cartItem,orderId);
            session.save(orderItem);
        }
        transaction.commit();





        /*


        session.getTransaction().commit();*/
        session.close();
        return orderId;




    }
    private static OrderItem convertCartItemToOrderItem(CartItem cartItem,int orderId){
        OrderItem orderItem=new OrderItem();
        orderItem.setItemId(cartItem.getItem().getId());
        orderItem.setPrice(cartItem.getItem().getPrice());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setOrderId(orderId);
        return orderItem;
    }

    public static Order getOrderInformation(int orderId){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Order.class).add(Restrictions.eq("orderId",orderId));
        Object result = criteria.uniqueResult();

        session.close();

        return (Order)result;


    }
    public static List<Order> getAllUncompleteOrderByStore(int storeId){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        String query="select * from orders where storeId="+storeId+" and complete="+0;

        List<Order> list = session.createNativeQuery(query).addEntity(Order.class).list();
        for(Order order:list){
            order.setOrderItems(getAllOrderItem(order.getOrderId()));
        }

        session.close();
        return list;

    }

    public static List<OrderItem> getAllOrderItem(int orderId){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Query query=session.createQuery("from OrderItem where orderId=:id");
        query.setParameter("id",orderId);
        List<OrderItem> list = query.list();
        session.close();
        return list;

    }
    public static DeliveryAddress getAddress(int addressId){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Order.class).add(Restrictions.eq("addrId",addressId));
        Object result = criteria.uniqueResult();

        session.close();
        return (DeliveryAddress) result;
    }
    public static void completeOrder(Order order){
        Session session=sessionFactory.openSession();
        Transaction traction=session.beginTransaction();
        order.setIsComplete(1);
        session.update(order);
        traction.commit();
        session.close();

    }

}
