package com.nyp.microdelivery.admin;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Named
@SessionScoped
public class UserController implements Serializable {
    private MsDbUtil msDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    private boolean rowSelected = false;
    private String theSearchUser;
    private List<UserSearch> userList;
    private UserSearch selectedUser = new UserSearch();
    private List<UserSearch> filteredUser;
    private enum listState{disableList,allList,enableList}
    private listState currState=listState.allList;

    public UserController() throws Exception {
        userList = new ArrayList<>();

        //msDbUtil = MsDbUtil.getInstance();
        theSearchUser = "";
    }

    public List<UserSearch> getFilteredUsers() {
        return filteredUser;
    }

    public void setFilteredUser (List<UserSearch> filteredUser) {
        this.filteredUser = filteredUser;
    }

    public List<UserSearch> getUserList() {
        return userList;
    }

    public List<UserSearch> getFilteredUser() {
        return filteredUser;
    }

    public void setUserList(List<UserSearch> userList) {
        this.userList = userList;
    }


    public UserSearch getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser (UserSearch selectedUser) {
        this.selectedUser = selectedUser;
        System.out.println("============ setSelectedUser " + this.selectedUser);
    }

    @PostConstruct
    public void loadEnabledUsers() {
        userList = MsDbUtil.getAllUsers();

        /*try {

            if (theSearchUser != null && theSearchUser.trim().length() > 0) {
                // search for users by username
                userList = msDbUtil.searchUsers(theSearchUser);
            }
            else {
                // get all students from database
                logger.log(Level.INFO," start loading");
                userList = MsDbUtil.getEnabledUsers();
                logger.info("############## = get enable user ==== " + userList.size() +  " " + userList.get(0));
            }

        } catch (Exception exc) {
            // send this to server logs
            logger.log(Level.SEVERE, "Error loading students", exc);

            // add error message for JSF page
            addErrorMessage(exc);
        }
        finally {
            // reset the search info
            theSearchUser = null;
        }*/
    }

    public void loadAllUsers(String type){
        if(type.equals("2")) {
            userList = MsDbUtil.getAllUsers();
            currState=listState.allList;
        }
        else if(type.equals("0")){
            userList=MsDbUtil.getEnabledUsers(0);
            currState=listState.enableList;
        }
        else{
            userList=MsDbUtil.getEnabledUsers(1);
            currState=listState.disableList;
        }
        filteredUser=userList;
        selectedUser=new UserSearch();

           /* if (theSearchUser != null && theSearchUser.trim().length() > 0) {
                // search for users by username
                userList = msDbUtil.searchUsers(theSearchUser);
            }
            else {*/
                // get all students from database




               // logger.info("############## = after getUsers ==== " + userList.size() +  " " + userList.get(0));

            //}


    }

    private void addErrorMessage(Exception exc) {
        FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void clearUser(){
        this.rowSelected = false;
        this.selectedUser = new UserSearch();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", new UserSearch());
        RequestContext.getCurrentInstance().reset("displayUserData");
    }

    public void onRowSelect(SelectEvent event){

        //System.out.println(((UserSearch) event.getObject()).getUsername());
        this.rowSelected = true;
        this.selectedUser = ((UserSearch) event.getObject());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put("user", this.selectedUser);
    }

    public void saveUser(){
        if (!rowSelected) {
            MsDbUtil.save(selectedUser);
            this.displayConfirmation("Added new user");
            clearUser();
        } else {
            MsDbUtil.save(selectedUser);
            this.displayConfirmation("Updated user");
        }

    }

    public void disableUser() {
        if (rowSelected == true) {
            MsDbUtil.disableUser(selectedUser);
            this.displayConfirmation("User disabled");
            if(currState==listState.enableList){
                userList.remove(selectedUser);
                if(filteredUser.contains(selectedUser))filteredUser.remove(selectedUser);
            }

        }
    }

    public void enableUser() {
        if (rowSelected == true) {
            MsDbUtil.enableUser(selectedUser);
            this.displayConfirmation("User enabled");
            if(currState==listState.disableList){
                userList.remove(selectedUser);
                if(filteredUser.contains(selectedUser))filteredUser.remove(selectedUser);
            }
        }
    }

    private void displayConfirmation(String message){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Success",  message) );
    }



}
