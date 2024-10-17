package project.c195_pa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.c195_pa.helper.JDBC;
import project.c195_pa.schedulingApplication;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static project.c195_pa.schedulingApplication.rbL;

/** This class adds functionality to the menuPage.
 * @author Kody Lowes */
public class menuPageController implements Initializable {

    @FXML
    private Button logBt;
    @FXML
    private Text navTitleTxt;
    @FXML
    private Button exitBt;
    @FXML
    private Button logsBt;
    @FXML
    private Button cstmrBt;
    @FXML
    private Button apptBt;

    /** This is the initialize method, which runs when the page is opened.
     * This method sets the text language for all visual text on the page. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navTitleTxt.setText(rbL.getString("NavigationMenu"));
        exitBt.setText(rbL.getString("Exit"));
        logsBt.setText(rbL.getString("Reports"));
        logBt.setText(rbL.getString("LogOut"));
        cstmrBt.setText(rbL.getString("Customers"));
        apptBt.setText(rbL.getString("Appointments"));
    }

    /** This method closes the application once the user confirms the action via confirmation message. */
    public void exitClicked() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(rbL.getString("CloseAppl"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) { javafx.application.Platform.exit(); JDBC.closeConnection();}
    }

    /** This method navigates the user to the reportsPage.
     * @param actionEvent when the reports button is clicked/activated. */
    public void reportsClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("reportsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(343);
        stage.setScene(scene);
        stage.show();
    }

    /** This method navigates the user to the customersPage.
     * @param actionEvent when the customers button is clicked/activated. */
    public void customersClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("customersPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(319);
        stage.setScene(scene);
        stage.show();
    }

    /** This method navigates the user to the appointmentsPage.
     * @param actionEvent when the appointments button is clicked/activated. */
    public void appointmentsClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("appointmentsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(319);
        stage.setScene(scene);
        stage.show();
    }

    /** This method will log the user out and return them to the login page once the user confirms the action via confirmation message.
     * @param actionEvent when the logout button is clicked/activated. */
    public void logOutClicked(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(rbL.getString("LogOutCnfr"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("loginPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}