package com.nyp.microdelivery.bounty;

import com.nyp.microdelivery.bounty.entity.BountyDAO;

public class Main {
    public static void main(String args[]) {

        System.out.print(BountyDAO.getPostsByUser(1).size());
    }
}
