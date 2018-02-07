package com.nyp.microdelivery.bounty;

import com.nyp.microdelivery.bounty.entity.Bounty;
import com.nyp.microdelivery.bounty.entity.BountyDAO;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Liu Woon Kit
 */
@Named
@RequestScoped
public class BountyConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null || s.isEmpty()) {
            return new Bounty();
        }
        return new BountyDAO().getPostById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null) {
            return null;
        }
        return ((Bounty)o).getId() + "";
    }
}