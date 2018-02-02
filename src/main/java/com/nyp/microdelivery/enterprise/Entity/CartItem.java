package com.nyp.microdelivery.enterprise.Entity;

public class CartItem {
    private Item item;
    private int quantity;
    private String itemTotalPrice;
    private String QuantityAndItem;

    public CartItem(Item item,int quantity){
        this.quantity=quantity;
        this.item=item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getQuantityAndItem(){

        return quantity+" * "+item.getName();
    }
    public String getItemTotalPrice(){
        return quantity*item.getPrice()+"";
    }
}
