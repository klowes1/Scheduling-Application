package project.c195_pa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import project.c195_pa.helper.JDBC;
import project.c195_pa.model.Country;
import project.c195_pa.model.Customer;
import project.c195_pa.model.First_Level_Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/** This class handles the interaction of Customer Data between the Application and the DataBase.
 * @author Kody Lowes */
public abstract class customers {

    /** This method is used to create a new customer using all the required values, which is added to the DataBase.
     * @param _cstmrID the Customer ID that will be used to create the Customer.
     * @param _cstmrName the Customer Name that will be used to create the Customer.
     * @param _address the Address that will be used to create the Customer.
     * @param _pstCode the Postal Code that will be used to create the Customer.
     * @param _phone the Phone Number that will be used to create the Customer.
     * @param _created the Created Date TimeStamp that will be used to show when the Customer was Created.
     * @param _createdBy the Created By value that will be used to show which user created the Customer.
     * @param _lstUpdt the Last Update TimeStamp that will be used to show when the Customer was Last Updated.
     * @param _updtBy the Last Updated By value that will be used to show which user updated the Customer Last.
     * @param _dvsID the Division ID that will be used to create the Customer. */
    public static void addCustomer(
            int _cstmrID,
            String _cstmrName,
            String _address,
            String _pstCode,
            String _phone,
            Timestamp _created,
            String _createdBy,
            Timestamp _lstUpdt,
            String _updtBy,
            int _dvsID)
            throws SQLException {
        String sql = "INSERT INTO customers " +
                "(Customer_ID, " +
                "Customer_Name, " +
                "Address, " +
                "Postal_Code, " +
                "Phone," +
                "Create_Date," +
                "Created_By," +
                "Last_Update," +
                "Last_Updated_By," +
                "Division_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, _cstmrID);
        ps.setString(2, _cstmrName);
        ps.setString(3, _address);
        ps.setString(4, _pstCode);
        ps.setString(5, _phone);
        ps.setTimestamp(6, _created);
        ps.setString(7, _createdBy);
        ps.setTimestamp(8, _lstUpdt);
        ps.setString(9, _updtBy);
        ps.setInt(10, _dvsID);
        ps.executeUpdate();
    }

    /** This method is used to update a Customer using all the required values, which is updated to the DataBase.
     * @param _cstmrID the Customer ID that will be used to update the Customer.
     * @param _cstmrName the Customer Name that will be used to update the Customer.
     * @param _address the Address that will be used to update the Customer.
     * @param _pstCode the Postal Code that will be used to update the Customer.
     * @param _phone the Phone Number that will be used to update the Customer.
     * @param _lstUpdt the Last Update TimeStamp that will be used to show when the Customer was Last Updated.
     * @param _updtBy the Last Updated By value that will be used to show which user updated the Customer Last.
     * @param _dvsID the Division ID that will be used to update the Customer. */
    public static void updateCustomer(
            int _cstmrID,
            String _cstmrName,
            String _address,
            String _pstCode,
            String _phone,
            Timestamp _lstUpdt,
            String _updtBy,
            int _dvsID)
            throws SQLException {
        String sql = "UPDATE customers SET " +
                "Customer_Name = ?," +
                "Address = ?," +
                "Postal_Code = ?," +
                "Phone = ?," +
                "Last_Update = ?," +
                "Last_Updated_By = ?," +
                "Division_ID = ?" +
                " WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, _cstmrName);
        ps.setString(2, _address);
        ps.setString(3, _pstCode);
        ps.setString(4, _phone);
        ps.setTimestamp(5, _lstUpdt);
        ps.setString(6, _updtBy);
        ps.setInt(7, _dvsID);
        ps.setInt(8, _cstmrID);
        ps.executeUpdate();
    }

    /** This method will retrieve all the Customers from the DataBase and convert them to a Customer Object which is used to populate an ObservableList.
     * @return will return the ObservableList that contains All the Customer Objects. */
    public static ObservableList<Customer> getAllCstm() throws SQLException {
        ObservableList<Customer> allCustomers;
        try {
            allCustomers = FXCollections.observableArrayList();
            String sql = "SELECT * FROM customers";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cstmrID = rs.getInt("Customer_ID");
                String cstmrName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String pstCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Timestamp created = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lstUpdt = rs.getTimestamp("Last_Update");
                String updtBy = rs.getString("Last_Updated_By");
                int dvsID = rs.getInt("Division_ID");
                String ctry = getCtry(dvsID);

                Customer customer = new Customer(cstmrID, cstmrName, address, pstCode, phone, created, createdBy, lstUpdt, updtBy, dvsID, ctry);
                allCustomers.add(customer);
            }
            return allCustomers;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method will delete the all the Appointments that have the Customer ID from the customer that is to be deleted. Once all the appointments for that Customer are deleted the Customer is then deleted.
     * @param cstmID the Customer ID that will be used in the WHERE statement to delete all the necessary Appointments and the Customer. */
    public static void deleteCstm (int cstmID) throws SQLException {
        String sql1 = "DELETE FROM appointments WHERE Customer_ID = ?";
        String sql2 = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps1 = JDBC.connection.prepareStatement(sql1);
        PreparedStatement ps2 = JDBC.connection.prepareStatement(sql2);
        ps1.setInt(1, cstmID);
        ps2.setInt(1, cstmID);
        ps1.executeUpdate();
        ps2.executeUpdate();
    }

    /** This method is used to generate a report that displays how many Appointments each Customer has. Each result set makes a Pair object which populates an ObservableList.
     * @param mainArg the string that is added to the sql statement to define what will be retrieved.
     * @return will return ObservableList of Pairs with the results from the SQL Query. */
    public static ObservableList<Pair<String, Integer>> getCustomerReport(String mainArg) throws SQLException {
        ObservableList<Customer> allCstmr = getAllCstm();
        ObservableList<Pair<String, Integer>> cstmrReport;
        String main1 = null;
        try {
            cstmrReport = FXCollections.observableArrayList();
            String sql = "SELECT " + mainArg;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String main = rs.getString(1);
                int count = rs.getInt(2);

                for (Customer customer : allCstmr) {
                    if (customer.getCstmrID() == Integer.parseInt(main)) {
                        main1 = customer.getCstmrName();
                    }
                }
                Pair<String, Integer> pair = new Pair<>(main1, count);
                cstmrReport.add(pair);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return cstmrReport;
    }

    /** This method will get the Country of the Customer using the Division ID associated to the Customer.
     * @param dvsID the Division ID.
     * @return the Country. */
    public static String getCtry(int dvsID) throws SQLException {
        ObservableList<First_Level_Division> allDvs = first_level_division.getFstLvlDvs();
        ObservableList<Country> allCountries = countries.getCountries();
        String ctry = null;
        for (First_Level_Division firstLevelDivision : allDvs) {
            if (firstLevelDivision.getDvsID() == dvsID) {
                for (Country country : allCountries) {
                    if (country.getCtryID() == firstLevelDivision.getCtryIDF()) {
                        ctry = country.getCtry();
                    }
                }
            }
        }
        return ctry;
    }
}