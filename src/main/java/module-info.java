module sk.stu.fiit.flexemvavaprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens sk.stu.fiit.flexemvavaprojekt to javafx.fxml;
    exports sk.stu.fiit.flexemvavaprojekt;
    exports sk.stu.fiit.flexemvavaprojekt.controllers;
    opens sk.stu.fiit.flexemvavaprojekt.controllers to javafx.fxml;
}