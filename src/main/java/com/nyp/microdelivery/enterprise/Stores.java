package com.nyp.microdelivery.enterprise;


import com.nyp.microdelivery.enterprise.Entity.Store;
import com.nyp.microdelivery.enterprise.Entity.StoreDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class Stores implements Serializable{
    private List<Store> foodList;
    private List<Store> beverageList;
    private List<Store> electronicList;
    private List<Store> groceryList;
    private List<Store> bookList;
    private List<Store> stationaryList;
    private List<Store> medicineList;
    private List<Store> gameList;

    public List<Store> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Store> foodList) {
        this.foodList = foodList;
    }

    public List<Store> getBeverageList() {
        return beverageList;
    }

    public void setBeverageList(List<Store> beverageList) {
        this.beverageList = beverageList;
    }

    public List<Store> getElectronicList() {
        return electronicList;
    }

    public void setElectronicList(List<Store> electronicList) {
        this.electronicList = electronicList;
    }

    public List<Store> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(List<Store> groceryList) {
        this.groceryList = groceryList;
    }

    public List<Store> getBookList() {
        return bookList;
    }

    public void setBookList(List<Store> bookList) {
        this.bookList = bookList;
    }

    public List<Store> getStationaryList() {
        return stationaryList;
    }

    public void setStationaryList(List<Store> stationaryList) {
        this.stationaryList = stationaryList;
    }

    public List<Store> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Store> medicineList) {
        this.medicineList = medicineList;
    }

    public List<Store> getGameList() {
        return gameList;
    }

    public void setGameList(List<Store> gameList) {
        this.gameList = gameList;
    }
    private  List<Store> storeList;

    public void load(){


        storeList = StoreDao.getAllComany();

        foodList=getStoreByType("food");
        electronicList=getStoreByType("electronic");
        groceryList=getStoreByType("grocery");
        bookList=getStoreByType("book");
        stationaryList=getStoreByType("stationary");
        beverageList=getStoreByType("beverage");
        gameList=getStoreByType("game");
        medicineList=getStoreByType("medicine");


    }

    public List<Store> getStoreByType(String type){
        List<Store> list=new ArrayList<>();
        for(Store c: storeList){
            if(c.getStoreType().equals(type)) list.add(c);
        }
        return list;
    }

}
