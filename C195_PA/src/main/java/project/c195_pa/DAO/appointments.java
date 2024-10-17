package project.c195_pa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import project.c195_pa.helper.JDBC;
import project.c195_pa.model.Appointment;
import project.c195_pa.model.reportResult;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static project.c195_pa.schedulingApplication.rbL;

/** This class handles the interaction of Appointment Data between the Application and the DataBase.
 * @author Kody Lowes */
public abstract class appointments {

    /** This method is used to create a new appointment using all the required values(Start and End times are converted to UTC), which is added to the DataBase.
     * @param apptID the Appointment ID that will be used to create the Appointment.
     * @param title the Title that will be used to create the Appointment.
     * @param description the Description that will be used to create the Appointment.
     * @param location the Location that will be used to create the Appointment.
     * @param contact the Contact ID that will be used to create the Appointment.
     * @param type the Type that will be used to create the Appointment.
     * @param start the Start Date and Time(Converted to UTC) that will be used to create the Appointment.
     * @param end the End Date and Time(Converted to UTC) that will be used to create the Appointment.
     * @param cstmID the Customer ID that will be used to create the Appointment.
     * @param usrID the User ID that will be used to create the Appointment.
     * @param crtDate the Created Date TimeStamp that will be used to show when the Appointment was Created.
     * @param crtBy the Created By value that will show which user created the Appointment.
     * @param lstUpdt the Last Updated TimeStamp that will be used to show when the Appointment was Last Updated.
     * @param lsdtUpdtBy the Last Updated By value that will be used to show which user updated the Appointment Last. */
    public static void addAppointment (
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
            String lsdtUpdtBy)
            throws SQLException {
        String sql = "INSERT INTO appointments" +
                "(Appointment_ID," +
                "Title," +
                "Description," +
                "Location," +
                "Type," +
                "Start," +
                "End," +
                "Create_Date," +
                "Created_By," +
                "Last_Update," +
                "Last_Updated_By," +
                "Customer_ID," +
                "User_ID," +
                "Contact_ID)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        Instant startI = start.toInstant();
        ZonedDateTime startUTC = startI.atZone(ZoneId.systemDefault());

        Instant endI = end.toInstant();
        ZonedDateTime endUTC = endI.atZone(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ps.setInt(1, apptID);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, Timestamp.valueOf(startUTC.format(formatter)));
        ps.setTimestamp(7, Timestamp.valueOf(endUTC.format(formatter)));
        ps.setTimestamp(8, crtDate);
        ps.setString(9, crtBy);
        ps.setTimestamp(10, lstUpdt);
        ps.setString(11, lsdtUpdtBy);
        ps.setInt(12, cstmID);
        ps.setInt(13, usrID);
        ps.setInt(14, contact);

        if (checkApptTime(startI, endI) && checkApptOverlap(startI, endI, apptID)) {
            ps.executeUpdate();
        }
    }

    /** This method is used to update an appointment using all the required values(Start and End times are converted to UTC), which is updated to the DataBase.
     * @param apptID the Appointment ID that will be used to update the Appointment.
     * @param title the Title that will be used to update the Appointment.
     * @param description the Description that will be used to update the Appointment.
     * @param location the Location that will be used to update the Appointment.
     * @param contact the Contact ID that will be used to update the Appointment.
     * @param type the Type that will be used to update the Appointment.
     * @param start the Start Date and Time(Converted to UTC) that will be used to update the Appointment.
     * @param end the End Date and Time(Converted to UTC) that will be used to update the Appointment.
     * @param cstmID the Customer ID that will be used to update the Appointment.
     * @param usrID the User ID that will be used to update the Appointment.
     * @param lstUpdt the Last Updated TimeStamp that will be used to show when the Appointment was Last Updated.
     * @param lsdtUpdtBy the Last Updated By value that will be used to show which user updated the Appointment Last. */
    public static void updateAppointment(
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
            Timestamp lstUpdt,
            String lsdtUpdtBy)
        throws SQLException {
        String sql = "UPDATE appointments SET " +
                "Title = ?," +
                "Description = ?," +
                "Location = ?," +
                "Type = ?," +
                "Start = ?," +
                "End = ?," +
                "Last_Update = ?," +
                "Last_Updated_By = ?," +
                "Customer_ID = ?," +
                "User_ID = ?," +
                "Contact_ID = ?" +
                " WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        Instant startI = start.toInstant();
        ZonedDateTime startUTC = startI.atZone(ZoneId.systemDefault());

        Instant endI = end.toInstant();
        ZonedDateTime endUTC = endI.atZone(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startUTC.format(formatter)));
        ps.setTimestamp(6, Timestamp.valueOf(endUTC.format(formatter)));
        ps.setTimestamp(7, lstUpdt);
        ps.setString(8, lsdtUpdtBy);
        ps.setInt(9, cstmID);
        ps.setInt(10, usrID);
        ps.setInt(11, contact);
        ps.setInt(12, apptID);

