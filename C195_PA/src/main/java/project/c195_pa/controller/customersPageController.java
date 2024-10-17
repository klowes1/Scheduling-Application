package project.c195_pa.controller;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.c195_pa.DAO.appointments;
import project.c195_pa.DAO.countries;
import project.c195_pa.DAO.customers;
import project.c195_pa.DAO.first_level_division;
import project.c195_pa.model.Appointment;
import project.c195_pa.model.Country;
import project.c195_pa.model.Customer;
import project.c195_pa.model.First_Level_Division;
import project.c195_pa.schedulingApplication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.ResourceBundle;

import static project.c195_pa.schedulingApplication.rbL;

/** This class adds functionality to the customersPage.
 * @author Kody Lowes */
public class customersPageController implements Initializable {

    @FXML
    private Tab cstmrTab;
    @FXML
    private TitledPane cstmrTitledPane;
    @FXML
    private Accordion cstmrAccord;
    @FXML
    private TextField cstmID;
    @FXML
    private TextField cstmName;
    @FXML
    private TextField address;
    @FXML
    private TextField pstlCode;
    @FXML
    private TextField phnNum;
    @FXML
    private Button addCstmrBtn;
    @FXML
    private Button updateCstmrBtn;
    @FXML
    private HBox addMenu;
    @FXML
    private Button saveCstmr_A;
    @FXML
    private Button applyCstmr_A;
    @FXML
    private HBox updateMenu;
    @FXML
    private Button saveCstmr_U;
    @FXML
    private Button applyCstmr_U;
    @FXML
    private Text dvsIDT;

    @FXML
    private ComboBox<First_Level_Division> dvsnID;
    @FXML
    private ComboBox<Country> ctryID;
    @FXML
    private Button delCstmrBtn;
    @FXML
    private Button cstmrCnlBtMenu;
    @FXML
    private Text cstmrIDTxt;
    @FXML
    private Text cstmrNmTxt;
    @FXML
    private Text cstmrCntryTxt;
    @FXML
    private Text cstmrAddTxt;
    @FXML
    private Text cstmrPstlTxt;
    @FXML
    private Text cstmrPhnTxt;
    @FXML
    private Button cancelCstmr_U;
    @FXML
    private Button cancelCstmr_A;

    @FXML
    private TableView<Customer> cstmList;
        @FXML
        private TableColumn<Customer, Integer> customerID;
        @FXML
        private TableColumn<Customer, String> addressC;
        @FXML
        private TableColumn<Customer, String> cstmrName;
        @FXML
        private TableColumn<Customer, String> pstlCodeC;
        @FXML
        private TableColumn<Customer, String> phoneC;
        @FXML
        private TableColumn<Customer, LocalDateTime> crtDateC;
        @FXML
        private TableColumn<Customer, String> crtByC;
        @FXML
        private TableColumn<Customer, LocalDateTime> lastUpdtC;
        @FXML
        private TableColumn<Customer, String> updtByC;
        @FXML
        private TableColumn<Customer, Integer> dvsIDC;
        @FXML
        private TableColumn<Customer, String> ctryC;

    ObservableList<Country> allCountries = null;
    ObservableList<First_Level_Division> allFstLvlDvs = null;
    FilteredList<First_Level_Division> allFstLvlDvsFiltered = null;
    ObservableList<Customer> allCustomers = null;
    ObservableList<Appointment> allAppointmentments = null;
    FilteredList<Appointment> cstmAppt = null;
    int ctryIDFilter = 0;

