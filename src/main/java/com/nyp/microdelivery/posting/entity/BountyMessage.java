package com.nyp.microdelivery.posting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="bounty_messages")
public class BountyMessage implements Serializable {
    @Id
    private int bountyID;

    @Id
    @Column
    private String personID;

    @Id
    @Column
    private Date date;

    @Column
    private String text;

    public BountyMessage() {
    }

    public BountyMessage(int bountyID, String personID, Date date, String text) {
        this.bountyID = bountyID;
        this.personID = personID;
        this.date = date;
        this.text = text;
    }

    public int getBountyID() {
        return bountyID;
    }

    public void setBountyID(int bountyID) {
        this.bountyID = bountyID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
