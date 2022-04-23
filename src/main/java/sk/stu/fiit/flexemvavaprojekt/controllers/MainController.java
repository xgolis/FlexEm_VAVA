package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;


public class MainController {

    @FXML
    private TextField loginMenoField;

    @FXML
    private TextField loginHesloField;


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
    protected void onEnterPressed(KeyEvent event){
        if (event.getCode().equals(KeyCode.ENTER)) {
            login();
        }
    }
    @FXML
    protected void login() {
        String meno = loginMenoField.getText();

        if (meno.equals("recepcna")){
            PrihlasenyPouzivatel.getInstance().setPouzivatel(new Recepcna(8, "java", "je", "super", "0java0", SpravaHesla.salt(), SpravaHesla.salt()));
            try {
                Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (meno.equals("trener")){
            PrihlasenyPouzivatel.getInstance().setPouzivatel(new Trener(8, "pivo", "je", "super", "0java0", SpravaHesla.salt(), SpravaHesla.salt(), "superman"));

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