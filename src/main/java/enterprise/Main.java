package enterprise;

import enterprise.Entity.EnterpriseDao;
import enterprise.Entity.Item;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("enterpriseMain")
@SessionScoped
public class Main implements Serializable{
    private List<Item> itemList;
    private Item selectItem;
    private boolean firstTimeCall=true;

    public Item getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(Item selectItem) {
        this.selectItem = selectItem;



    }

    public Main(){


    }

    public void load(){
        EnterpriseDao e=new EnterpriseDao();
        itemList=e.getAllItem();


    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
    public void update(){
        EnterpriseDao dao=new EnterpriseDao();

        dao.update(selectItem);


    }
    public void deleteItem(){

        itemList.remove(selectItem);

        EnterpriseDao dao=new EnterpriseDao();

        dao.deleteItem(selectItem);
            //load();


       //


    }
    private String s;
    public String getS(){
        return itemList.size()+"";
    }

}
