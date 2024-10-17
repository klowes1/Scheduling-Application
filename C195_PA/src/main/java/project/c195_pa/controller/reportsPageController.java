package project.c195_pa.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import project.c195_pa.model.Appointment;
import project.c195_pa.model.Contact;
import project.c195_pa.model.reportResult;
import project.c195_pa.schedulingApplication;
import project.c195_pa.DAO.appointments;
import project.c195_pa.DAO.contacts;
import project.c195_pa.DAO.customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import static project.c195_pa.schedulingApplication.rbL;

/** This class adds functionality to the reportsPage.
 * @author Kody Lowes */
public class reportsPageController implements Initializable {

    @FXML
    private Button refershBtn;
    @FXML
    private Button returnBtn;
    @FXML
    private Tab typeMonth_tab;
    @FXML
    private Tab schedule_Tab;
    @FXML
    private Tab cstmrAppts_Tab;
    @FXML
    private TableColumn<Pair<String, Integer>, String> cstmrName;
    @FXML
    private TableView<Pair<String, Integer>> cstmrAppts;
    @FXML
    private TableColumn<Pair<String, Integer>, Integer> cstmrApptsNum;
    @FXML
    private TableColumn<Appointment, Integer> apptIDC;
    @FXML
    private TableColumn<Appointment, String> apptTitleC;
    @FXML
    private TableColumn<Appointment, String> apptTypeC;
    @FXML
    private TableColumn<Appointment, String> apptDescC;
    @FXML
    private TableColumn<Appointment, Timestamp> apptStart;
    @FXML
    private TableColumn<Appointment, Timestamp> apptEnd;
    @FXML
    private TableColumn<Appointment, Integer> apptCstmID;
    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private TableView<Appointment> cntcSchedule;
    @FXML
    private TableView<reportResult> apptType;
    @FXML
    private TableColumn<reportResult, String> apptTypeCol;
    @FXML
    private TableColumn<reportResult, Integer> apptNum;
    @FXML
    private TableColumn<reportResult, String> apptMonthCol;

    ObservableList<reportResult> typeMonth = null;
    ObservableList<Contact> allContacts = null;
    ObservableList<Appointment> allAppts = null;
    ObservableList<Pair<String, Integer>> allCstmrAppts = null;

    /** This is the initialize method, which runs when the page is opened. This method sets the text
     * language for all the visual text on the page and populates all the table views and combo box. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeMonth_tab.setText(rbL.getString("AppointmentsTypeMonth"));
        schedule_Tab.setText(rbL.getString("ContactSchedule"));
        cstmrAppts_Tab.setText(rbL.getString("CustomerAppointments"));
        refershBtn.setText(rbL.getString("Refresh"));
        returnBtn.setText(rbL.getString("Return"));
        cstmrName.setText(rbL.getString("CustomerName"));
        cstmrApptsNum.setText(rbL.getString("Appointments"));
        apptIDC.setText(rbL.getString("AppointmentID"));
        apptTitleC.setText(rbL.getString("Title"));
        apptTypeC.setText(rbL.getString("Type"));
        apptDescC.setText(rbL.getString("Description"));
        apptStart.setText(rbL.getString("StartDtTm"));
        apptEnd.setText(rbL.getString("EndDtTm"));
        apptCstmID.setText(rbL.getString("CustomerID"));
        contactCombo.setPromptText(rbL.getString("Contact"));
        apptTypeCol.setText(rbL.getString("Type"));
        apptNum.setText(rbL.getString("Appointments"));
        apptMonthCol.setText(rbL.getString("Month"));

        try { allContacts = contacts.getAllcntc(); } catch (SQLException e) { throw new RuntimeException(e); }
        contactCombo.setItems(allContacts);
        try { apptTypeMonthRefresh(); } catch (SQLException e) { throw new RuntimeException(e); }
        try { cstmrApptRefresh(); } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method returns the user to the Navigation Menu.
     * @param actionEvent when teh return button is clicked/activated. */
    public void returnClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("menuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    /** This method will refresh/populate the Number of Appointments by Type and Month TableView by using the getAppointmentsReport method from the appointments class. */
    public void apptTypeMonthRefresh() throws SQLException {
        typeMonth = appointments.getAppointmentsReport("MONTH(Start), Type, COUNT(Type) FROM client_schedule.appointments GROUP BY Type, MONTH(Start) ORDER BY MONTH(Start);");
        apptType.setItems(typeMonth);
        try {
            apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            apptMonthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
            apptNum.setCellValueFactory(new PropertyValueFactory<>("count"));
        } catch (RuntimeException ignore) {}
    }

    /** This method will refresh/populate the TableViews: Number of Appointments by Type, Month, and Customer by calling the refresh methods for those TableViews. */
    public void refreshClicked() {
        try { apptTypeMonthRefresh(); } catch (SQLException e) { throw new RuntimeException(e); }
        try { cstmrApptRefresh(); } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method will refresh/populate the Contact Schedule TableView using the selected Contact from the Contact ComboBox and using the ContactID in the input for the getAppointmentsWhere method from the appointments class.
     * @param cntcID the Contact ID that will be used to retrieve only the appointments with that Contact ID to populate the table view using the getAppointmentsWhere method from the appointments class. */
    public void cntcScheduleRefresh(int cntcID) throws SQLException {
        allContacts = contacts.getAllcntc();
        allAppts = appointments.getAppointmentsWhere("Contact_ID = " + cntcID + " ORDER BY Start");
        cntcSchedule.setItems(allAppts);
        apptIDC.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        apptTitleC.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptTypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptDescC.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCstmID.setCellValueFactory(new PropertyValueFactory<>("cstmID"));
    }

    /** This method will get the Contact selected in the Contact ComboBox, get the Contact ID, call the Contact Schedule Refresh method and use the Contact ID as the input for the method. */
    public void contactSelected() throws SQLException {
        int cntcID = 0;
        try { cntcID = contactCombo.getSelectionModel().getSelectedItem().getCntcID(); } catch (RuntimeException ignored) {}
        cntcScheduleRefresh(cntcID);
    }

    /** This method will refresh/populate the Number of Appointments by Customer TableView by calling the getCustomerReport method from the customers class. */
    public void cstmrApptRefresh() throws SQLException {
        allCstmrAppts = customers.getCustomerReport("Customer_ID, COUNT(Customer_ID) FROM appointments GROUP BY Customer_ID");
        cstmrAppts.setItems(allCstmrAppts);
        cstmrName.setCellValueFactory(new PropertyValueFactory<>("key"));
        cstmrApptsNum.setCellValueFactory(new PropertyValueFactory<>("value"));
    }
}