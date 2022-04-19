package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class TrenerController {

    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void zobrazProfil() {

        try {
            Router.goTo(RouterEnum.TRENERPROFILVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void zobrazPlan() {

        try {
            Router.goTo(RouterEnum.TRENERPLANVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
