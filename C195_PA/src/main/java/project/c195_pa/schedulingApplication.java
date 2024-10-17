package project.c195_pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import project.c195_pa.helper.JDBC;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class creates/launches Scheduling Application.
 * @author Kody Lowes */
public class schedulingApplication extends Application {
    public static ResourceBundle rbL = null;

    /** This is the start method. This method determines the user's default language and selects the appropriate language bundle, calls the JDBC method to connect to the database, and loads the loginPage which is displayed on launch. */
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        String userLocale = Locale.getDefault().getLanguage();
//        userLocale = Locale.FRENCH.getLanguage();

        if (userLocale.equals("en")) {
            rbL = ResourceBundle.getBundle("Lang", new Locale("en_US"));
        } else if (userLocale.equals("fr")) {
            rbL = ResourceBundle.getBundle("Lang", new Locale("fr_FR"));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Your region is not supported by this application.");
            alert.showAndWait();
        }
        JDBC.openConnection();

        FXMLLoader fxmlLoader = new FXMLLoader(schedulingApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    /** This is the main method. This method launches the application. */
    public static void main(String[] args) {
        launch();
    }
}