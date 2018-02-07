package com.nyp.microdelivery.bounty;

import com.nyp.microdelivery.bounty.entity.BountyMessage;
import com.nyp.microdelivery.bounty.entity.BountyMessageDAO;
import com.nyp.microdelivery.user.Login;
import net.bootsfaces.component.inputText.InputText;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Liu Woon Kit
 */
@ViewScoped
@Named
public class MessageController  implements Serializable {
    @Inject
    private Login login;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void insertMessage(int bountyId) {
        //System.err.println("INSERTING MESSAGE");
        BountyMessageDAO.saveMessage(new BountyMessage(bountyId, login.getUser().getId(), new Date(), text));
    }

    public List<BountyMessage> getMessages(int bountyId) {
        List<BountyMessage> messageList = BountyMessageDAO.retrieveMessages(bountyId);
        return messageList;
    }

    public void onChangeText(AjaxBehaviorEvent event) {
        //System.err.println("NEWVALUE CHANGED");
        InputText inputText = (InputText)event.getComponent();
        setText(inputText.getValue().toString());
        //((UIInput) event.getComponent()).setLocalValueSet(false);
    }
}