/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.posting;

import enterprise.Entity.Company;

/**
 *
 * @author Liu Woon Kit
 */
public class Main {
    public static void main(String[] args) {
        Company c=new Company();
        System.out.print(c.getCompanyDetail().toString());
    }
}