package project.c195_pa.DAO;

import project.c195_pa.helper.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class handles the interaction of User Data between the Application and the DataBase.
 * @author Kody Lowes */
public abstract class users {

    /** This method is used to verify that the credentials being used to log into the application exist and are correct.
     * @param _userName will be used to find and compare the login information of the user data from the DataBase.
     * @param _password will be compared to the stored password in the DataBase to verify the credential is correct.
     * @return will return True if credentials are valid and False if they are invalid. */
    public static boolean login(String _userName, String _password) throws SQLException {
        String userName = "";
        String password = "";
        String sql = "SELECT * FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, _userName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            userName = rs.getString("User_Name");
            password = rs. getString("Password");
        }
        if (userName.equals(_userName) && password.equals(_password) && !_userName.isEmpty() && !_password.isEmpty()) { return true;}
        else{ return false; }
    }
}