package project.c195_pa.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.c195_pa.helper.JDBC;
import project.c195_pa.model.First_Level_Division;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class handles the interaction of Division Data between the Application and the DataBase.
 * @author Kody Lowes */
public abstract class first_level_division {

    /** This method retrieves all the Divisions from the DataBase and converts the into a Division Object that is used to populate an ObservableList.
     * @return will return ObservableList of all First_Level_Division Objects. */
    public static ObservableList<First_Level_Division> getFstLvlDvs() throws SQLException {
        ObservableList<First_Level_Division> allFstLvlDvs;
        try {
            allFstLvlDvs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int dvsID = rs.getInt("Division_ID");
                String dvs = rs.getString("Division");
                int ctryID = rs.getInt("Country_ID");
                First_Level_Division division = new First_Level_Division(dvsID, dvs, ctryID);
                allFstLvlDvs.add(division);
            }
            return allFstLvlDvs;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}