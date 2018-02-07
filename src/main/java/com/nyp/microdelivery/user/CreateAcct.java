package com.nyp.microdelivery.user;

import com.nyp.microdelivery.enterprise.Entity.Store;
import com.nyp.microdelivery.enterprise.Entity.StoreDao;
import org.primefaces.event.TabChangeEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class CreateAcct {
    @Inject
    private User user;
    private String type="";
    @Inject
    private Store store;





    public String register(){

        //user.setUserName("hhhhhh");
        Logger logger=Logger.getLogger("xxx");
        logger.log(Level.INFO,user.getUserName()+"  xxx");
        User u=new User();
        u.setAddr(user.getAddr());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setPhoneNo(user.getPhoneNo());
        u.setUserName(user.getUserName());
        UserDao.registerUser(u);

        return "registSuccess";
    }
    public String registerStore(){


        Store s=new Store();
        s.setAddr(store.getAddr());
        s.setCompanyName(store.getCompanyName());
        s.setEmail(store.getEmail());
        s.setPasswd(store.getPasswd());
        s.setPhoneNo(store.getPhoneNo());
        s.setPostalCode(store.getPostalCode());
        s.setStoreType(store.getStoreType());
        StoreDao.saveStore(s);
        return "registSuccess";

    }
    public void onTabChange(TabChangeEvent event){
       type=event.getTab().getId();
       System.out.print(type+" cccc");

    }
    public void validateSamePassword(FacesContext context, UIComponent toValidate, Object value){
        String comfirmPassword = (String)value;
        if(!comfirmPassword.equals(user.getPassword())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Passwords do not match!","Passwords do not match!");
            //throw new Validatorexception(msg);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