    /** This is the initialize method, which runs when the page is opened. This method sets the text
     * language for all the visual text on the page and populates all the combo boxes and table view. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cstmrTab.setText(rbL.getString("Customers"));
        cstmrTitledPane.setText(rbL.getString("CustomerMenu"));
        cstmrIDTxt.setText(rbL.getString("CustomerID"));
        cstmID.setPromptText(rbL.getString("CustomerID"));
        cstmrNmTxt.setText(rbL.getString("CustomerName"));
        cstmName.setPromptText(rbL.getString("CustomerName"));
        cstmrCntryTxt.setText(rbL.getString("Country"));
        ctryID.setPromptText(rbL.getString("Country"));
        dvsIDT.setText(rbL.getString("DivisionID"));
        dvsnID.setPromptText(rbL.getString("DivisionID"));
        cstmrAddTxt.setText(rbL.getString("Address"));
        address.setPromptText(rbL.getString("Address"));
        cstmrPstlTxt.setText(rbL.getString("PostalCode"));
        pstlCode.setPromptText(rbL.getString("PostalCode"));
        cstmrPhnTxt.setText(rbL.getString("PhoneNumber"));
        phnNum.setPromptText(rbL.getString("PhoneNumber"));
        addCstmrBtn.setText(rbL.getString("Add"));
        updateCstmrBtn.setText(rbL.getString("Update"));
        delCstmrBtn.setText(rbL.getString("Delete"));
        cstmrCnlBtMenu.setText(rbL.getString("Cancel"));
        saveCstmr_U.setText(rbL.getString("Save"));
        saveCstmr_A.setText(rbL.getString("Save"));
        cancelCstmr_U.setText(rbL.getString("Cancel"));
        cancelCstmr_A.setText(rbL.getString("Cancel"));
        applyCstmr_U.setText(rbL.getString("Apply"));
        applyCstmr_A.setText(rbL.getString("Apply"));
        customerID.setText(rbL.getString("CustomerID"));
        cstmrName.setText(rbL.getString("CustomerName"));
        addressC.setText(rbL.getString("Address"));
        pstlCodeC.setText(rbL.getString("PostalCode"));
        phoneC.setText(rbL.getString("Phone"));
        crtDateC.setText(rbL.getString("CreateDate"));
        crtByC.setText(rbL.getString("CreatedBy"));
        lastUpdtC.setText(rbL.getString("LastUpdate"));
        updtByC.setText(rbL.getString("LastUpdatedBy"));
        dvsIDC.setText(rbL.getString("DivisionID"));
        ctryC.setText((rbL.getString("Country")));

        updateCstmLst();

        try { allCountries = countries.getCountries(); } catch (SQLException e) { throw new RuntimeException(e); }
        ctryID.setItems(allCountries);

        try { allFstLvlDvs = first_level_division.getFstLvlDvs(); } catch (SQLException e) { throw new RuntimeException(e); }
    }

    /** This method displays the Customer Menu, sets the add menu visible, and prepares the user input controls for a new customer to be added.
     * @param actionEvent when the add button is clicked/activated. */
    public void addCstmrClicked(ActionEvent actionEvent) {
        saveCstmr_A.setDisable(false);
        cstmrAccord.setMaxHeight(Region.USE_COMPUTED_SIZE);
        cstmrAccord.setVisible(true);
        cstmrTitledPane.setExpanded(true);
        addMenu.setVisible(true);
        updateMenu.setVisible(false);
        Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        apptStage.setHeight(593);
        cstmName.clear();
        address.clear();
        pstlCode.clear();
        phnNum.clear();
        ctryID.getSelectionModel().clearSelection();
        dvsnID.getSelectionModel().clearSelection();
        int cstmListSze = allCustomers.size();
        cstmID.setText(String.valueOf(cstmListSze + 1));
    }

