package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Trener;

public class Inicializator {

    public static void inicializujCasChoiceBox(ChoiceBox<String> choiceBox) {
        choiceBox.setValue("600.key");

        choiceBox.getItems().add("600.key");
        choiceBox.getItems().add("700.key");
        choiceBox.getItems().add("800.key");
        choiceBox.getItems().add("900.key");
        choiceBox.getItems().add("1000.key");
        choiceBox.getItems().add("1100.key");
        choiceBox.getItems().add("1200.key");
        choiceBox.getItems().add("1300.key");
        choiceBox.getItems().add("1400.key");
        choiceBox.getItems().add("1500.key");
        choiceBox.getItems().add("1600.key");
        choiceBox.getItems().add("1700.key");
        choiceBox.getItems().add("1800.key");
        choiceBox.getItems().add("1900.key");
        choiceBox.getItems().add("2000.key");
        choiceBox.getItems().add("2100.key");
        choiceBox.getItems().add("2200.key");

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
