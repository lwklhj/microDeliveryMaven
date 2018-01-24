package com.nyp.microdelivery.enterprise;

import com.nyp.microdelivery.enterprise.Entity.EnterpriseDao;
import com.nyp.microdelivery.enterprise.Entity.Item;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AddItem {

    private String name;
    private String description;
    private String price;
    private String type;
    private String pic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String add(){
        EnterpriseDao e=new EnterpriseDao();
        Item i=new Item();
        i.setType(type);
        i.setName(name);
        i.setPicture(pic);
        i.setDescrption(description);
        i.setPrice(Double.parseDouble(price));
        e.save(i);

        return "/Enterprise/main";
    }
}
