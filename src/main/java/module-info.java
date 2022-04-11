module com.example.flexem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flexem to javafx.fxml;
    exports com.example.flexem;
    exports com.example.flexem.controller;
    opens com.example.flexem.controller to javafx.fxml;
}