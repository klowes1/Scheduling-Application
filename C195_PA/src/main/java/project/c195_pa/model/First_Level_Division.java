package project.c195_pa.model;

/** This method handles the interaction with the First_Level_Division Objects.
 * @author Kody Lowes */
public class First_Level_Division {

    private int dvsID;
    private String dvs;
    private int ctryIDF;

    /** Constructor for the First_Level_Division Object.
     * @param dvsID the Division ID.
     * @param dvs the Division.
     * @param ctryID the Country ID. */
    public First_Level_Division(
            int dvsID,
            String dvs,
            int ctryID) {
        this.dvsID = dvsID;
        this.dvs = dvs;
        this.ctryIDF = ctryID;
    }

    /** This method will return the Division ID for a First_Level_Division Object.
     * @return the Division ID. */
    public int getDvsID() { return dvsID; }

    /** This method will return the Division for a First_Level_Division Object.
     * @return the Division. */
    public String getDvs() { return dvs; }

    /** This method will return the Country ID for a First_Level_Division Object.
     * @return the Country ID. */
    public int getCtryIDF() { return ctryIDF; }

    /** This method will set the Division ID for a First_Level_Division Object.
     * @param dvsID the new Division ID. */
    public void setDvsID(int dvsID) { this.dvsID = dvsID; }

    /** This method will set the Division for a First_Level_Division Object.
     * @param dvs the new Division. */
    public void setDvs(String dvs) { this.dvs = dvs; }

    /** This method will set the Country ID for a First_Level_Division Object.
     * @param ctryID the new Country ID. */
    public void setCtryID(int ctryID) { this.ctryIDF = ctryID; }

    /** This method will return the Division for a First_Level_Division Object.
     * @return the Division. */
    @Override
    public String toString(){ return (dvs); }
}