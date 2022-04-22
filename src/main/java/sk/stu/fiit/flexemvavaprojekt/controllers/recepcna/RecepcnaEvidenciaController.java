package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;


import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
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
        Pouzivatel cvicenec = new Cvicenec(2,"fero","nehe","asd","989","tajne",2,3);
        Pouzivatel cvicenec2 = new Cvicenec(3,"iny fero","nehe","asd","989","tajne",2,3);
        ObservableList<Pouzivatel> lanes=FXCollections.observableArrayList();
        lanes.add(cvicenec);
        lanes.add(cvicenec2);

        evidenciaRIdStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().idToString()));
        evidenciaRPriezviskoStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        evidenciaRMenoStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));

        evidenciaRTabulka.setItems(lanes);

        evidenciaRRegIDField.setText("id = regid?");
    }
}
