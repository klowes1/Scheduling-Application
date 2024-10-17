package project.c195_pa.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.c195_pa.DAO.appointments;
import project.c195_pa.DAO.contacts;
import project.c195_pa.model.Appointment;
import project.c195_pa.model.Contact;
import project.c195_pa.schedulingApplication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import static project.c195_pa.schedulingApplication.rbL;

/** This class adds functionality to the appointmentsPage.
 * @author Kody Lowes */
public class appointmentsPageController implements Initializable {

    @FXML
    private Tab allApptTab;
    @FXML
    private TextField apptID;
    @FXML
    private TextField title;
    @FXML
    private TextField desc;
    @FXML
    private TextField location;
    @FXML
    private TextField type;
    @FXML
    private TextField cstmrID;
    @FXML
    private TextField userID;
    @FXML
    private AnchorPane apptmentPane;
    @FXML
    private Accordion apptAccord;
    @FXML
    private TitledPane apptTitledPane;
    @FXML
    private Tab apptMonthTab;
    @FXML
    private Tab apptWeekTab;
    @FXML
    private HBox addApptMenu;
    @FXML
    private HBox updateApptMenu;
    @FXML
    private TabPane tabSlct;
    @FXML
    private Button saveAppt_A;
    @FXML
    private Button saveAppt_U;
    @FXML
    private DatePicker dtPick;
    @FXML
    private DatePicker startDt;
    @FXML
    private DatePicker endDt;
    @FXML
    private Button addBtMenu;
    @FXML
    private Button updBtMenu;
    @FXML
    private Button delBtMenu;
    @FXML
    private Button cnlBtMenu;
    @FXML
    private Button cnlBt_A;
    @FXML
    private Button aplBt_A;
    @FXML
    private Text apptIDTxt;
    @FXML
    private Text apptTtlTxt;
    @FXML
    private Text apptDescTxt;
    @FXML
    private Text apptLocTxt;
    @FXML
    private Text apptCntcTxt;
    @FXML
    private Text apptTpTxt;
    @FXML
    private Text apptStDtTxt;
    @FXML
    private Text apptEndDtTxt;
    @FXML
    private Text apptCstmIDTxt;
    @FXML
    private Text apptUsrIDTxt;
    @FXML
    private Text apptStTmTxt;
    @FXML
    private Text apptEndTmTxt;
    @FXML
    private Button cnlBt_U;
    @FXML
    private Button aplBt_U;

    @FXML
    private ComboBox<String> startHr;
    @FXML
    private ComboBox<String> startMnt;
    @FXML
    private ComboBox<String> endHr;
    @FXML
    private ComboBox<String> endMnt;

    @FXML
    private TableView<Appointment> allAppts;
        @FXML
        private TableColumn<Appointment, Integer> apptID_A;
        @FXML
        private TableColumn<Appointment, String> title_A;
        @FXML
        private TableColumn<Appointment, String> desc_A;
        @FXML
        private TableColumn<Appointment, String> location_A;
        @FXML
        private TableColumn<Appointment, String> contact_A;
        @FXML
        private TableColumn<Appointment, String> type_A;
        @FXML
        private TableColumn<Appointment, Calendar> start_A;
        @FXML
        private TableColumn<Appointment, Calendar> end_A;
        @FXML
        private TableColumn<Appointment, Integer> cstmID_A;
        @FXML
        private TableColumn<Appointment, Integer> usrID_A;

    @FXML
    private TableView<Appointment> monthAppts;
        @FXML
        private TableColumn<Appointment, Integer> apptID_M;
        @FXML
        private TableColumn<Appointment, String> title_M;
        @FXML
        private TableColumn<Appointment, String> desc_M;
        @FXML
        private TableColumn<Appointment, String> location_M;
        @FXML
        private TableColumn<Appointment, String> contact_M;
        @FXML
        private TableColumn<Appointment, String> type_M;
        @FXML
        private TableColumn<Appointment, Calendar> start_M;
        @FXML
        private TableColumn<Appointment, Calendar> end_M;
        @FXML
        private TableColumn<Appointment, Integer> cstmID_M;
        @FXML
        private TableColumn<Appointment, Integer> usrID_M;

