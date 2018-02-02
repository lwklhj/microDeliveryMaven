package com.nyp.microdelivery.posting;

import com.github.binarywang.utils.qrcode.QrcodeUtils;
import com.google.zxing.NotFoundException;
import com.nyp.microdelivery.posting.entity.BountyService;
import com.nyp.microdelivery.posting.entity.User;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ViewPost implements Serializable {
    private Bounty post;
    private BountyService bountyService = new BountyService();

    public Bounty getPost() {
        return post;
    }

    public void setPost(Bounty post) {
        this.post = post;
    }

    public void accept() {
        post.setCourierID(User.userID);
        post.setStatus("delivering");
        bountyService.saveOrUpdatePost(post);
    }

    public boolean checkIsOp() {
        if(User.userID.equals(post.getOpID())) {
            return true;
        }
        return false;
    }

    public boolean checkIsCourier() {
        return User.userID.equals(post.getCourierID());
    }

    public void cashIn(String input){
        if(input.equals(post.getConfirmationCd())) {
            post.setStatus("completed");
            bountyService.saveOrUpdatePost(post);
        }
    }

    public List<Bounty> getUserPosts() {
        return bountyService.getPostsByUser(User.userID);
    }

    public List<Bounty> getUserBountyHunting() {
        return bountyService.getBountiesHuntedByUser(User.userID);
    }

    public List<Bounty> getByPostalCd(String postalCd) {
        List<Bounty> list = bountyService.getPostsByPostalCd(postalCd);
        return list;
    }

    public void decodeQRCode(FileUploadEvent fileUploadEvent) throws IOException, NotFoundException {
        File file = new File("test");
        FileUtils.copyInputStreamToFile(fileUploadEvent.getFile().getInputstream(), file);
        if(QrcodeUtils.decodeQrcode(file).equals(this.post.getConfirmationCd())) {
            post.setStatus("completed");
            bountyService.saveOrUpdatePost(post);
        }
        FacesMessage message = new FacesMessage("Succesful", " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}