package com.nyp.microdelivery.enterprise.Entity;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> orderItems;

    public Cart(){
        orderItems=new ArrayList<>();
    }

    public ArrayList<CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<CartItem> orderItems) {
        this.orderItems = orderItems;
    }
    public void addToCart(Item item){
        boolean isAdd=false;
        for(CartItem ct:orderItems){
            if(ct.getItem().getId()==item.getId()){
                ct.setQuantity(ct.getQuantity()+1);
                isAdd=true;
            }
        }
        if(!isAdd){
            orderItems.add(new CartItem(item,1));
        }
    }
    public void deleteInCart(Item item){

        for(CartItem ct:orderItems){
            if(ct.getItem().getId()==item.getId()){
                orderItems.remove(orderItems);
            }
        }

    }
    public void increaseInCart(Item item){

        for(CartItem ct:orderItems){
            if(ct.getItem().getId()==item.getId()){
                ct.setQuantity(ct.getQuantity()+1);
            }
        }

    }
    public void decreaseInCart(Item item){

        for(CartItem ct:orderItems){
            if(ct.getItem().getId()==item.getId()){
                if(ct.getQuantity()==1) orderItems.remove(ct);
                else ct.setQuantity(ct.getQuantity()-1);
            }
        }

    }



    public double getTotalPrice(){
        int sum=0;
        for(CartItem ct:orderItems){
            sum+=ct.getItem().getPrice()*ct.getQuantity();
        }
        return sum;
    }
    public void reset(){
        orderItems.clear();
    }



}
