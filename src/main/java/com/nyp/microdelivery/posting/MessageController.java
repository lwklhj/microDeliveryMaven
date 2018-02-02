package com.nyp.microdelivery.posting;

import com.nyp.microdelivery.posting.entity.BountyMessage;
import com.nyp.microdelivery.posting.entity.BountyService;
import com.nyp.microdelivery.posting.entity.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@RequestScoped
@Named
public class MessageController {
    public void insertMessage(String text, int bountyId) {
        System.err.println("INSERTING MESSAGE");
        BountyService.saveMessage(new BountyMessage(bountyId, User.userID, new Date(), "test"));
    }

    public List<BountyMessage> getMessages(int bountyId) {
        List<BountyMessage> messageList = BountyService.retrieveMessages(bountyId);
        return messageList;
    }
}