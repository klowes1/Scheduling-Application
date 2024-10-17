package project.c195_pa.model;

/** This class handles the interaction with Country Objects.
 * @author Kody Lowes */
public class Country {

    private int ctryID;
    private String ctry;

    /** Constructor for the Country Object.
     * @param ctryID the Country ID.
     * @param ctry the Country. */
    public Country(
            int ctryID,
            String ctry) {
        this.ctryID = ctryID;
        this.ctry = ctry;
    }

    /** This method will return the Country ID for a Country Object.
     * @return the Country ID. */
    public int getCtryID() { return ctryID; }

    /** This method will set the Country ID for a Country Object.
     * @param ctryID the new Country ID. */
    public void setCtryID(int ctryID) { this.ctryID = ctryID; }

    /** This method will return the Country for a Country Object.
     * @return the Country. */
    public String getCtry() { return ctry; }

    /** This method will set the Country for a Country Object.
     * @param ctry the new Country. */
    public void setCtry(String ctry) { this.ctry = ctry; }

    /** This method will return the Country for a Contact Object.
     * @return the Country. */
    @Override
    public String toString(){ return (ctry); }
}