    /** This method displays the Customer menu, sets the update menu visible, and prepares the user input controls by populating them with the selected customer to update the information.
     * @param actionEvent when the update button is clicked/activated. */
    public void updateCstmrClicked(ActionEvent actionEvent) {
        saveCstmr_U.setDisable(false);
        cstmrAccord.setMaxHeight(Region.USE_COMPUTED_SIZE);
        cstmrAccord.setVisible(true);
        cstmrTitledPane.setExpanded(true);
        updateMenu.setVisible(true);
        addMenu.setVisible(false);
        Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        apptStage.setHeight(593);
        cstmName.clear();
        address.clear();
        pstlCode.clear();
        phnNum.clear();
        ctryID.getSelectionModel().clearSelection();
        dvsnID.getSelectionModel().clearSelection();
        try {
            Customer selectedCstm = cstmList.getSelectionModel().getSelectedItem();
            int dvs = selectedCstm.getDvsID();
            selectCountry(dvs);
            selectDvs(dvs);
            cstmID.setText(String.valueOf(selectedCstm.getCstmrID()));
            cstmName.setText(String.valueOf(selectedCstm.getCstmrName()));
            address.setText(String.valueOf(selectedCstm.getAddress()));
            pstlCode.setText(String.valueOf(selectedCstm.getPstCode()));
            phnNum.setText(String.valueOf(selectedCstm.getPhone()));
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("SlctCstmU"));
            alert.showAndWait();
        }
    }

    /** This method will delete the selected customer once the action has been confirmed via the confirmation message, then updates the table view to display the changes made. */
    public void deleteCstmrClicked() throws SQLException {
            allAppointmentments = appointments.getAppointments();
        try {
            Customer selectedCstm = cstmList.getSelectionModel().getSelectedItem();
            cstmAppt = new FilteredList<>(allAppointmentments, appointment -> appointment.getCstmID() == selectedCstm.getCstmrID());
            String customerName = selectedCstm.getCstmrName();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(rbL.getString("DltCstm1") + "\n" + rbL.getString("Customer") + " '" + customerName + ",' " + rbL.getString("DltCstm2") + " " + cstmAppt.size() + " " + rbL.getString("DltCstm3"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    customers.deleteCstm(selectedCstm.getCstmrID());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                updateCstmLst();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("SlctCstmD"));
            alert.showAndWait();
        }
    }

    /** This method will return the user to the Navigation Menu
     * @param actionEvent when the return button is clicked/activated */
    public void returnMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("menuPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    /** This method will add/update a customer based on which menu, Add or Update, is visible, close the Customer Menu, clear the user inputs, and update the table view to display any changes made.
     * @param actionEvent when the save button is clicked/activated */
    public void saveClicked(ActionEvent actionEvent) throws SQLException {
        try {
            int cID = Integer.parseInt(cstmID.getText());
            String cName = cstmName.getText();
            String addy = address.getText();
            String pstl = pstlCode.getText();
            String phNum = phnNum.getText();
            Timestamp crtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String crtBy = project.c195_pa.controller.loginPageController.actvUsr;
            Timestamp updtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String updtBy = project.c195_pa.controller.loginPageController.actvUsr;
            int dvsID = dvsnID.getSelectionModel().getSelectedItem().getDvsID();
            if (addMenu.isVisible() && !cName.isEmpty() && !addy.isEmpty() && !pstl.isEmpty() && !phNum.isEmpty()) {
                try {
                    customers.addCustomer(cID, cName, addy, pstl, phNum, crtDt, crtBy, updtDt, updtBy, dvsID);
                    cstmrTitledPane.setExpanded(false);
                    cstmrAccord.setVisible(false);
                    cstmrAccord.setMaxHeight(0);
                    Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    apptStage.setHeight(319);
                } catch (SQLIntegrityConstraintViolationException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(rbL.getString("CstmExst"));
                    alert.showAndWait();
                }
            } else if (updateMenu.isVisible() && !cName.isEmpty() && !addy.isEmpty() && !pstl.isEmpty() && !phNum.isEmpty()) {
                    customers.updateCustomer(cID, cName, addy, pstl, phNum, updtDt, updtBy, dvsID);
                    cstmrTitledPane.setExpanded(false);
                    cstmrAccord.setVisible(false);
                    cstmrAccord.setMaxHeight(0);
                    Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    apptStage.setHeight(319);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rbL.getString("InvalidInfo"));
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(rbL.getString("VrfInfoP"));
        alert.showAndWait();
        }
        updateCstmLst();
    }

    /** This method will clear the user inputs and close the Customer Menu without making any changes to any customer old/new.
     * @param actionEvent when the cancel button is clicked/activated. */
    public void cancelClicked(ActionEvent actionEvent) {
        cstmrTitledPane.setExpanded(false);
        cstmrAccord.setVisible(false);
        cstmrAccord.setMaxHeight(0);
        Stage apptStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        apptStage.setHeight(319);
        cstmName.clear();
        address.clear();
        pstlCode.clear();
        phnNum.clear();
        ctryID.getSelectionModel().clearSelection();
        dvsnID.getSelectionModel().clearSelection();
    }

    /** This method will add/update a customer based on which menu, Add or Update, is visible and update the table view to display any changes made. */
    public void applyClicked() throws SQLException {
        try {
            int cID = Integer.parseInt(cstmID.getText());
            String cName = cstmName.getText();
            String addy = address.getText();
            String pstl = pstlCode.getText();
            String phNum = phnNum.getText();
            Timestamp crtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String crtBy = project.c195_pa.controller.loginPageController.actvUsr;
            Timestamp updtDt = Timestamp.valueOf(LocalDateTime.now(ZoneOffset.UTC));
            String updtBy = project.c195_pa.controller.loginPageController.actvUsr;
            int dvsID = dvsnID.getSelectionModel().getSelectedItem().getDvsID();
            if (addMenu.isVisible() && !cName.isEmpty() && !addy.isEmpty() && !pstl.isEmpty() && !phNum.isEmpty()) {
                try {
                    customers.addCustomer(cID, cName, addy, pstl, phNum, crtDt, crtBy, updtDt, updtBy, dvsID);
                    saveCstmr_A.setDisable(true);
                } catch (SQLIntegrityConstraintViolationException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(rbL.getString("CstmExst"));
                    alert.showAndWait();
                }
            } else if (updateMenu.isVisible() && !cName.isEmpty() && !addy.isEmpty() && !pstl.isEmpty() && !phNum.isEmpty()) {
                customers.updateCustomer(cID, cName, addy, pstl, phNum, updtDt, updtBy, dvsID);
                saveCstmr_U.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rbL.getString("InvalidInfo"));
                alert.showAndWait();
            }
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(rbL.getString("VrfInfoP"));
            alert.showAndWait();
        }
        updateCstmLst();
    }

    /** This method will get the selected country in the Country ComboBox and filter the Division ComboBox to only show division within the selected country.
     * <p>LAMBDA: The lambda expression used in this method is used to create a filtered list by taking all the Divisions and getting the Country ID from each one of them and only adds the Divisions with the same Country ID as the Country selected in the Country ComboBox. Which allows for an easy way to filter the list of divisions that the user will select from.</p> */
    public void ctrySelected() {
        if (ctryID.getSelectionModel().getSelectedItem() != null) {
            ctryIDFilter = ctryID.getSelectionModel().getSelectedItem().getCtryID();
            allFstLvlDvsFiltered = new FilteredList<>(allFstLvlDvs, firstLevelDivision -> firstLevelDivision.getCtryIDF() == ctryIDFilter);
            if (ctryIDFilter != 0) {
                dvsnID.setItems(allFstLvlDvsFiltered);
                dvsnID.setVisible(true);
                dvsIDT.setVisible(true);
            }
        }
    }

    /** This method will populate the Country ComboBox by getting the inputted Division ID and looping through all the Divisions to find the Division with the matched Division ID. Then takes the matched Division and gets the Country ID which is then looped through the list of Countries to find the Country with the matching Country ID. Finally, sets the Country ComboBox value to the matched Country and filters the Division ComboBox.
     * <p>LAMBDA: The lambda expression used in this method is used to create a filtered list by taking all the Divisions and getting the Country ID from each one of them and only adds the Divisions with the same Country ID as the Country selected in the Country ComboBox. Which allows for an easy way to filter the list of divisions that the user will select from.</p>
     * @param dvsCode the Division Code that will be used to find the correct Country to select in the Country ComboBox. */
    private void selectCountry(int dvsCode) {
        for (First_Level_Division fstLvlDvs : allFstLvlDvs) {
            if (fstLvlDvs.getDvsID() == dvsCode) {
                for (Country country : allCountries) {
                    if (country.getCtryID() == fstLvlDvs.getCtryIDF()) {
                        ctryID.getSelectionModel().select(country);
                        ctryIDFilter = country.getCtryID();
                        allFstLvlDvsFiltered = new FilteredList<>(allFstLvlDvs, firstLevelDivision -> firstLevelDivision.getCtryIDF() == ctryIDFilter);
                        dvsnID.setItems(allFstLvlDvsFiltered);
                    }
                }
            }
        }
    }

    /** This method will populate the Division ComboBox by getting the inputted Division ID and looping through the list of Divisions to find the Division with the matching Division ID. Then select that Division as the Value for the Division ComboBox.
     * @param dvsCode the Division ID that will be used to find the matching Division to select in the Division ComboBox. */
    private void selectDvs(int dvsCode) {
        for (First_Level_Division fstLvlDvs : allFstLvlDvsFiltered) {
            if (fstLvlDvs.getDvsID() == dvsCode) {
                dvsnID.getSelectionModel().select(fstLvlDvs);
            }
        }
    }

    /** This method will update the Customer TableView by retrieving the list of customers from the database using the getAllCstmLst method from the customers class. */
    private void updateCstmLst() {
        try { allCustomers = customers.getAllCstm(); } catch (SQLException e) { throw new RuntimeException(e); }
        cstmList.setItems(allCustomers);
        customerID.setCellValueFactory(new PropertyValueFactory<>("cstmrID"));
        cstmrName.setCellValueFactory(new PropertyValueFactory<>("cstmrName"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        pstlCodeC.setCellValueFactory(new PropertyValueFactory<>("pstCode"));
        phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));
        crtDateC.setCellValueFactory(new PropertyValueFactory<>("created"));
        crtByC.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdtC.setCellValueFactory(new PropertyValueFactory<>("lstUpdt"));
        updtByC.setCellValueFactory(new PropertyValueFactory<>("updtBy"));
        dvsIDC.setCellValueFactory(new PropertyValueFactory<>("dvsID"));
        ctryC.setCellValueFactory(new PropertyValueFactory<>("ctry"));
    }
}