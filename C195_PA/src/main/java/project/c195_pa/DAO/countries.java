package project.c195_pa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.c195_pa.helper.JDBC;
import project.c195_pa.model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class handles the interaction of Country Data between the Application and the DataBase.
 * @author Kody Lowes */
public class countries {

    /** This method retrieves all the Countries from the DataBase and convert them into a Country Object which is used to populate an ObservableList.
     * @return will return ObservableList of all Country Objects. */
    public static ObservableList<Country> getCountries() throws SQLException {
        ObservableList<Country> allCountries;
        try {
            allCountries = FXCollections.observableArrayList();
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ctryID = rs.getInt("Country_ID");
                String ctry = rs.getString("Country");
                Country country = new Country(ctryID, ctry);
                allCountries.add(country);
            }
            return allCountries;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
