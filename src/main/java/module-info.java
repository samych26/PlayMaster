module PlayMaster {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
    requires java.desktop;


    opens com.example.playmaster to javafx.fxml;
    opens com.example.playmaster.Controllers to javafx.fxml;
    opens com.example.playmaster.Views to javafx.fxml;
    opens com.example.playmaster.Controllers.Admin to javafx.fxml;
    opens com.example.playmaster.Controllers.Employe to javafx.fxml;
        exports com.example.playmaster;
        exports com.example.playmaster.Controllers;
        exports com.example.playmaster.Controllers.Admin;
        exports com.example.playmaster.Controllers.Employe;
        exports com.example.playmaster.Models;
        exports com.example.playmaster.Views;

}