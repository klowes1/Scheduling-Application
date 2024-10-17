package project.c195_pa.model;

import java.sql.Timestamp;

/** This class handles the interaction with Customer Objects.
 * @author Kody Lowes */
public class Customer {

    private int cstmrID;
    private String cstmrName;
    private String address;
    private String pstCode;
    private String phone;
    private Timestamp created;
    private String createdBy;
    private Timestamp lstUpdt;
    private String updtBy;
    private int dvsID;
    private String ctry;

    /** Constructor for the Customer Object.
     * @param cstmrID the Customer ID.
     * @param cstmrName the Customer Name.
     * @param address the Address.
     * @param pstCode the Postal Code.
     * @param phone the Phone.
     * @param created the Created Date.
     * @param createdBy the Created By.
     * @param lstUpdt the Last Updated Date.
     * @param updtBy the Last Updated By.
     * @param dvsID the Division ID.
     * @param ctry the Country. */
    public Customer(
            int cstmrID,
            String cstmrName,
            String address,
            String pstCode,
            String phone,
            Timestamp created,
            String createdBy,
            Timestamp lstUpdt,
            String updtBy,
            int dvsID,
            String ctry) {
        this.cstmrID = cstmrID;
        this.cstmrName = cstmrName;
        this.address = address;
        this.pstCode = pstCode;
        this.phone = phone;
        this.created = created;
        this.createdBy = createdBy;
        this.lstUpdt = lstUpdt;
        this.updtBy = updtBy;
        this.dvsID = dvsID;
        this.ctry = ctry;
    }

    /** This method will return the Customer ID for a Customer Object.
     * @return the Customer ID. */
    public int getCstmrID() { return cstmrID; }

    /** This method will return the Customer Name for a Customer Object.
     * @return the Customer Name. */
    public String getCstmrName() { return cstmrName; }

    /** This method will return the Address for a Customer Object.
     * @return the Address. */
    public String getAddress() { return address; }

    /** This method will return the Postal Code for a Customer Object.
     * @return the Postal Code. */
    public String getPstCode() { return pstCode; }

    /** This method will return the Phone for a Customer Object.
     * @return the Phone. */
    public String getPhone() { return phone; }

    /** This method will return the Created Date for a Customer Object.
     * @return the Created Date. */
    public Timestamp getCreated() { return created; }

    /** This method will return the Created By for a Customer Object.
     * @return the Created By. */
    public String getCreatedBy() { return createdBy; }

    /** This method will return the Last Updated for a Customer Object.
     * @return the Last Updated. */
    public Timestamp getLstUpdt() { return lstUpdt; }

    /** This method will return the Updated By for a Customer Object.
     * @return the Updated By. */
    public String getUpdtBy() { return updtBy; }

    /** This method will return the Division ID for a Customer Object.
     * @return the Division ID. */
    public int getDvsID() { return dvsID; }

    /** This method will return the Country for a Customer Object.
     * @return the Country. */
    public String getCtry() { return ctry; }

    /** This method will set the Customer ID for a Customer Object.
     * @param cstmrID the new Customer ID. */
    public void setCstmrID(int cstmrID) { this.cstmrID = cstmrID; }

    /** This method will set the Customer Name for a Customer Object.
     * @param cstmrName the new Customer Name. */
    public void setCstmrName(String cstmrName) { this.cstmrName = cstmrName; }

    /** This method will set the Address for a Customer Object.
     * @param address the new Address. */
    public void setAddress(String address) { this.address = address; }

    /** This method will set the Postal Code for a Customer Object.
     * @param pstCode the new Postal Code. */
    public void setPstCode(String pstCode) { this.pstCode = pstCode; }

    /** This method will set the Phone for a Customer Object.
     * @param phone the new Phone. */
    public void setPhone(String phone) { this.phone = phone; }

    /** This method will set the Created Date for a Customer Object.
     * @param created the new Created Date. */
    public void setCreated(Timestamp created) { this.created = created; }

    /** This method will set the Created By for a Customer Object.
     * @param createdBy the new Created By. */
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    /** This method will set the Last Updated for a Customer Object.
     * @param lstUpdt the new Last Updated. */
    public void setLstUpdt(Timestamp lstUpdt) { this.lstUpdt = lstUpdt; }

    /** This method will set the Updated for a Customer Object.
     * @param updtBy the new Updated By. */
    public void setUpdtBy(String updtBy) { this.updtBy = updtBy; }

    /** This method will set the Division ID for a Customer Object.
     * @param dvsID the new Division ID. */
    public void setDvsID(int dvsID) { this.dvsID = dvsID; }

    /** This method will set the Country for a Customer Object.
     * @param ctry the new Country. */
    public void setCtry(String ctry) { this.ctry = ctry; }
}