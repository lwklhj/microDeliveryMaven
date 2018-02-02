package com.nyp.microdelivery.posting;

import com.nyp.microdelivery.posting.entity.BountyService;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class BountyConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null || s.isEmpty()) {
            return new Bounty();
        }
        return new BountyService().getPostById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null) {
            return null;
        }
        return ((Bounty)o).getId() + "";
    }
}