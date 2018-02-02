package com.nyp.microdelivery.enterprise;


import com.nyp.microdelivery.enterprise.Entity.Item;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class Category implements Serializable{
    private List<Item> items =new ArrayList<>();
    public void load(){

    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
