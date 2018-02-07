package com.nyp.microdelivery.admin;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Named
@Entity
@Table(name = "user")
public class UserSearch {


    @Column(name="email")
    private String email;
    @Column(name="address")
    private String addr;
    @Column(name="phoneNo")
    private String phoneNo;
    @Column(name="disable")
    private int disable = 0;
    @Column(name = "password")
    private String password;

    @Id
    private int id;
    @Column(name ="userName")
    private String username;

    public UserSearch() {
    }

    public UserSearch(String username, String email, String addr, String phoneNo, int disable) {
        this.username = username;

        this.email = email;
        this.addr = addr;
        this.phoneNo = phoneNo;
        this.disable = disable;
    }

    public UserSearch(String username, String password, String email, String addr, String phoneNo, int disable) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.addr = addr;
        this.phoneNo = phoneNo;
        this.disable = disable;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getDisable() {
        return disable;
    }

    public void setDisable(int disable) {
        this.disable = disable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
