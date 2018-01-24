package enterprise.Entity;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Named
@Entity
@Table(name="enterpriseItem")
public class Item {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "description")
    private String descrption;
    @Column(name = "price")
    private double price;
    @Column(name = "item_type")
    private String type;
    @Column(name = "pic")
    private String picture;

    public Item(){}
    public Item(String name, String descrption, double price, String pic){
        this.name=name;
        this.descrption=descrption;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
