package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CvicenecProfilController implements Initializable{


    @FXML
    private TextField profilCEmailField;

    @FXML
    private TextField profilCMenoField;

    @FXML
    private TextField profilCPriezivskoField;

    @FXML
    private TextField profilCTelefonField;

    @FXML
    private TextField profilCHeslo1Field;

    @FXML
    private TextField profilCHeslo2Field;

    @FXML
    private Label actionLabel;


    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.CVICENECRECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.CVICENECMIESTNOSTIVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void plan() {

        try {
            Router.goTo(RouterEnum.CVICENECPLANVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Inicializator.inicializujProfil(profilCMenoField, profilCPriezivskoField, profilCEmailField, profilCTelefonField);
    }

    @FXML
    protected void changePassword(){
        if(profilCHeslo1Field.getText().isEmpty() || profilCHeslo2Field.getText().isEmpty()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("fillallfields.key"));
            return;
        }
        if(profilCHeslo1Field.getText().equals(profilCHeslo2Field.getText())){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("samepassword.key"));
            return;
        }
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("passwordchanged.key"));
    }




}
