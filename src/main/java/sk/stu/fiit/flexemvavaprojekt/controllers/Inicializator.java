package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.ls.LSOutput;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Trener;

public class Inicializator {

    public static void inicializujCasChoiceBox(ChoiceBox<String> choiceBox) {
        choiceBox.setValue(Jazyk.getInstance().cas("06:00"));

        for (int i = 0; i < 24;i++){
            if (i >=10) {
                choiceBox.getItems().add(Jazyk.getInstance().cas(i + ":00"));
            }
            else {
                choiceBox.getItems().add(Jazyk.getInstance().cas("0" + i + ":00"));
            }
        }



    }

    public static void inicializujTrenerovChoiceBox(ChoiceBox<String> choiceBox){
        int i = 2;
        Trener trener = DbConnector.getInstance().getTrener(i);
        choiceBox.setValue(trener.getMeno() + " " + trener.getPriezvisko());
        do {
            choiceBox.getItems().add(trener.getMeno() + " " + trener.getPriezvisko());
            i++;
            trener = DbConnector.getInstance().getTrener(i);
        } while (trener != null);
    }

    public static void inicializujTabulkuTreningovyPlanCvicenci(TableView<Pouzivatel> tabulka,
                                                                TableColumn<Pouzivatel, String> indivTMenoCollum,
                                                                TableColumn<Pouzivatel, String> indivTPriezviskoCollum){

        int i = 3;
        Pouzivatel cvicenec = DbConnector.getInstance().getCvicenec(i);
        ObservableList<Pouzivatel> lanes= FXCollections.observableArrayList();
        do {
            lanes.add(cvicenec);
            System.out.println(cvicenec);
            i++;
            cvicenec = DbConnector.getInstance().getCvicenec(i);
        } while (cvicenec != null);

        indivTMenoCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        indivTPriezviskoCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        tabulka.setItems(lanes);
    }
}


