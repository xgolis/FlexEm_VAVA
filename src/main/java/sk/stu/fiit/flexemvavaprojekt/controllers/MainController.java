package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
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
        System.out.println(Jazyk.getInstance().cas("22:00"));

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