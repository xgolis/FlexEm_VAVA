module com.example.flexem {
    requires javafx.controls;
    requires javafx.fxml;


    opens sk.stu.fiit.flexem to javafx.fxml;
    exports sk.stu.fiit.flexem;
    exports sk.stu.fiit.flexem.controller;
    opens sk.stu.fiit.flexem.controller to javafx.fxml;
}