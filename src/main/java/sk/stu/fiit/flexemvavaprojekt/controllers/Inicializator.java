package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.w3c.dom.ls.LSOutput;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Recenzia;
import sk.stu.fiit.flexemvavaprojekt.models.Trener;

import java.util.ArrayList;

public class Inicializator {

    public static void inicializujCasChoiceBox(ChoiceBox<String> choiceBox) {
        choiceBox.setValue(Jazyk.getInstance().naformatujCas("06:00"));

        for (int i = 0; i < 24;i++){
            if (i >=10) {
                choiceBox.getItems().add(Jazyk.getInstance().naformatujCas(i + ":00"));
            }
            else {
                choiceBox.getItems().add(Jazyk.getInstance().naformatujCas("0" + i + ":00"));
            }
        }



    }

    public static void inicializujTrenerovChoiceBox(ChoiceBox<String> choiceBox){
        ArrayList<Pouzivatel> treners = DbConnector.getInstance().getAllTreners();
        System.out.println(treners);
        choiceBox.setValue(treners.get(0).getMeno() + " " + treners.get(0).getPriezvisko());

        for (Pouzivatel trener: treners){
            choiceBox.getItems().add(trener.getMeno() + " " + trener.getPriezvisko());
        }
    }

    public static void inicializujTabulkuTreningovyPlanCvicenci(TableView<Pouzivatel> tabulka,
                                                                TableColumn<Pouzivatel, String> indivTMenoColumn,
                                                                TableColumn<Pouzivatel, String> indivTPriezviskoColumn){

        ArrayList<Pouzivatel> cvicenec = DbConnector.getInstance().getAllCvicenecs();
        ObservableList<Pouzivatel> lanes = FXCollections.observableArrayList();
        lanes.addAll(cvicenec);

        indivTPriezviskoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        indivTMenoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        tabulka.setItems(lanes);
    }

//    public static void inicializujTabulkuRecenzii(TableView<Recenzia> tabulka,
//                                                  TableColumn<Recenzia, String> menoColumn,
//                                                  TableColumn<Recenzia, String> priezviskoColumn,
//                                                  TableColumn<Recenzia, String> sportColumn,
//                                                  TableColumn<Recenzia, String> hvColumn){
//
//        ArrayList<Recenzia> recenzie = DbConnector.getInstance().getAllRecenzias();
//        ObservableList<Recenzia> lanes = FXCollections.observableArrayList();
//        lanes.addAll(recenzie);
//
//        menoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
//        priezviskoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
//
//        tabulka.setItems(lanes);
//
//    }
}


