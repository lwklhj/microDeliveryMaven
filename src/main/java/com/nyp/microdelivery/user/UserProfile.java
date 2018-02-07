package com.nyp.microdelivery.user;


import com.nyp.microdelivery.enterprise.Entity.DeliveryAddress;
import com.nyp.microdelivery.enterprise.Entity.OrderDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class UserProfile implements Serializable{
    private User user;
    private String receName;
    private String addr;
    private String postalCode;
    private List<DeliveryAddress> addressList;

    public List<DeliveryAddress> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<DeliveryAddress> addressList) {
        this.addressList = addressList;
    }

    @Inject
    private Login login;

    @PostConstruct
    public void load(){


    }
    public void loadUser(){
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String id = request.getParameter("id");
        Logger logger = Logger.getLogger("xxx");
        logger.log(Level.INFO,id+"size");
        user= UserDao.getUser(login.getUser().getId());
        addressList=OrderDao.getDeliveryAddress(user.getId());
        System.out.println(addressList.size()+"size");


    }



    public void updateUser(){

        System.out.print(user.getUserName()+"      xxxxxxxxxxxxx");
        UserDao.update(user);


    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Don't touch this xin yi
    public String getUsernameFromID(int userID) {
        return UserDao.getUser(userID).getUserName();
    }


    //address
    public String addAddress(){
        System.out.print("addr"+addr);
        DeliveryAddress address=new DeliveryAddress();
        address.setUserId(user.getId());
        address.setAddr(addr);
        address.setReceiveName(receName);
        address.setPostalCode(postalCode);
        OrderDao.saveSDeliveryAddress(address);
        addressList=OrderDao.getDeliveryAddress(user.getId());
        receName="";
        postalCode="";
        addr="";
        return "";

    }

    public String getReceName() {
        return receName;
    }

    public void setReceName(String receName) {
        this.receName = receName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
