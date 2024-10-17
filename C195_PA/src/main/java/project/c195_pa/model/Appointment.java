package project.c195_pa.model;

import java.sql.Timestamp;

/** This class handles the interaction with Appointment Objects.
 * @author Kody Lowes */
public class Appointment {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private int contact;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int cstmID;
    private int usrID;
    private Timestamp crtDate;
    private String crtBy;
    private Timestamp lstUpdt;
    private String lsdtUpdtBy;

    /** Constructor for the Appointment Object.
     * @param apptID the Appointment ID.
     * @param title the Title.
     * @param description the Description.
     * @param location the Location.
     * @param contact the Contact.
     * @param type the Type.
     * @param start the Start.
     * @param end the End.
     * @param cstmID the Customer ID.
     * @param usrID the User ID.
     * @param crtDate the Create Date.
     * @param crtBy the Created By.
     * @param lstUpdt the Last Updated.
     * @param lsdtUpdtBy the Last Updated By. */
    public Appointment(
            int apptID,
            String title,
            String description,
            String location,
            int contact,
            String type,
            Timestamp start,
            Timestamp end,
            int cstmID,
            int usrID,
            Timestamp crtDate,
            String crtBy,
            Timestamp lstUpdt,
            String lsdtUpdtBy) {
        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.cstmID = cstmID;
        this.usrID = usrID;
    }

    /** This method will return the Appointment ID for an Appointment Object.
     * @return the Appointment ID. */
    public int getApptID() {
        return apptID;
    }

    /** This method will set the Appointment ID for an Appointment Object.
     * @param apptID the new Appointment ID. */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /** This method will get the Title for an Appointment Object.
     * @return the Title. */
    public String getTitle() {
        return title;
    }

    /** This method will set the Title for an Appointment Object.
     * @param title the new Title. */
    public void setTitle(String title) {
        this.title = title;
    }

    /** This method will return the Description for an Appointment Object.
     * @return the Description. */
    public String getDescription() {
        return description;
    }

    /** This method will set the Description for an Appointment Object.
     * @param description the new Description. */
    public void setDescription(String description) {
        this.description = description;
    }

    /** This method will return the Location for an Appointment Object.
     * @return the Location. */
    public String getLocation() { return location; }

    /** This method will set the Location for an Appointment Object.
     * @param location the new Location. */
    public void setLocation(String location) { this.location = location; }

    /** This method will return the Contact for an Appointment Object.
     * @return the Contact. */
    public int getContact() { return contact; }

    /** This method will set the Contact for an Appointment Object.
     * @param contact the new Contact. */
    public  void setContact(int contact) { this.contact = contact; }
    /** This method will return the Type for an Appointment Object.
     * @return the Appointment. */
    public String getType() { return type; }

    /** This method will set the Type for an Appointment Object.
     * @param type the new Type. */
    public void setType(String type) { this.type = type; }

    /** This method will return the Start for an Appointment Object.
     * @return the Start. */
    public Timestamp getStart() { return start; }

    /** This method will set the Start for an Appointment Object.
     * @param start the new Start. */
    public void setStart(Timestamp start) { this.start = start; }

    /** This method will return the End for an Appointment Object.
     * @return the End. */
    public Timestamp getEnd() { return end; }

    /** This method will set the End for an Appointment Object.
     * @param end the new End. */
    public void setEnd(Timestamp end) { this.end = end; }

    /** This method will return the Customer ID for an Appointment Object.
     * @return the Customer ID. */
    public int getCstmID() { return cstmID; }

    /** This method will set the Customer ID for an Appointment Object.
     * @param cstmID the new Customer ID. */
    public void setCstmID(int cstmID) { this.cstmID = cstmID; }

    /** This method will return the User ID for an Appointment Object.
     * @return the User ID. */
    public int getUsrID() { return usrID; }

    /** This method will set the User ID for an Appointment Object.
     * @param usrID the new User ID. */
    public void setUsrID(int usrID) { this.usrID = usrID; }

    /** This method will return the Create Date for an Appointment Object.
     * @return the Create Date. */
    public Timestamp getCrtDate() { return crtDate; }

    /** This method will set the Create Date for an Appointment Object.
     * @param crtDate the new Create Date. */
    public void setCrtDate(Timestamp crtDate) { this.crtDate = crtDate; }

    /** This method will return the Created By for an Appointment Object.
     * @return the Created By. */
    public String getCrtBy() { return crtBy; }

    /** This method will set the Created By for an Appointment Object.
     * @param crtBy the new Created By. */
    public void setCrtBy(String crtBy) { this.crtBy = crtBy; }

    /** This method will return the Last Updated for an Appointment Object.
     * @return the Last Updated. */
    public Timestamp getLstUpdt() { return lstUpdt; }

    /** This method will set the Last Updated for an Appointment Object.
     * @param lstUpdt the new Last Updated. */
    public void setLstUpdt(Timestamp lstUpdt) { this.lstUpdt = lstUpdt; }

    /** This method will return the Last Updated By for an Appointment Object.
     * @return the Last Updated By. */
    public String getLsdtUpdtBy() { return lsdtUpdtBy; }

    /** This method will set the Last Updated By for an Appointment Object.
     * @param lsdtUpdtBy the new Last Updated By. */
    public void setLsdtUpdtBy(String lsdtUpdtBy) { this.lsdtUpdtBy = lsdtUpdtBy; }
}