    @FXML
    private TableView<Appointment> weekAppts;
        @FXML
        private TableColumn<Appointment, Integer> apptID_W;
        @FXML
        private TableColumn<Appointment, String> title_W;
        @FXML
        private TableColumn<Appointment, String> desc_W;
        @FXML
        private TableColumn<Appointment, String> location_W;
        @FXML
        private TableColumn<Appointment, String> contact_W;
        @FXML
        private TableColumn<Appointment, String> type_W;
        @FXML
        private TableColumn<Appointment, Timestamp> start_W;
        @FXML
        private TableColumn<Appointment, Timestamp> end_W;
        @FXML
        private TableColumn<Appointment, Integer> cstmID_W;
        @FXML
        private TableColumn<Appointment, Integer> usrID_W;

    @FXML
    private ComboBox<Contact> contact;

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<Appointment> allAppointmentments = null;
    FilteredList<Appointment> apptMonthFilter = null;
    FilteredList<Appointment> apptWeekFilter = null;
    ObservableList<Contact> allContacts = null;


    /** This is the initialize method, which runs when the page is opened. This method sets the text
     * language for all visual text on the page and populates the tables and combo boxes. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allApptTab.setText((rbL.getString("AllAppointments")));
        apptMonthTab.setText(rbL.getString("Month"));
        apptWeekTab.setText(rbL.getString("Week"));
        apptID_A.setText(rbL.getString("AppointmentID"));
        apptID_M.setText(rbL.getString("AppointmentID"));
        apptID_W.setText(rbL.getString("AppointmentID"));
        title_A.setText(rbL.getString("Title"));
        title_M.setText(rbL.getString("Title"));
        title_W.setText(rbL.getString("Title"));
        desc_A.setText(rbL.getString("Description"));
        desc_M.setText(rbL.getString("Description"));
        desc_W.setText(rbL.getString("Description"));
        location_A.setText(rbL.getString("Location"));
        location_M.setText(rbL.getString("Location"));
        location_W.setText(rbL.getString("Location"));
        contact_A.setText(rbL.getString("Contact"));
        contact_M.setText(rbL.getString("Contact"));
        contact_W.setText(rbL.getString("Contact"));
        type_A.setText(rbL.getString("Type"));
        type_M.setText(rbL.getString("Type"));
        type_W.setText(rbL.getString("Type"));
        start_A.setText(rbL.getString("StartDtTm"));
        start_M.setText(rbL.getString("StartDtTm"));
        start_W.setText(rbL.getString("StartDtTm"));
        end_A.setText(rbL.getString("EndDtTm"));
        end_M.setText(rbL.getString("EndDtTm"));
        end_W.setText(rbL.getString("EndDtTm"));
        cstmID_A.setText(rbL.getString("CustomerID"));
        cstmID_M.setText(rbL.getString("CustomerID"));
        cstmID_W.setText(rbL.getString("CustomerID"));
        usrID_A.setText(rbL.getString("UserID"));
        usrID_M.setText(rbL.getString("UserID"));
        usrID_W.setText(rbL.getString("UserID"));
        dtPick.setPromptText(rbL.getString("SelectDt"));
        addBtMenu.setText(rbL.getString("Add"));
        updBtMenu.setText(rbL.getString("Update"));
        delBtMenu.setText(rbL.getString("Delete"));
        cnlBtMenu.setText(rbL.getString("Cancel"));
        apptTitledPane.setText(rbL.getString("AppointmentMenu"));
        apptIDTxt.setText(rbL.getString("AppointmentID"));
        apptID.setPromptText(rbL.getString("AppointmentID"));
        apptTtlTxt.setText(rbL.getString("Title"));
        title.setPromptText(rbL.getString("Title"));
        apptDescTxt.setText(rbL.getString("Description"));
        desc.setPromptText(rbL.getString("Description"));
        apptLocTxt.setText(rbL.getString("Location"));
        location.setPromptText(rbL.getString("Location"));
        apptCntcTxt.setText(rbL.getString("Contact"));
        contact.setPromptText(rbL.getString("Contact"));
        apptTpTxt.setText(rbL.getString("Type"));
        type.setPromptText(rbL.getString("Type"));
        apptStDtTxt.setText(rbL.getString("StartDt"));
        startDt.setPromptText(rbL.getString("StartDt"));
        apptStTmTxt.setText(rbL.getString("StartTm"));
        startHr.setPromptText(rbL.getString("Hour"));
        startMnt.setPromptText(rbL.getString("Minute"));
        apptEndDtTxt.setText(rbL.getString("EndDt"));
        endDt.setPromptText(rbL.getString("EndDt"));
        apptEndTmTxt.setText(rbL.getString("EndTm"));
        endHr.setPromptText(rbL.getString("Hour"));
        endMnt.setPromptText(rbL.getString("Minute"));
        saveAppt_U.setText(rbL.getString("Save"));
        saveAppt_A.setText(rbL.getString("Save"));
        cnlBt_U.setText(rbL.getString("Cancel"));
        cnlBt_A.setText(rbL.getString("Cancel"));
        aplBt_U.setText(rbL.getString("Apply"));
        aplBt_A.setText(rbL.getString("Apply"));
        apptCstmIDTxt.setText(rbL.getString("CustomerID"));
        apptUsrIDTxt.setText(rbL.getString("UserID"));

        hours.addAll("00", "01", "02", "03", "04", "05", "06","07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        startHr.setItems(hours);
        endHr.setItems(hours);
        minutes.addAll("00", "15", "30", "45");
        startMnt.setItems(minutes);
        endMnt.setItems(minutes);

        try { allContacts = contacts.getAllcntc(); } catch (SQLException e) { throw  new RuntimeException(e); }
        contact.setItems(allContacts);

        updateAllApptList();
        updateMonthApptList();
        updateWeekApptList();

        try { dtPick.setValue(LocalDate.now()); } catch (RuntimeException ignored) {}
    }

    /** This method returns the user to the Navigation Menu.
     * @param actionEvent when the return button is clicked/activated. */
    public void returnMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("menuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    /** This method displays the Appointments Menu and prepares the user input controls for a new appointment to be added, sets the add menu visible, and sets the update menu invisible.
     * @param actionEvent when the add button is clicked/activated. */
    public void addApptClicked(ActionEvent actionEvent) {
        saveAppt_A.setDisable(false);
        saveAppt_U.setDisable(false);
        addApptMenu.setDisable(false);
        apptAccord.setMaxHeight(Region.USE_COMPUTED_SIZE);
        apptAccord.setVisible(true);
        apptTitledPane.setExpanded(true);
        addApptMenu.setVisible(true);
        updateApptMenu.setVisible(false);
        Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        apptStage.setHeight(650);
        title.clear();
        desc.clear();
        location.clear();
        type.clear();
        startDt.setValue(null);
        startHr.setValue(null);
        startMnt.setValue(null);
        endDt.setValue(null);
        endHr.setValue(null);
        endMnt.setValue(null);
        cstmrID.clear();
        userID.clear();
        contact.getSelectionModel().clearSelection();
        int apptListSze = allAppointmentments.size();
        apptID.setText(String.valueOf(allAppointmentments.get(apptListSze - 1).getApptID() +1));
        try { monthAppts.getSelectionModel().clearSelection(); } catch (RuntimeException ignored) {}
        try { weekAppts.getSelectionModel().clearSelection(); } catch (RuntimeException ignored) {}
    }

    /** This method displays the Appointment Menu and populates the user input controls with the selected appointment's data, sets the update menu visible, and sets the add menu invisible.
     * @param actionEvent when the update button is clicked/activated. */
    public void updateApptClicked(ActionEvent actionEvent) {
        saveAppt_U.setDisable(false);
        saveAppt_A.setDisable(false);
        try {
            DateTimeFormatter formatterHR = DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter formatterMN = DateTimeFormatter.ofPattern("mm");
            if (allAppts.isVisible()) {
                Appointment appointment = allAppts.getSelectionModel().getSelectedItem();
                apptID.setText(String.valueOf(appointment.getApptID()));
                title.setText(appointment.getTitle());
                desc.setText(appointment.getDescription());
                location.setText(appointment.getLocation());
                int contactID = appointment.getContact();
                selectContact(contactID);
                type.setText(appointment.getType());
                startDt.setValue(appointment.getStart().toLocalDateTime().toLocalDate());
                startHr.setValue(formatterHR.format(appointment.getStart().toLocalDateTime()));
                startMnt.setValue(formatterMN.format(appointment.getStart().toLocalDateTime()));
                endDt.setValue(appointment.getEnd().toLocalDateTime().toLocalDate());
                endHr.setValue(formatterHR.format(appointment.getEnd().toLocalDateTime()));
                endMnt.setValue(formatterMN.format(appointment.getEnd().toLocalDateTime()));
                cstmrID.setText(String.valueOf(appointment.getCstmID()));
                userID.setText(String.valueOf(appointment.getUsrID()));
            } else if (monthAppts.isVisible()) {
                Appointment appointment = monthAppts.getSelectionModel().getSelectedItem();
                apptID.setText(String.valueOf(appointment.getApptID()));
                title.setText(appointment.getTitle());
                desc.setText(appointment.getDescription());
                location.setText(appointment.getLocation());
                int contactID = appointment.getContact();
                selectContact(contactID);
                type.setText(appointment.getType());
                startDt.setValue(appointment.getStart().toLocalDateTime().toLocalDate());
                startHr.setValue(formatterHR.format(appointment.getStart().toLocalDateTime()));
                startMnt.setValue(formatterMN.format(appointment.getStart().toLocalDateTime()));
                endDt.setValue(appointment.getEnd().toLocalDateTime().toLocalDate());
                endHr.setValue(formatterHR.format(appointment.getEnd().toLocalDateTime()));
                endMnt.setValue(formatterMN.format(appointment.getEnd().toLocalDateTime()));
                cstmrID.setText(String.valueOf(appointment.getCstmID()));
                userID.setText(String.valueOf(appointment.getUsrID()));
            } else if (weekAppts.isVisible()) {
                Appointment appointment = weekAppts.getSelectionModel().getSelectedItem();
                apptID.setText(String.valueOf(appointment.getApptID()));
                title.setText(appointment.getTitle());
                desc.setText(appointment.getDescription());
                location.setText(appointment.getLocation());
                Appointment selectedAppt = weekAppts.getSelectionModel().getSelectedItem();
                int contactID = selectedAppt.getContact();
                selectContact(contactID);
                type.setText(appointment.getType());
                startDt.setValue(appointment.getStart().toLocalDateTime().toLocalDate());
                startHr.setValue(String.valueOf(appointment.getStart().toLocalDateTime().getHour()));
                startMnt.setValue(String.valueOf(appointment.getStart().toLocalDateTime().getMinute()));
                if (startMnt.getValue().equals("0")) {
                    startMnt.setValue("00");
                }
                endDt.setValue(appointment.getEnd().toLocalDateTime().toLocalDate());
                endHr.setValue(String.valueOf(appointment.getEnd().toLocalDateTime().getHour()));
                endMnt.setValue(String.valueOf(appointment.getEnd().toLocalDateTime().getMinute()));
                if (endMnt.getValue().equals("0")) {
                    endMnt.setValue("00");
                }
                cstmrID.setText(String.valueOf(appointment.getCstmID()));
                userID.setText(String.valueOf(appointment.getUsrID()));
            }
            saveAppt_U.setDisable(false);
            apptAccord.setMaxHeight(Region.USE_COMPUTED_SIZE);
            apptAccord.setVisible(true);
            apptTitledPane.setExpanded(true);
            updateApptMenu.setVisible(true);
            addApptMenu.setVisible(false);
            Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            apptStage.setHeight(650);
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("SlctApptU"));
            alert.showAndWait();
        }
    }

