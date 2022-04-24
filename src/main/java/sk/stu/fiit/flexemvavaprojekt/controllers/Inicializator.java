package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.w3c.dom.ls.LSOutput;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;

import java.sql.Timestamp;
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

    public static void inicializujTabulkuRecenzii(TableView<Recenzia> tabulka,
                                                  TableColumn<Recenzia, String> menoColumn,
                                                  TableColumn<Recenzia, String> priezviskoColumn,
                                                  TableColumn<Recenzia, String> sportColumn,
                                                  TableColumn<Recenzia, String> hvColumn, int trenerId){
        String dodatocneQuery = "";
        if (trenerId !=0) {
            dodatocneQuery = dodatocneQuery + "WHERE treners.id = " + trenerId;
        }

        ArrayList<Recenzia> recenzie = DbConnector.getInstance().getAllRecenzias(dodatocneQuery);
        ObservableList<Recenzia> lanes = FXCollections.observableArrayList();
        lanes.addAll(recenzie);

        menoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        priezviskoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        hvColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().pocetHviezdToString()));

        tabulka.setItems(lanes);

    }

    public static void inicializujTabulkuTreningov(TableView<SkupinovyPlan> tabulka,
                                                  TableColumn<SkupinovyPlan, String> miestnostColumn,
                                                  TableColumn<SkupinovyPlan, String> trenerColumn,
                                                  TableColumn<SkupinovyPlan, String> datumACasColumn,
                                                  TableColumn<SkupinovyPlan, String> Sport){


    }

    public static void nastavHodnotyCMiestnost(TextField izba, TextField sport, TextField cas, TextField popis, TextField trener, SkupinovyPlan sp){
        izba.setText(sp.getMiestnost());
        sport.setText(sp.getSport());
        cas.setText(sp.getCas().toString());
        popis.setText(sp.getPopis());
        trener.setText(Integer.toString(sp.getTrenerId()));

    }


    public static void inicializujCviacichCvicencov(TableView<Cvicenec> tableView, TableColumn<Cvicenec,String> menoColumn,
                                                    TableColumn<Cvicenec,String> priezviskoColumn,TableColumn<Cvicenec,String> regIDColumn) {

        ArrayList<Cvicenec> cvicenec = DbConnector.getInstance().getInside();
        ObservableList<Cvicenec> lanes = FXCollections.observableArrayList();
        lanes.addAll(cvicenec);

        regIDColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().idToString()));
        priezviskoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        menoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        tableView.setItems(lanes);


    }

    public static void inicializujSkupinoveTreningy(TableView<SkupinovyPlan> tableView, TableColumn<SkupinovyPlan,String> miestnostColumn,
                                                    TableColumn<SkupinovyPlan,String> trenerColumn,TableColumn<SkupinovyPlan,String> casColumn,
                                                    TableColumn<SkupinovyPlan,String> sportColumn) {

        ArrayList<SkupinovyPlan> skupinovyPlans = DbConnector.getInstance().getUpcomingSkupPlans();
        ObservableList<SkupinovyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(skupinovyPlans);

        miestnostColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        trenerColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTrener()));
        casColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCas().toString()));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tableView.setItems(lanes);


    }

    public static void inicializujMojeSkupinoveTreningy(TableView<SkupinovyPlan> tableView, TableColumn<SkupinovyPlan,String> miestnostColumn,
                                                    TableColumn<SkupinovyPlan,String> trenerColumn,TableColumn<SkupinovyPlan,String> casColumn,
                                                    TableColumn<SkupinovyPlan,String> sportColumn) {

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<SkupinovyPlan> skupinovyPlans = DbConnector.getInstance().getMyUpcomingSkupPlans(id);
        ObservableList<SkupinovyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(skupinovyPlans);

        miestnostColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        trenerColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTrener()));
        casColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCas().toString()));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tableView.setItems(lanes);


    }
}


