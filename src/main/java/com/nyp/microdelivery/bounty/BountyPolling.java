package com.nyp.microdelivery.bounty;

import com.nyp.microdelivery.bounty.entity.Bounty;
import com.nyp.microdelivery.bounty.entity.BountyDAO;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
@Named
@ViewScoped
public class BountyPolling implements Serializable {
    @Inject
    private ViewPost viewPost;

    private List<Bounty> oldList;
    private List<Bounty> newList;

    public void checkBountyAccepted() {
        newList = viewPost.getUserPosts();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getMessages().remove();

        newList = viewPost.getUserPosts();
        if(newList.size() != oldList.size()) {
            oldList = newList;
        }

        for(int i = 0; i < newList.size(); i++) {
            if(newList.get(i).getCourierID() > 0 && oldList.get(i).getCourierID() == 0) {
                context.addMessage(null, new FacesMessage(newList.get(i).getTitle(), "Your bounty has been accepted"));
            }
        }
        oldList = newList;
    }
}
