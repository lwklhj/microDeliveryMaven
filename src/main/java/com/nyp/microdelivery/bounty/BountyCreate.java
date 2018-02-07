package com.nyp.microdelivery.bounty;

import com.nyp.microdelivery.bounty.entity.Bounty;
import com.nyp.microdelivery.bounty.entity.BountyDAO;
import com.nyp.microdelivery.user.Login;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liu Woon Kit
 */
@Named
@ViewScoped
public class BountyCreate implements Serializable {
    @Inject
    private Login login;

    private Bounty bounty;
    private BountyDAO bountyDAO = new BountyDAO();

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        this.bounty = bounty;
    }

    public String saveOrUpdate() {
        if(bounty.getId() > 0 && bounty.getOpID() != login.getUser().getId()) {
            return null;
        }
        bounty.setOpID(login.getUser().getId());
        bountyDAO.saveOrUpdatePost(bounty);
        // Me use flash scope, cause using session scope for CDI bean feels like setting everything to global
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("confirmCd", bounty.getConfirmationCd());
        return "bounty_create_success";
    }

    public boolean deleteBounty() {
        if(bounty.getId() <= 0 && bounty.getOpID() != login.getUser().getId()) {
            return false;
        }
        bountyDAO.deleteBounty(bounty);
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

    public void removeImage(byte[] image) {
        bounty.removeImage(image);
    }
}