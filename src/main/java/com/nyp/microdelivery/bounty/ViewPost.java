package com.nyp.microdelivery.bounty;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.google.zxing.NotFoundException;
import com.nyp.microdelivery.bounty.entity.Bounty;
import com.nyp.microdelivery.bounty.entity.BountyDAO;
import com.nyp.microdelivery.user.Login;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
@Named
@ViewScoped
public class ViewPost implements Serializable {
    @Inject
    private Login login;
    private Bounty post;
    private BountyDAO bountyDAO = new BountyDAO();

    public Bounty getPost() {
        return post;
    }

    public void setPost(Bounty post) {
        this.post = post;
    }

    public void accept() {
        post.setCourierID(login.getUser().getId());
        post.setStatus("delivering");
        bountyDAO.saveOrUpdatePost(post);
    }

    public boolean checkIsOp() {
        if(login.getUser().getId() == post.getOpID()) {
            return true;
        }
        return false;
    }

    public boolean checkIsCourier() {
        return (login.getUser().getId() == post.getCourierID());
    }

    public void cashIn(String input){
        if(input.equals(post.getConfirmationCd())) {
            post.setStatus("completed");
            bountyDAO.saveOrUpdatePost(post);
        }
    }

    public List<Bounty> getUserPosts() {
        //System.err.println(Login.getSavedUser());
        return bountyDAO.getPostsByUser(login.getUser().getId());
    }

    public List<Bounty> getUserBountyHunting() {
        return bountyDAO.getBountiesHuntedByUser(login.getUser().getId());
    }

    public List<Bounty> getByPostalCd(String postalCd) {
        List<Bounty> list = bountyDAO.getPostsByPostalCd(login.getUser().getId(), postalCd);
        return list;
    }

    public void decodeQRCode(FileUploadEvent fileUploadEvent) throws IOException, NotFoundException {
        File file = new File("test");
        FileUtils.copyInputStreamToFile(fileUploadEvent.getFile().getInputstream(), file);
        if(QrcodeUtils.decodeQrcode(file).equals(this.post.getConfirmationCd())) {
            post.setStatus("completed");
            bountyDAO.saveOrUpdatePost(post);
            sendNotification("Successs!", "Bounty claimed!");
            return;
        }
        sendNotification("Failed to confirm delivery", "Please try again");
    }

    public String rejectCourier(ActionEvent event) {
        post.setCourierID(0);
        post.setStatus("open");
        bountyDAO.saveOrUpdatePost(post);
        sendNotification("Success", "Courier has been removed from your bounty");
        return "";
        //System.err.println("SUCCESS METHOD TRIGGERED");
    }

    public void sendNotification(String title, String details) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, details));
    }
}