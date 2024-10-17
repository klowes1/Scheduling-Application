package project.c195_pa.model;

/** This class handles the interaction with Report Result Objects.
 * @author Kody Lowes */
public class reportResult {

    private String month;
    private String type;
    private int count;

    /** Constructor for the reportResult Object.
     * @param month the Month for the Appointment Date.
     * @param type the Appointment Type.
     * @param count the Number of Appointment. */
    public reportResult(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /** This method will return the Month for a reportResult Object.
     * @return the Month. */
    public String getMonth() { return month; }

    /** This method will set the Month for a reportResult Object.
     * @param month the new Month. */
    public void setMonth(String month) { this.month = month; }

    /** This method will return the Type for a reportResult Object.
     * @return the Type. */
    public String getType() { return type; }

    /** This method will return the Month for a reportResult Object.
     * @param type the new Type. */
    public void setType(String type) { this.type = type; }

    /** This method will return the Number of Appointments for a reportResult Object.
     * @return the Number of Appointments. */
    public int getCount() { return count; }

    /** This method will return the Month for a reportResult Object.
     * @param count the new Number of Appointments. */
    public void setCount(int count) { this.count = count; }
}
