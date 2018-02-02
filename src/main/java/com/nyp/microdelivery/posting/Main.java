/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nyp.microdelivery.posting;

import com.nyp.microdelivery.posting.entity.BountyMessage;

/**
 *
 * @author Liu Woon Kit
 */
public class Main {
    public static void main(String agrs[]) {
        new MessageController().insertMessage("Hello there", 21);
        for(BountyMessage m : new MessageController().getMessages(21)) {
            System.out.println(m.getText());
        }
    }
}