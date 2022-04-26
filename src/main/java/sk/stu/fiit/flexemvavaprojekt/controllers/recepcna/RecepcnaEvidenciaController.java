package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecepcnaEvidenciaController implements Initializable {

    @FXML
    private TextField evidenciaRRegIDField;


    @FXML
    private TableView<Cvicenec> evidenciaRTabulka;

    @FXML
    private TableColumn<Cvicenec, String> evidenciaRIdStlpec;

    @FXML
    private TableColumn<Cvicenec, String> evidenciaRMenoStlpec;

    @FXML
    private TableColumn<Cvicenec, String> evidenciaRPriezviskoStlpec;

    @FXML
    private Label actionLabel;




    @FXML
    protected void zaevidovanieOdchodu(){
        if(!validateRegId()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        if (evidenciaRRegIDField.getText().isEmpty()) {
            Cvicenec cvicenec = evidenciaRTabulka.getSelectionModel().getSelectedItem();
            evidenciaRTabulka.getItems().remove(cvicenec);
            DbConnector.getInstance().setInside(cvicenec.getId(), false);


        }
        else {
            for (int i = 0; i <evidenciaRTabulka.getItems().size(); i++) {
                if (evidenciaRTabulka.getItems().get(i).getId() == Integer.parseInt(evidenciaRRegIDField.getText()))   {

                    Cvicenec cvicenec = evidenciaRTabulka.getItems().get(i);
                    evidenciaRTabulka.getItems().remove(i);
                    DbConnector.getInstance().setInside(cvicenec.getId(), false);

                    actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("leaveregged.key"));
                    break;
                }
            }
        }
    }

    @FXML
    protected void zaevidovaniePrichodu(){
        if(!validateRegId()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }

        int id_cvicenca = Integer.parseInt(evidenciaRRegIDField.getText());

        if (DbConnector.getInstance().setInside(id_cvicenca, true)) {
            evidenciaRTabulka.getItems().add(DbConnector.getInstance().getCvicenec(id_cvicenca));
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("enterregged.key"));
        }
        else {
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidid.key"));
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
    protected void novyClen() {

        try {
            Router.goTo(RouterEnum.RECEPCNANOVYCLENVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.RECEPCNARECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void clenovia() {

        try {
            Router.goTo(RouterEnum.RECEPCNACLENOVIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.RECEPCNAMIESTNOSTIVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected void profil() {

        try {
            Router.goTo(RouterEnum.RECEPCNAPROFILVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void nastavHodnotuZTabulky() {
        Cvicenec cvicenec = evidenciaRTabulka.getSelectionModel().getSelectedItem();
        if (cvicenec != null) {
            Inicializator.nastavRegID(evidenciaRRegIDField,cvicenec);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujCviacichCvicencov(evidenciaRTabulka,evidenciaRMenoStlpec,evidenciaRPriezviskoStlpec,
                                                    evidenciaRIdStlpec);



    }

    @FXML
    protected boolean validateRegId(){
        String regId = evidenciaRRegIDField.getText();
        if(!InputValidation.validateREGIDFilter(regId) || !InputValidation.isSqlInjectionSafe(regId)){
            evidenciaRRegIDField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            evidenciaRRegIDField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }
}
