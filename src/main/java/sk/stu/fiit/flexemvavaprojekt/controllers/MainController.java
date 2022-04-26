package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;
import java.util.logging.Level;


public class MainController {

    @FXML
    private TextField loginMenoField;

    @FXML
    private TextField loginHesloField;
    @FXML
    private Label actionLabel;


    @FXML
    protected void nastavEN(){
        Jazyk.getInstance().setAktualnyJazyk("EN");
        Main.logger.log(Level.INFO, "Language set to english");
        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, "Error while routing to login");
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void nastavSK(){
        Jazyk.getInstance().setAktualnyJazyk("SK");
        Main.logger.log(Level.INFO, "Language set to slovak");
        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, "Error while routing to login");
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
        String email = loginMenoField.getText();
        String heslo = loginHesloField.getText();

        if(!validateEmail() || !validatePassword()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        actionLabel.setText("");


        Pouzivatel pouzivatel = DbConnector.getInstance().loginOverenie(email,heslo);
        if (pouzivatel != null) {
            Main.logger.log(Level.INFO, "User logged in succesffuly");
            PrihlasenyPouzivatel.getInstance().setPouzivatel(pouzivatel);
            PrihlasenyPouzivatel.getInstance().getPouzivatel().prihlaseniePouzivatela();
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("badpassword.key"));

        }
        else {
            Main.logger.log(Level.INFO, "Could not log user in");
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("badpassword.key"));
        }

//        if (email.equals("recepcna")){
//            PrihlasenyPouzivatel.getInstance().setPouzivatel(new Recepcna(8, "java", "je", "super", "0java0", SpravaHesla.salt(), SpravaHesla.salt()));
//            try {
//                Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//        else if (email.equals("trener")){
//            PrihlasenyPouzivatel.getInstance().setPouzivatel(new Trener(8, "pivo", "je", "super", "0java0", "superman", SpravaHesla.salt(),SpravaHesla.salt()));
//
//            try {
//                Router.goTo(RouterEnum.TRENERROZVRHVIEW);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        else {
//            try {
//                Router.goTo(RouterEnum.CVICENECPLANVIEW);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }
  
    @FXML
    protected boolean validateEmail(){
        String email = loginMenoField.getText();
        if(!InputValidation.validateEmail(email) || !InputValidation.isSqlInjectionSafe(email)){
            Main.logger.log(Level.INFO, "Email validated unsuccessfully");
            loginMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            Main.logger.log(Level.INFO, "Email validated successfully");
            loginMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validatePassword(){
        String password = loginHesloField.getText();
        if(loginHesloField.getText().isEmpty() || !InputValidation.isSqlInjectionSafe(password)){
            Main.logger.log(Level.INFO, "Password validated unsuccessfully");
            loginHesloField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            Main.logger.log(Level.INFO, "Password validated successfully");
            loginHesloField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

}