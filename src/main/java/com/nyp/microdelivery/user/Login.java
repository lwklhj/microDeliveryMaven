package com.nyp.microdelivery.user;

import com.nyp.microdelivery.enterprise.Entity.Store;
import com.nyp.microdelivery.enterprise.Entity.StoreDao;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class Login implements Serializable{
    private User user;
    private String userName;
    private String virtualCheck;
    private boolean rememberMe = false;
    private Store store;
    private String storeName;
    private String storePass;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePass() {
        return storePass;
    }

    public void setStorePass(String storePass) {
        this.storePass = storePass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVirtualCheck() {
        return virtualCheck;
    }

    public void setVirtualCheck(String virtualCheck) {
        this.virtualCheck = virtualCheck;
    }

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String login() {
        User u = UserDao.logging(getUserName(), getPassWord());
        Logger log = Logger.getLogger("xinyuInfo");
        if (u != null) {



            user = u;

            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/" + "index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "index";

        } else {

            log.log(Level.INFO,  "not found");

        }
        return null;



    }
    public String storeLogin() {
        Store u = StoreDao.storeLogging(getStoreName(), getStorePass());
        if (u != null) {
            store = u;

            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/Enterprise/storeManagement.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {


        }
        return null;



    }
    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean check) {
        if(check){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Cookie cookie[] = ((HttpServletRequest)facesContext.getExternalContext().getRequest()).getCookies();
            if(cookie != null && cookie.length > 0)
                for (int i =0; i < cookie.length; i++){
                    String cName= cookie[i].getName();
                    String cValue = cookie[i].getValue();
                    System.out.println("***cV***"+cValue);
                    if(cName.equals("cUserName")){
                        setUserName(cValue);
                    }else if (cName.equals("cPassword")){
                        setUserName(cValue);
                    }else if (cName.equals("cVirtualCheck")){
                        setVirtualCheck(cValue);
                        if(getVirtualCheck().equals("false")){
                            setRememberMe(false);
                            setUserName(null);
                            setPassWord(null);
                        }else if (getVirtualCheck().equals("true")){
                            System.out.println("login");
                            setRememberMe(true);
                        }
                    }
                }
        }

        this.rememberMe = rememberMe;
    }
    public  void  logout(){
        user = null;
        store=null;
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/" + "index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }


