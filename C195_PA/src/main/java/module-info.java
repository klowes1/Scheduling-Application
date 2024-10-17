module project.c195_pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens project.c195_pa to javafx.fxml;
    exports project.c195_pa;
    exports project.c195_pa.controller;
    exports project.c195_pa.model;
    opens project.c195_pa.controller to javafx.fxml;
    opens project.c195_pa.model to javafx.fxml;
}