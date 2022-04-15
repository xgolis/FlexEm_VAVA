package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField menofield;
    @FXML
    protected void login() {

        String meno = new String(menofield.getText());

        if (meno.equals("recepcna")){
            try {
                Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (meno.equals("trener")){
            try {
                Router.goTo(RouterEnum.TRENERPLANVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                Router.goTo(RouterEnum.CVICENECPLANVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}