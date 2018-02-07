package com.nyp.microdelivery.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validateUser")
public class validator implements Validator{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        boolean userexist = UserDao.checkDuplicateLogin((String) o);

                System.out.print((String) o);
                if(userexist){
            throw new ValidatorException(new FacesMessage("User exist"));
                }
    }
}
