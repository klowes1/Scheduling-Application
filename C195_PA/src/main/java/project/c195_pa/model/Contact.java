package project.c195_pa.model;

/** This class handles the interaction with Contact Objects.
 * @author Kody Lowes */
public class Contact {

    private int cntcID;
    private String cntcName;
    private String cntcEmail;

    /** Constructor for the Contact Object.
     * @param cntcID the Contact ID.
     * @param cntcName the Contact Name.
     * @param cntcEmail the Contact Email. */
    public Contact(
            int cntcID,
            String cntcName,
            String cntcEmail) {
        this.cntcID = cntcID;
        this.cntcName = cntcName;
        this.cntcEmail = cntcEmail;
    }

    /** This method will return the Contact ID for a Contact Object.
     * @return the Contact ID. */
    public int getCntcID() { return cntcID; }

    /** This method will set the Contact ID for a Contact Object.
     * @param cntcID the new Contact ID. */
    public void setCntcID(int cntcID) { this.cntcID = cntcID; }

    /** This method will return the Contact Name for a Contact Object.
     * @return the Contact Name. */
    public String getCntcName() { return cntcName; }

    /** This method will set the Contact Name for a Contact Object.
     * @param cntcName the new Contact Name. */
    public void setCntcName(String cntcName) { this.cntcName = cntcName; }

    /** This method will return the Contact Email for a Contact Object.
     * @return the Contact Email. */
    public String getCntcEmail() { return cntcEmail; }

    /** This method will set the Contact Email for a Contact Email.
     * @param cntcEmail the new Contact Email. */
    public void setCntcEmail(String cntcEmail) { this.cntcEmail = cntcEmail; }

    /** This method will return the Contact Name for a Contact Object.
     * @return the Contact Name. */
    @Override
    public String toString(){ return (cntcName); }
}
