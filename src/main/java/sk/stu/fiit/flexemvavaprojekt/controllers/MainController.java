package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class MainController {

    @FXML
    protected void login() {

        try {
            Router.goTo(RouterEnum.CVICENECVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}