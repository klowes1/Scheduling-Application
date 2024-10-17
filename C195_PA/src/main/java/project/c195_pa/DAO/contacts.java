package project.c195_pa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.c195_pa.helper.JDBC;
import project.c195_pa.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class handles the interaction of Contact Data between the Application and the DataBase.
 * @author Kody Lowes */
public class contacts {

    /** This method retrieves all the Contacts from the DataBase and convert them to a Contact Object which is used to populate and ObservableList.
     * @return will return ObservableList of all Contact Objects. */
    public static ObservableList<Contact> getAllcntc() throws SQLException {
        ObservableList<Contact> allContacts;
        try {
            allContacts = FXCollections.observableArrayList();
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cntcID = rs.getInt("Contact_ID");
                String cntcName = rs.getString("Contact_Name");
                String cntcEmail = rs.getString("Email");
                Contact contact = new Contact(cntcID, cntcName, cntcEmail);
                allContacts.add(contact);
            }
            return allContacts;
        } catch (SQLException e) {throw new RuntimeException(e); }
    }
}