        if (checkApptTime(startI, endI) && checkApptOverlap(startI, endI, apptID)) {
            ps.executeUpdate();
        }
    }

    /** This method will retrieve all the Appointments from the DataBase and convert all the Appointments to an Appointments Object which is used to populate an ObservableList. Also, Start and End times are converted to the System Default TimeZone.
     * @return the ObservableList with all the Appointments. */
    public static ObservableList<Appointment> getAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments;
        try {
            allAppointments = FXCollections.observableArrayList();
            String sql = "SELECT * FROM APPOINTMENTS";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contact = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                String startS = rs.getString("Start");
                String endS = rs.getString("End");
                int cstmID = rs.getInt("Customer_ID");
                int usrID = rs.getInt("User_ID");
                Timestamp crtDate = rs.getTimestamp("Create_Date");
                String crtdBy = rs.getString("Created_By");
                Timestamp lstUpdt = rs.getTimestamp("Last_Update");
                String lstUpdtBy = rs.getString("Last_Updated_By");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

                LocalDateTime startLTD = LocalDateTime.parse(startS, formatter);
                Instant startI = startLTD.atZone(ZoneId.of("Etc/UTC")).toInstant();
                ZonedDateTime startLocale = startI.atZone(ZoneId.systemDefault());

                LocalDateTime endLTD = LocalDateTime.parse(endS, formatter);
                Instant endI = endLTD.atZone(ZoneId.of("Etc/UTC")).toInstant();
                ZonedDateTime endLocale = endI.atZone(ZoneId.systemDefault());

                Timestamp start = Timestamp.valueOf(startLocale.toLocalDateTime());
                Timestamp end = Timestamp.valueOf(endLocale.toLocalDateTime());

                Appointment appointment = new Appointment(apptID, title, description, location, contact, type, start, end, cstmID, usrID, crtDate, crtdBy, lstUpdt, lstUpdtBy);
                allAppointments.add(appointment);
            }
            return allAppointments;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method is used to retrieve all the Appointments from the DataBase on the condition that they match the value of the WHERE statement, then populate an ObservableList with the results. Also, Start and End times are converted to the System Default TimeZone.
     * @param where the WHERE statement that will be used to filter the Appointments retrieved from the DataBase.
     * @return the ObservableList with the filtered Appointments. */
    public static ObservableList<Appointment> getAppointmentsWhere(String where) {
        ObservableList<Appointment> allAppointments;
        try {
            allAppointments = FXCollections.observableArrayList();
            String sql = "SELECT * FROM APPOINTMENTS WHERE " + where;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int apptID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contact = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                String startS = rs.getString("Start");
                String endS = rs.getString("End");
                int cstmID = rs.getInt("Customer_ID");
                int usrID = rs.getInt("User_ID");
                Timestamp crtDate = rs.getTimestamp("Create_Date");
                String crtdBy = rs.getString("Created_By");
                Timestamp lstUpdt = rs.getTimestamp("Last_Update");
                String lstUpdtBy = rs.getString("Last_Updated_By");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

                LocalDateTime startLTD = LocalDateTime.parse(startS, formatter);
                Instant startI = startLTD.atZone(ZoneId.of("Etc/UTC")).toInstant();
                ZonedDateTime startLocale = startI.atZone(ZoneId.systemDefault());

                LocalDateTime endLTD = LocalDateTime.parse(endS, formatter);
                Instant endI = endLTD.atZone(ZoneId.of("Etc/UTC")).toInstant();
                ZonedDateTime endLocale = endI.atZone(ZoneId.systemDefault());

                Timestamp start = Timestamp.valueOf(startLocale.toLocalDateTime());
                Timestamp end = Timestamp.valueOf(endLocale.toLocalDateTime());

                Appointment appointment = new Appointment(apptID, title, description, location, contact, type, start, end, cstmID, usrID, crtDate, crtdBy, lstUpdt, lstUpdtBy);
                allAppointments.add(appointment);
            }
            return allAppointments;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method will delete an Appointment from the DataBase using the Appointment ID.
     * @param apptID the Appointment ID that will be used to determine which Appointment will be deleted. */
    public static void deleteAppt (int apptID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptID);
        ps.executeUpdate();
    }

    /** This method will check that the Appointment Start and End(both times are converted to Eastern Time) times are within the Office Hours and that the times have appropriate values.
     * @param startTime the value of the Start Time that will be checked.
     * @param endTime the value of the End Time that will be checked.
     * @return will return True if the Appointment Start and End times are within the required times and will return False if the times are not within the required times. */
    public static boolean checkApptTime(Instant startTime, Instant endTime) {
        ZonedDateTime startT = startTime.atZone(ZoneId.systemDefault());
        ZonedDateTime endT = endTime.atZone(ZoneId.systemDefault());
        if (!startT.getZone().getId().equals("America/New_York")) {
            startT = startTime.atZone(ZoneId.of("America/New_York"));
            endT = endTime.atZone(ZoneId.of("America/New_York"));
        }
        if ((startT.getHour() >= 8 && startT.getHour() < 22) && (((endT.getHour() < 22) || ((endT.getHour() == 22) && (endT.getMinute() == 0)))) && (startT.isBefore(endT)) && (!startT.isEqual(endT))) {
            return true;
        } else if (startT.getHour() == endT.getHour() && (startT.getMinute() == endT.getMinute()) && (startT.getDayOfMonth() == endT.getDayOfMonth()) && startT.getMonthValue() == endT.getMonthValue()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("ApptTm"));
            alert.showAndWait();
            return false;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("OffHrs"));
            alert.showAndWait();
            return false;
        }
    }

    /** This method will ensure that the Start and End(both times are converted to Eastern Time) times do not overlap any other pre-existing appointment times(will not compare the Appointment's time to itself to prevent invalid error being thrown).
     * @param startTime the value of the Start Time that will be checked.
     * @param endTime the value of the End Time that will be checked.
     * @param apptID used to prevent an invalid error being thrown due to the Appointment's time being compared to itself.
     * @return will return False if the Appointment duration overlaps another Appointment and returns True if the Appointment does not overlap any other Appointments. */
    public static boolean checkApptOverlap(Instant startTime, Instant endTime, int apptID) throws SQLException {
        ObservableList<Appointment> apptList = getAppointments();
        ZonedDateTime startT = startTime.atZone(ZoneId.systemDefault());
        ZonedDateTime endT = endTime.atZone(ZoneId.systemDefault());
        boolean r = true;
        int i = 0;
        while (i != apptList.size()) {
            ZonedDateTime tempStart = ZonedDateTime.of(apptList.get(i).getStart().toLocalDateTime(), ZoneOffset.systemDefault());
            ZonedDateTime tempEnd = ZonedDateTime.of(apptList.get(i).getEnd().toLocalDateTime(), ZoneOffset.systemDefault());
            int tempID = apptList.get(i).getApptID();
            if (((startT.isAfter(tempStart) && startT.isBefore(tempEnd)) || (endT.isAfter(tempStart) && endT.isBefore(tempEnd)) || (startT.isEqual(tempStart)) || (endT.isEqual(tempEnd)) || (startT.isBefore(tempStart) && endT.isAfter(tempEnd))) && (apptID != tempID)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rbL.getString("ApptTmO"));
                alert.showAndWait();
                r = false;
                return r;
            }
            i ++;
        }
        return r;
    }

    /** This method will check if there are any appointments within 15 minutes from logging in and display an Informative alert showing the upcoming appointment. If there are not any upcoming appointments and informative alert will be shown. */
    public static void checkApptAlert() throws SQLException {
        ObservableList<Appointment> apptList = getAppointments();
        LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.systemDefault());
        int j = 0;
        int i = 0;
        while (i != apptList.size()) {
            LocalDateTime tempStart = apptList.get(i).getStart().toLocalDateTime();
            Appointment appointment = apptList.get(i);
            int timeTil = (int) ChronoUnit.MINUTES.between(currentTime, tempStart);
            if ((timeTil <= 15) && (timeTil > 0)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(rbL.getString("Appointment") + " " + appointment.getApptID() + ", " + rbL.getString("ChkAlert3") + " " + appointment.getStart() + ", " + rbL.getString("ChkAlert1") + " " + timeTil + " " + rbL.getString("ChkAlert2") + ".");
                j += 1;
                alert.showAndWait();
            }
            i ++;
        }
        if (j == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rbL.getString("noApptAlert"));
            alert.showAndWait();
        }
    }

    /** This method will get all the appointments for the Appointments by Type and Month Report and return them in an ObservableList of reportResult Objects.
     * @param mainArg this is used to define what is being selected and if there is any other parameters that need to be added to the retrieval statement.
     * @return will return the Observable List of reportResult Objects with the results from the SQL Query. */
    public static ObservableList<reportResult> getAppointmentsReport(String mainArg) {
        ObservableList<reportResult> apptReport;
        try {
            apptReport = FXCollections.observableArrayList();
            String sql = "SELECT " + mainArg;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Month month = Month.of(Integer.parseInt(rs.getString(1)));
                String type = rs.getString(2);
                int count = rs.getInt(3);

                reportResult result = new reportResult(String.valueOf(month), type, count);
                apptReport.add(result);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return apptReport;
    }
}