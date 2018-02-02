package com.nyp.microdelivery.posting;

import com.nyp.microdelivery.posting.entity.BountyService;
import com.nyp.microdelivery.posting.entity.User;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class BountyCreate implements Serializable {
    private Bounty bounty;
    private BountyService bountyService = new BountyService();

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        this.bounty = bounty;
    }

    public boolean saveOrUpdate() {
        if(bounty.getId() > 0 && bounty.getOpID() != User.userID) {
            return false;
        }
        bounty.setOpID(User.userID);
        bountyService.saveOrUpdatePost(bounty);
        return true;
    }

    public boolean deleteBounty() {
        if(bounty.getId() <= 0 && bounty.getOpID() != User.userID) {
            return false;
        }
        bountyService.deleteBounty(bounty);
        return true;
    }

    public void uploadImage(FileUploadEvent event) {
        try {
            byte[] image = IOUtils.toByteArray(event.getFile().getInputstream());
            //post.getImages().add(image);
            //bounty = new Bounty();
            bounty.addImage(image);

        } catch (IOException ex) {
            Logger.getLogger(Bounty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeImage(ActionEvent event) {
        System.err.println("RUNNING");
        //bounty.removeImage(img);
    }
}