/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.posting;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
public class Main {
    public static void main(String[] args) {
        List<Post> list = Post.searchByPostalCd("238858");
        for(Post p : list) {
            System.out.println(p.getPostal_cd());
        }
    }
}