    /** This method will delete the selected appointment after the user has confirmed the action via the confirmation message, then updates the table views to display the change. */
    public void deleteApptClicked() throws SQLException {
        try {
            if (allApptTab.isSelected()) {
                Appointment selectedAppt = allAppts.getSelectionModel().getSelectedItem();
                int slctApptID = selectedAppt.getApptID();
                String slctApptType = selectedAppt.getType();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(rbL.getString("CnclAppt1") + "\n" + rbL.getString("Appointment") + ": " + slctApptID + ", " + slctApptType);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    appointments.deleteAppt(selectedAppt.getApptID());
                }
            } else if (apptMonthTab.isSelected()) {
                Appointment selectedAppt = monthAppts.getSelectionModel().getSelectedItem();
                int slctApptID = selectedAppt.getApptID();
                String slctApptType = selectedAppt.getType();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(rbL.getString("CnclAppt1") + "\n" + rbL.getString("Appointment") + ": " + slctApptID + ", " + slctApptType);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    appointments.deleteAppt(selectedAppt.getApptID());
                }
            } else if (apptWeekTab.isSelected()) {
                Appointment selectedAppt = weekAppts.getSelectionModel().getSelectedItem();
                int slctApptID = selectedAppt.getApptID();
                String slctApptType = selectedAppt.getType();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText(rbL.getString("CnclAppt1") + "\n" + rbL.getString("Appointment") + ": " + slctApptID + ", " + slctApptType);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    appointments.deleteAppt(selectedAppt.getApptID());
                }
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("SlctApptD"));
            alert.showAndWait();
        }
        updateAllApptList();
        updateWeekApptList();
        updateMonthApptList();
        dateSelected();
    }

    /** This method will save the appointment information that is being added/updated depending on which menu, Add or Update, is being activated and closes the appointment menu. Also, updates the table views to display changes that have been made.
     * @param actionEvent when the save button is clicked/activated. */
    public void saveClicked(ActionEvent actionEvent) throws SQLException {
        LocalDate strDate = null;
        String strHour = null;
        String strMin = null;
        LocalDate endDate = null;
        String endHour = null;
        String endMin = null;
        int appt_ID = 0;
        String appt_Tt = null;
        String appt_D = null;
        String appt_L = null;
        int appt_C = 0;
        String appt_Tp = null;
        int appt_CID = 0;
        int appt_UID = 0;
        try {
            strDate = startDt.getValue();
            strHour = startHr.getValue();
            strMin = startMnt.getValue();
            LocalDateTime startLTD = LocalDateTime.of(strDate.getYear(), strDate.getMonth(), strDate.getDayOfMonth(), Integer.parseInt(strHour), Integer.parseInt(strMin));
            ZonedDateTime startLocale = ZonedDateTime.of(startLTD, ZoneId.systemDefault());

            endDate = endDt.getValue();
            endHour = endHr.getValue();
            endMin = endMnt.getValue();
            LocalDateTime endLTD = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMin));
            ZonedDateTime endLocale = ZonedDateTime.of(endLTD, ZoneId.systemDefault());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            appt_ID = Integer.parseInt(apptID.getText());
            appt_Tt = title.getText();
            appt_D = desc.getText();
            appt_L = location.getText();
            appt_C = contact.getSelectionModel().getSelectedItem().getCntcID();
            appt_Tp = type.getText();
            Timestamp start = Timestamp.valueOf(formatter.format(startLocale));
            Timestamp end = Timestamp.valueOf(formatter.format(endLocale));
            appt_CID = Integer.parseInt(cstmrID.getText());
            appt_UID = Integer.parseInt(userID.getText());
            Timestamp crtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String crtBy = project.c195_pa.controller.loginPageController.actvUsr;
            Timestamp updtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String updtBy = project.c195_pa.controller.loginPageController.actvUsr;
            try {
                if (addApptMenu.isVisible()) {
                    appointments.addAppointment(appt_ID, appt_Tt, appt_D, appt_L, appt_C, appt_Tp, start, end, appt_CID, appt_UID, crtDt, crtBy, updtDt, updtBy);
                } else if (updateApptMenu.isVisible()) {
                    appointments.updateAppointment(appt_ID, appt_Tt, appt_D, appt_L, appt_C, appt_Tp, start, end, appt_CID, appt_UID, updtDt, updtBy);
                }
                apptTitledPane.setExpanded(false);
                apptAccord.setVisible(false);
                apptAccord.setMaxHeight(0);
                apptmentPane.setMinHeight(319);
                Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                apptStage.setHeight(319);
            } catch (SQLIntegrityConstraintViolationException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rbL.getString("VrfInfoC"));
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("VrfInfoP"));
            alert.showAndWait();
        }
        updateAllApptList();
        updateMonthApptList();
        updateWeekApptList();
        dateSelected();
    }

    /** This method closes the appointment menu and makes no changes to any appointment information old/new.
     * @param actionEvent when the cancel button is clicked/activated. */
    public void cancelClicked(ActionEvent actionEvent) {
        apptTitledPane.setExpanded(false);
        apptAccord.setVisible(false);
        apptAccord.setMaxHeight(0);
        apptmentPane.setMinHeight(319);
        Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        apptStage.setHeight(319);
    }

    /** This method will apply/save the appointment information that is being added/updated depending on which menu, Add or Update, is being activated and updates the table views to display changes that have been made.  */
    public void applyClicked() throws SQLException {
        try {
            LocalDate strDate = startDt.getValue();
            String strHour = startHr.getValue();
            String strMin = startMnt.getValue();
            LocalDateTime startLTD = LocalDateTime.of(strDate.getYear(), strDate.getMonth(), strDate.getDayOfMonth(), Integer.parseInt(strHour), Integer.parseInt(strMin));
            ZonedDateTime startLocale = ZonedDateTime.of(startLTD, ZoneId.systemDefault());
            LocalDate endDate = endDt.getValue();
            String endHour = endHr.getValue();
            String endMin = endMnt.getValue();
            LocalDateTime endLTD = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMin));
            ZonedDateTime endLocale = ZonedDateTime.of(endLTD, ZoneId.systemDefault());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            int appt_ID = Integer.parseInt(apptID.getText());
            String appt_Tt = title.getText();
            String appt_D = desc.getText();
            String appt_L = location.getText();
            int appt_C = contact.getSelectionModel().getSelectedItem().getCntcID();
            String appt_Tp = type.getText();
            Timestamp start = Timestamp.valueOf(formatter.format(startLocale));
            Timestamp end = Timestamp.valueOf(formatter.format(endLocale));
            int appt_CID = Integer.parseInt(cstmrID.getText());
            int appt_UID = Integer.parseInt(userID.getText());
            Timestamp crtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String crtBy = project.c195_pa.controller.loginPageController.actvUsr;
            Timestamp updtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String updtBy = project.c195_pa.controller.loginPageController.actvUsr;
            try {
                if (addApptMenu.isVisible()) {
                    saveAppt_A.setDisable(true);
                    appointments.addAppointment(appt_ID, appt_Tt, appt_D, appt_L, appt_C, appt_Tp, start, end, appt_CID, appt_UID, crtDt, crtBy, updtDt, updtBy);
                } else if (updateApptMenu.isVisible()) {
                    saveAppt_U.setDisable(true);
                    appointments.updateAppointment(appt_ID, appt_Tt, appt_D, appt_L, appt_C, appt_Tp, start, end, appt_CID, appt_UID, updtDt, updtBy);
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rbL.getString("VrfInfoC"));
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("VrfInfoP"));
            alert.showAndWait();
        }
        updateAllApptList();
        updateMonthApptList();
        updateWeekApptList();
        dateSelected();
    }

    /** This method gets the date selected in the date picker and filters the TableView, Month Table or Week Table, that is visible based on the selected month/week.
     * LAMBDA: The lambda expression used in this method takes the appointment and converts it to a Calendar object by getting the Start Date and Time and using it as the input for the TimeStampConvert(METHOD NAME: tmStCvt) Method. Converting the timestamp to a calendar allows for easier filtering since the calendar object has month and week value retrieval methods already built into the object. */
    public void dateSelected() {
        int month = dtPick.getValue().getMonth().getValue() - 1;
        int week = dtPick.getValue().get(WeekFields.ISO.weekOfWeekBasedYear());
        int year = dtPick.getValue().getYear();
        if (dtPick.getValue() != null && allAppts.isVisible() ) {
            allAppts.setItems(allAppointmentments);
        } else if (dtPick.getValue() != null && monthAppts.isVisible() ) {
            apptMonthFilter = new FilteredList<>(allAppointmentments, appointment -> (tmStCvt(appointment.getStart().toInstant()).get(Calendar.MONTH) == month && tmStCvt(appointment.getStart().toInstant()).get(Calendar.YEAR) == year));
            monthAppts.setItems(apptMonthFilter);
        } else if (dtPick.getValue() != null && weekAppts.isVisible()) {
            apptWeekFilter = new FilteredList<>(allAppointmentments, appointment -> (tmStCvt(appointment.getStart().toInstant()).get(Calendar.WEEK_OF_YEAR) == week && tmStCvt(appointment.getStart().toInstant()).get(Calendar.YEAR) == year));
            weekAppts.setItems(apptWeekFilter);
        } else {
            allAppts.setItems(allAppointmentments);
        }
    }

    /** This method updates the Month TableView by retrieving the list of appointments from the database using the getAppointments method from the appointments class. */
    private void updateMonthApptList() {
        try { allAppointmentments = appointments.getAppointments(); } catch (SQLException e) { throw new RuntimeException(e); }
        monthAppts.setItems(allAppointmentments);
        apptID_M.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        title_M.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_M.setCellValueFactory(new PropertyValueFactory<>("description"));
        location_M.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact_M.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type_M.setCellValueFactory(new PropertyValueFactory<>("type"));
        start_M.setCellValueFactory(new PropertyValueFactory<>("start"));
        end_M.setCellValueFactory(new PropertyValueFactory<>("end"));
        cstmID_M.setCellValueFactory(new PropertyValueFactory<>("cstmID"));
        usrID_M.setCellValueFactory(new PropertyValueFactory<>("usrID"));
    }

    /** This method updates the Week TableView by retrieving the list of appointments from the database using the getAppointments method from the appointments class. */
    private void updateWeekApptList() {
        try { allAppointmentments = appointments.getAppointments(); } catch (SQLException e) { throw new RuntimeException(e); }
        weekAppts.setItems(allAppointmentments);
        apptID_W.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        title_W.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_W.setCellValueFactory(new PropertyValueFactory<>("description"));
        location_W.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact_W.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type_W.setCellValueFactory(new PropertyValueFactory<>("type"));
        start_W.setCellValueFactory(new PropertyValueFactory<>("start"));
        end_W.setCellValueFactory(new PropertyValueFactory<>("end"));
        cstmID_W.setCellValueFactory(new PropertyValueFactory<>("cstmID"));
        usrID_W.setCellValueFactory(new PropertyValueFactory<>("usrID"));
    }

    /** This method updates the All TableView by retrieving the list of appointments from the database using the getAppointments method from the appointments class. */
    private void updateAllApptList() {
        try { allAppointmentments = appointments.getAppointments(); } catch (SQLException e) { throw new RuntimeException(e); }
        allAppts.setItems(allAppointmentments);
        apptID_A.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        title_A.setCellValueFactory(new PropertyValueFactory<>("title"));
        desc_A.setCellValueFactory(new PropertyValueFactory<>("description"));
        location_A.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact_A.setCellValueFactory(new PropertyValueFactory<>("contact"));
        type_A.setCellValueFactory(new PropertyValueFactory<>("type"));
        start_A.setCellValueFactory(new PropertyValueFactory<>("start"));
        end_A.setCellValueFactory(new PropertyValueFactory<>("end"));
        cstmID_A.setCellValueFactory(new PropertyValueFactory<>("cstmID"));
        usrID_A.setCellValueFactory(new PropertyValueFactory<>("usrID"));
    }

    /** This method converts a TimeStamp/Instant to a Calendar object which is used to filter the appointments in the TableViews.
     * @param instant the timeStamp to be converted set to an instant using the .toInstant method.
     * @return will return the Calendar Object. */
    public Calendar tmStCvt(Instant instant){
        ZonedDateTime startZoned = instant.atZone(ZoneId.systemDefault());
        return GregorianCalendar.from(startZoned);
    }

    /** This method changes which TableView is visible based on which tab is selected and sets the datePick value the current date to filter the visible TableView. */
    public void tabChange() {
        if (allApptTab.isSelected()) {
            allAppts.setVisible(true);
            try { updateAllApptList(); } catch (RuntimeException ignored) {}
            try { monthAppts.setVisible(false); } catch (NullPointerException ignored) {}
            try { weekAppts.setVisible(false); } catch (NullPointerException ignored) {}
        } else if (apptMonthTab.isSelected()) {
            monthAppts.setVisible(true);
            try {
                dtPick.setValue(LocalDate.now());
                int month = dtPick.getValue().getMonth().getValue() - 1;
                apptMonthFilter = new FilteredList<>(allAppointmentments, appointment -> tmStCvt(appointment.getStart().toInstant()).get(Calendar.MONTH) == month);
                monthAppts.setItems(apptMonthFilter);
            } catch (RuntimeException ignored) {}
            try { weekAppts.setVisible(false); } catch (NullPointerException ignored) {}
            try { allAppts.setVisible(false); } catch (NullPointerException ignored) {}
            dateSelected();
        } else if (apptWeekTab.isSelected()) {
            weekAppts.setVisible(true);
            try {
                dtPick.setValue(LocalDate.now());
                int week = dtPick.getValue().get(WeekFields.ISO.weekOfWeekBasedYear());
                apptWeekFilter = new FilteredList<>(allAppointmentments, appointment -> tmStCvt(appointment.getStart().toInstant()).get(Calendar.WEEK_OF_YEAR) == week);
                weekAppts.setItems(apptWeekFilter);
            } catch (RuntimeException ignored) {}
            try { monthAppts.setVisible(false); } catch (NullPointerException ignored) {}
            try { allAppts.setVisible(false); } catch (NullPointerException ignored) {}
            dateSelected();
        }
    }

    /** This method will select the appropriate Contact for the Contact ComboBox that is attached to the appointment that is being updated. Which is done by taking the Contact ID attached to an appointment and using it to loop through the list of Contacts to find the match.
     * @param contactID this is the Contact ID that is retrieved from the selected appointment that will be updated. */
    private void selectContact(int contactID) {
        for (Contact cntc : allContacts) {
            if (cntc.getCntcID() == contactID) {
                contact.getSelectionModel().select(cntc);
            }
        }
    }
}