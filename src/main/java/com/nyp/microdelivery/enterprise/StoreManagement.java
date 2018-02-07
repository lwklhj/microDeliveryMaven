package com.nyp.microdelivery.enterprise;

import com.nyp.microdelivery.enterprise.Entity.*;
import com.nyp.microdelivery.user.Login;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class StoreManagement implements Serializable{
    @Inject
    private Login login;

    private List<Item> itemList;
    private Item selectItem;
    private Store store;
    private int storeId;
    private Store saveStore;

    private List<Order> orders;


    public Item getSelectItem() {

        return selectItem;
    }


    public void setSelectItem(Item selectItem) {
        this.selectItem = selectItem;
    }
    public void load(){
        storeId=login.getStore().getId();
        //selectItem=new Item();
        store =StoreDao.getStoreInfo(storeId);
        itemList=StoreDao.getAllItem(storeId);
        List<Order> list=OrderDao.getAllUncompleteOrderByStore(storeId);
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

            selectItem.setStoreId(store.getId());
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
        try {

            selectItem.setPicture(IOUtils.toByteArray(file.getInputstream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleStoreImageUpload(FileUploadEvent event) {
        UploadedFile file=event.getFile();
        saveStore=new Store();

        try {

            saveStore.setPicture(IOUtils.toByteArray(file.getInputstream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String saveStore(){
        Store newStore=new Store();
        newStore.setId(store.getId());
        newStore.setCompanyName(store.getCompanyName());
        newStore.setAddr(store.getAddr());
        newStore.setEmail(store.getEmail());
        newStore.setPasswd(store.getPasswd());
        newStore.setPhoneNo(store.getPhoneNo());
        newStore.setPostalCode(store.getPostalCode());
        newStore.setStoreType(store.getStoreType());
        if(saveStore!=null) newStore.setPicture(saveStore.getPicture());
        else if(store.getPicture()!=null) newStore.setPicture(store.getPicture());

        StoreDao.saveStore(newStore);
        return null;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
