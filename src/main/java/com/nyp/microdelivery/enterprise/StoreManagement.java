package com.nyp.microdelivery.enterprise;

import com.nyp.microdelivery.enterprise.Entity.Item;
import com.nyp.microdelivery.enterprise.Entity.Order;
import com.nyp.microdelivery.enterprise.Entity.OrderDao;
import com.nyp.microdelivery.enterprise.Entity.StoreDao;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class StoreManagement implements Serializable{
    private int storeId=1;
    private List<Item> itemList;
    private Item selectItem;

    private List<Order> orders;


    public Item getSelectItem() {

        return selectItem;
    }

    public void setSelectItem(Item selectItem) {
        this.selectItem = selectItem;
    }
    public void load(){
        int storeId=1;
        //selectItem=new Item();
        itemList= StoreDao.getAllItem(storeId);
        List<Order> list= OrderDao.getAllUncompleteOrderByStore(storeId);
        orders=list;

    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void deleteItem(){

        itemList.remove(selectItem);
        StoreDao.deleteItem(selectItem);
    }
    public void resetSelectItem(ActionEvent event){
        selectItem=new Item();


    }
    public String saveItem(){

            selectItem.setStoreId(storeId);
            StoreDao.save(selectItem);
        return null;

    }

    public String update(Item item){
        selectItem=item;
        return null;
    }

    public void orderComplete(Order order){
        orders.remove(order);
        OrderDao.completeOrder(order);

    }
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file=event.getFile();
        System.out.println("upload file name "+file.getFileName());
        try {

            selectItem.setPicture(IOUtils.toByteArray(file.getInputstream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
