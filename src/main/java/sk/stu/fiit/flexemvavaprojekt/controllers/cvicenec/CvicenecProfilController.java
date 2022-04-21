package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
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

    Cvicenec cvicenec = DbConnector.getInstance().getCvicenec(1);

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
//        profilCMenoField.setText(cvicenec.getMeno());
//        profilCPriezivskoField.setText(cvicenec.getPriezvisko());
//        profilCEmailField.setText(cvicenec.getEmail());
//        profilCTelefonField.setText(cvicenec.getTelefonneCislo());
    }




}
