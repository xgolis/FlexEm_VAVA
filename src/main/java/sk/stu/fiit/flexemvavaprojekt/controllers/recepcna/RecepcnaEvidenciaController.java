package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;


import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
    private TableView<Pouzivatel> evidenciaRTabulka;

    @FXML
    private TableColumn<Pouzivatel, String> evidenciaRIdStlpec;

    @FXML
    private TableColumn<Pouzivatel, String> evidenciaRMenoStlpec;

    @FXML
    private TableColumn<Pouzivatel, String> evidenciaRPriezviskoStlpec;

    @FXML
    private Label actionLabel;




    @FXML
    protected void zaevidovanieOdchodu(){
        if(!validateRegId()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        if (evidenciaRRegIDField.getText().isEmpty()) {
            evidenciaRTabulka.getItems().remove(evidenciaRTabulka.getSelectionModel().getSelectedItem());

        }
        else {
            for (int i = 0; i <evidenciaRTabulka.getItems().size(); i++) {
                if (evidenciaRTabulka.getItems().get(i).getId() == Integer.parseInt(evidenciaRRegIDField.getText()))   {
                    evidenciaRTabulka.getItems().remove(i);
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
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("enterregged.key"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pouzivatel cvicenec = new Cvicenec(2,"fero","nehe","asd","49",SpravaHesla.salt(),SpravaHesla.salt());
        Pouzivatel cvicenec2 =  new Cvicenec(2,"iny fero","nehe","asd","49",SpravaHesla.salt(),SpravaHesla.salt());
        Pouzivatel cvicenec3 =  new Cvicenec(2,"zase iny fero","nehe","asd","49",SpravaHesla.salt(),SpravaHesla.salt());


        ObservableList<Pouzivatel> lanes=FXCollections.observableArrayList();
        lanes.add(cvicenec);
        lanes.add(cvicenec2);
        lanes.add(cvicenec3);

        evidenciaRIdStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().idToString()));
        evidenciaRPriezviskoStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        evidenciaRMenoStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        evidenciaRTabulka.setItems(lanes);

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
