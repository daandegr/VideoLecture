/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author Daan
 */
@Entity
@Table(name="LECTUREMESSAGE")
public class LectureMessage implements Serializable {
    
    @Id
    @GeneratedValue
    private int MESSAGEID;
    private int USERID;
    private Timestamp TS;
    private String MESSAGE;
    private int LECTUREID;

    public LectureMessage(int MESSAGEID, int USERID, Timestamp TS, String MESSAGE, int LECTUREID) {
        this.MESSAGEID = MESSAGEID;
        this.USERID = USERID;
        this.TS = TS;
        this.MESSAGE = MESSAGE;
        this.LECTUREID = LECTUREID;
    }

    

    public LectureMessage() {
    }

    public int getMESSAGEID() {
        return MESSAGEID;
    }

    public void setMESSAGEID(int MESSAGEID) {
        this.MESSAGEID = MESSAGEID;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public Timestamp getTS() {
        return TS;
    }

    public void setTS(Timestamp TS) {
        this.TS = TS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public int getLECTUREID() {
        return LECTUREID;
    }

    public void setLECTUREID(int LECTUREID) {
        this.LECTUREID = LECTUREID;
    }

    
    
    
}
