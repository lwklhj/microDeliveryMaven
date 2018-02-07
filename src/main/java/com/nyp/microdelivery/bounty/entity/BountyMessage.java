package com.nyp.microdelivery.bounty.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Liu Woon Kit
 */
@Entity
@Table(name="bounty_messages")
public class BountyMessage implements Serializable {
    @Id
    private int bountyID;

    @Id
    @Column
    private int personID;

    @Id
    @Column
    private Date date;

    @Column
    private String text;

    public BountyMessage() {
    }

    public BountyMessage(int bountyID, int personID, Date date, String text) {
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

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
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
