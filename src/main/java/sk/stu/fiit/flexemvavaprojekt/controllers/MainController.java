package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;


public class MainController {


    @FXML
    private TextField menoField;


    @FXML
    protected void nastavEN(){
        Jazyk.getInstance().setAktualnyJazyk("EN");

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void nastavSK(){
        Jazyk.getInstance().setAktualnyJazyk("SK");

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void login() {

        String meno = menoField.getText();

        if (meno.equals("recepcna")){
            try {
                Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (meno.equals("trener")){
            try {
                Router.goTo(RouterEnum.TRENERROZVRHVIEW);
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