package project.c195_pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.c195_pa.DAO.appointments;
import project.c195_pa.helper.JDBC;
import project.c195_pa.DAO.users;
import project.c195_pa.schedulingApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import static project.c195_pa.schedulingApplication.rbL;

/** This class adds functionality to the loginPage.
 * @author Kody Lowes */
public class loginPageController implements Initializable {

    @FXML
    private TextField user_name;
    @FXML
    private PasswordField password;
    @FXML
    private Label title1;
    @FXML
    private Button loginB;
    @FXML
    private Button cancelB;
    @FXML
    private Label title2;
    @FXML
    private Label localeL;

    public static String actvUsr;

    /** This is the initialize method, which runs when the page is opened. This method sets the text
     * language for all visual text on the page and gets the user's location based on the user's default ZoneID. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_name.setPromptText(rbL.getString("usrName"));
        password.setPromptText(rbL.getString("pssWrd"));
        title1.setText(rbL.getString("title_primary"));
        title2.setText(rbL.getString("title_secondary"));
        cancelB.setText(rbL.getString("Exit"));
        loginB.setText(rbL.getString("loginButton"));
        localeL.setText(ZoneId.systemDefault().getId());
    }

    /** This method will close the application once the action is confirmed via confirmation message. */
    public void cancelClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(rbL.getString("CloseAppl"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) { javafx.application.Platform.exit(); JDBC.closeConnection();}
    }

    /** This method will get the username and password from the user input and use it as the input for the login method in the users class. If the login is successful it will change the result value to "Login Successful" which is used for the loginActivity method and send the user to the Navigation Menu. If the login is unsuccessful the result value will be changed to "Login Failed" and an error message will show. Also on a successful login the active user value will be set to the username used to login.
     * @param actionEvent when login button is clicked/activated. */
    public void loginClicked(ActionEvent actionEvent) throws SQLException, IOException {
        String result = null;
        if (users.login( user_name.getText(), password.getText())) {
            result = "Login Successful";
            actvUsr = user_name.getText();
            appointments.checkApptAlert();
            FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("menuPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else {
            result = "Login Failed";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("InvalidLogin"));
            alert.showAndWait();
        }
        loginActivity(result, user_name.getText(), password.getText());
    }

    /** This method will take the username and password from the user inputs, the result from the login attempt, and a current TimeStamp. Which is then appended to the login_activity.txt file to track user login attempts.
     * @param result the outcome of the login attempt ("Login Successful" or "Login Failed").
     * @param usrName the username that was used to login.
     * @param pswd the password that was used to login. */
    public void loginActivity(String result, String usrName, String pswd) throws IOException {
        String actLogName = "src/main/java/project/c195_pa/login_activity.txt";
        try { File actLog = new File("src/main/java/project/c195_pa/login_activity.txt"); actLog.createNewFile(); } catch (IOException ignore) {}
            FileWriter lgnActFileW = new FileWriter(actLogName, true);
            PrintWriter lgnActPrintW = new PrintWriter(lgnActFileW);
            lgnActPrintW.println("\nLogin Result: " + result + "\t|\tUsername: " + usrName + "\t|\tPassword: " + pswd + "\t|\tDate/Time: " + Timestamp.from(Instant.now()));
            lgnActFileW.close();
    }
}