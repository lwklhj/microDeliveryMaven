package com.nyp.microdelivery.enterprise;

import com.nyp.microdelivery.enterprise.Entity.Cart;
import com.nyp.microdelivery.enterprise.Entity.Item;
import com.nyp.microdelivery.enterprise.Entity.Store;
import com.nyp.microdelivery.enterprise.Entity.StoreDao;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class StoreDetail implements Serializable{
    private Cart cart=new Cart();
    private int id;
    private byte[] storeQr;
    private Store store;





    private List<Item> storeItems;


    public void loadStoreItem(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        String storeId = request.getParameter("storeId");


        if(storeId!=null){
            id=Integer.parseInt(storeId);
            storeItems =StoreDao.getAllItem(id);
            store=StoreDao.getStoreInfo(id);
        }

        storeQr=getQrCode();



    }
    //cart
    public void addToCart(Item item){
        cart.addToCart(item);
    }

    public String getTotalPrice() {
        return cart.getTotalPrice()+"";
    }




    public List<Item> getStoreItems() {
        return storeItems;
    }

    public void setStoreItems(List<Item> storeStoreItems) {
        this.storeItems = storeStoreItems;
    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String checkOut(){
        System.out.println(cart.getOrderItems().size()+"size");
        if(cart.getOrderItems().isEmpty());
        else return "checkout";
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getStoreQr() {
        return storeQr;
    }

    public void setStoreQr(byte[] storeQr) {
        this.storeQr = storeQr;
    }

    public byte[] getQrCode(){
        ExternalContext externalContext=FacesContext.getCurrentInstance().getExternalContext();
        String uri = ((HttpServletRequest) externalContext.getRequest()).getRequestURI();
        Map<String, String> params =externalContext.getRequestParameterMap();
        String id = params.get("storeId");
        uri+="?storeId="+id;
        QRCodeUtil qr=new QRCodeUtil();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/mainIcon.png");

        try {
            Image logo=ImageIO.read(iStream);
            ImageIO.write( qr.createQrCode(uri,logo), "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
