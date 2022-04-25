package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;

import java.util.ArrayList;
import java.util.Collections;

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


    public static void inicializujTrenerovComboBox(ComboBox<Pouzivatel> comboBox){

        Callback<ListView<Pouzivatel>, ListCell<Pouzivatel>> cellFactory = lv -> new ListCell<Pouzivatel>() {
            @Override
            protected void updateItem(Pouzivatel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : (item.getMeno() + " " + item.getPriezvisko()));
            }
        };


        comboBox.setCellFactory(cellFactory);
        comboBox.setButtonCell(cellFactory.call(null));

        ArrayList<Pouzivatel> treneri = DbConnector.getInstance().getAllTreners();
        ObservableList<Pouzivatel> list = FXCollections.observableArrayList();
        list.addAll(treneri);

        comboBox.getItems().addAll(list);
        comboBox.getSelectionModel().select(1);
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

    public static void nastavHodnotyCPlan(TextField datum, TextField nazov, TextField cvik1, TextField cvik2,
                                          TextField cvik3, TextField cvik4, IndividualnyPlan ip){
        datum.setText(ip.getCas().toString());
        nazov.setText(ip.getPopis());
        cvik1.setText(ip.getCvik1());
        cvik2.setText(ip.getCvik2());
        cvik3.setText(ip.getCvik3());
        cvik4.setText(ip.getCvik4());


    }

    public static void nastavRegID(TextField regID,Cvicenec cvicenec) {
        regID.setText(Integer.toString(cvicenec.getId()));
    }

    public  static void nastavRRecenzie(TextField meno, TextField priezvisko, TextField sport, TextField hviezdy, TextArea popis,
                                        Recenzia recenzia) {
        meno.setText(recenzia.getMeno());
        priezvisko.setText(recenzia.getPriezvisko());
        sport.setText(recenzia.getSport());
        hviezdy.setText(recenzia.pocetHviezdToString());
        popis.setText(recenzia.getPopis());
    };

    public static void inicializujCCvicenia(TableView<IndividualnyPlan> tableView,TableColumn<IndividualnyPlan, String> datum,
                                            TableColumn<IndividualnyPlan, String> nazovTreningu, Boolean done){

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<IndividualnyPlan> individualnyPlans = DbConnector.getInstance().getMyPlanCvicenec(id, done);
        ObservableList<IndividualnyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(individualnyPlans);

        datum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCas().toString()));
        nazovTreningu.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPopis()));
        tableView.setItems(lanes);

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

    public static void inicializujMojRozvrh(TableView<Plan> tabulka, TableColumn<Plan, String> izbaColumn,
                                            TableColumn<Plan, String> nazovColumn, TableColumn<Plan, String> sportColumn,
                                            TableColumn<Plan, String> datumColumn) {

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<Plan> plany = DbConnector.getInstance().getMySchedule(id);
        ObservableList<Plan> lanes = FXCollections.observableArrayList();
        lanes.addAll(plany);

        Collections.sort(plany);

        izbaColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
//        nazovColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        datumColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCas().toString()));
        tabulka.setItems(lanes);

    }

    public static void inicializujProfil(TextField menoField, TextField priezviskoField, TextField emailField,
                                         TextField telefonField) {

        menoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getMeno());
        priezviskoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getPriezvisko());
        emailField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getEmail());
        telefonField.setText(Jazyk.getInstance().naformatujTelefon(PrihlasenyPouzivatel.getInstance().getPouzivatel().getTelefonneCislo()));
    }
}


