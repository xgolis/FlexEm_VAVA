package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.Callback;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

/**
 * Trieda slúži na inicializáciu všetkych potrebných objektov z FXML. (Tabuľky, ChoiseBoxy, ComboBoxy, Fieldy...)
 *
 * Väčšina inicializácií prebieha vybraním objektov (Recepcna, Cvicenec, Trener, Skupinovy plan, Individualny plan ...)
 * z databázy a zapísaním požadovaných informácií do Tabuliek, TextFieldov...
 *
 * @author Tomáš Golis
 */
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
        Main.logger.log(Level.INFO, "Treners combo box initialized");
    }

    /**
     * Z datábazy sa uložia informácie o cvičencoch do ArrayListu objektov Cvičenca.
     * Vytvorí sa Observablelist, do sa pridajú a následne vypíšu v Tabulke.
     *
     */
    public static void inicializujTabulkuTreningovyPlanCvicenci(TableView<Pouzivatel> tabulka,
                                                                TableColumn<Pouzivatel, String> indivTMenoColumn,
                                                                TableColumn<Pouzivatel, String> indivTPriezviskoColumn){

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<Cvicenec> cvicenec = DbConnector.getInstance().getTrenersCvicenec(id);
        ObservableList<Pouzivatel> lanes = FXCollections.observableArrayList();
        lanes.addAll(cvicenec);

        indivTPriezviskoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        indivTMenoColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        tabulka.setItems(lanes);
        Main.logger.log(Level.INFO, "Table treningovy plan cvicenec initialized");
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
        Main.logger.log(Level.INFO, "Table recenzii initialized");
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
        cas.setText(Jazyk.getInstance().naformatujDatumACas(sp.getCas().toString()));
        popis.setText(sp.getPopis());
        trener.setText(sp.getTrener());
        Main.logger.log(Level.INFO, "Miestnost vlaues set");
    }

    /**
     *
     * V metóde sa potrebné hodnoty z objektu IndividualnyPlan ip vypíšu v TextFieldoch.
     *
     */
    public static void nastavHodnotyCPlan(TextField datum, TextField nazov, TextField cvik1, TextField cvik2,
                                          TextField cvik3, TextField cvik4, IndividualnyPlan ip){
        datum.setText(Jazyk.getInstance().naformatujDatumACas(ip.getCas().toString()));
        nazov.setText(ip.getPopis());
        cvik1.setText(ip.getCvik1());
        cvik2.setText(ip.getCvik2());
        cvik3.setText(ip.getCvik3());
        cvik4.setText(ip.getCvik4());

        Main.logger.log(Level.INFO, "Values cvicenec plan set");
    }

    public static void nastavHodnotyCRecenzia(TextField miestnost, TextField trener, TextField sport,
                                              SkupinovyPlan sp){
        miestnost.setText(sp.getMiestnost());
        trener.setText(sp.getTrener());
        sport.setText(sp.getSport());
        Main.logger.log(Level.INFO, "Values cvicenec recenzia set");
    }

    public static void nastavRegID(TextField regID,Cvicenec cvicenec) {
        regID.setText(Integer.toString(cvicenec.getId()));
        Main.logger.log(Level.INFO, "Value registration number set");
    }

    public  static void nastavRRecenzie(TextField meno, TextField priezvisko, TextField sport, TextField hviezdy, TextArea popis,
                                        Recenzia recenzia) {
        meno.setText(recenzia.getMeno());
        priezvisko.setText(recenzia.getPriezvisko());
        sport.setText(recenzia.getSport());
        hviezdy.setText(recenzia.pocetHviezdToString());
        popis.setText(recenzia.getPopis());
        Main.logger.log(Level.INFO, "Values recepcna recenzie set");
    };

    public static void inicializujCCvicenia(TableView<IndividualnyPlan> tableView,TableColumn<IndividualnyPlan, String> datum,
                                            TableColumn<IndividualnyPlan, String> nazovTreningu, Boolean done){

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<IndividualnyPlan> individualnyPlans = DbConnector.getInstance().getMyPlanCvicenec(id, done);
        ObservableList<IndividualnyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(individualnyPlans);

        datum.setCellValueFactory(param -> new ReadOnlyStringWrapper(Jazyk.getInstance().naformatujDatumACas(param.getValue().getCas().toString())));
        nazovTreningu.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPopis()));
        tableView.setItems(lanes);
        Main.logger.log(Level.INFO, "Cvicenec cvicenia initialized");

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
        Main.logger.log(Level.INFO, "Cvicenecs inside initialized");


    }

    public static void inicializujSkupinoveTreningy(TableView<SkupinovyPlan> tableView, TableColumn<SkupinovyPlan,String> miestnostColumn,
                                                    TableColumn<SkupinovyPlan,String> trenerColumn,TableColumn<SkupinovyPlan,String> casColumn,
                                                    TableColumn<SkupinovyPlan,String> sportColumn) {

        ArrayList<SkupinovyPlan> skupinovyPlans = DbConnector.getInstance().getUpcomingSkupPlans();
        ObservableList<SkupinovyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(skupinovyPlans);

        miestnostColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        trenerColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTrener()));
        casColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(Jazyk.getInstance().naformatujDatumACas(param.getValue().getCas().toString())));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tableView.setItems(lanes);
        Main.logger.log(Level.INFO, "Skupinove treningy initialized");

        tableView.getSortOrder().add(casColumn);


    }

    public static void inicializujMojeSkupinoveTreningy(TableView<SkupinovyPlan> tableView, TableColumn<SkupinovyPlan,String> miestnostColumn,
                                                    TableColumn<SkupinovyPlan,String> trenerColumn,TableColumn<SkupinovyPlan,String> casColumn,
                                                    TableColumn<SkupinovyPlan,String> sportColumn) {

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<SkupinovyPlan> skupinovyPlans = DbConnector.getInstance().getMySkupPlans(id, false);
        ObservableList<SkupinovyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(skupinovyPlans);

        miestnostColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        trenerColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTrener()));
        casColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(Jazyk.getInstance().naformatujDatumACas(param.getValue().getCas().toString())));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tableView.setItems(lanes);
        Main.logger.log(Level.INFO, "Skupinove treningy cvicenec initialized");

        tableView.getSortOrder().add(casColumn);

    }

    public static void inicializujMojRozvrh(TableView<Plan> tabulka, TableColumn<Plan, String> izbaColumn,
                                            TableColumn<Plan, String> nazovColumn, TableColumn<Plan, String> sportColumn,
                                            TableColumn<Plan, String> datumColumn) {

        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<Plan> plany = DbConnector.getInstance().getMySchedule(id);
        ObservableList<Plan> lanes = FXCollections.observableArrayList();
        lanes.addAll(plany);


        izbaColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        nazovColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getNazov()));
        sportColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        datumColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(Jazyk.getInstance().naformatujDatumACas(param.getValue().getCas().toString())));
        tabulka.setItems(lanes);

        tabulka.getSortOrder().add(datumColumn);
        Main.logger.log(Level.INFO, "My plan trener initialized");

    }

    public static void inicializujProfil(TextField menoField, TextField priezviskoField, TextField emailField,
                                         TextField telefonField) {

        menoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getMeno());
        priezviskoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getPriezvisko());
        emailField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getEmail());
        telefonField.setText(Jazyk.getInstance().naformatujTelefon(PrihlasenyPouzivatel.getInstance().getPouzivatel().getTelefonneCislo()));
        Main.logger.log(Level.INFO, "Profil initialized");
    }

    public static void inicializujTabulkuMiestnosti(TableView<Miestnost> tabulka,
                                                    TableColumn<Miestnost, String> menoStlpec,
                                                    TableColumn<Miestnost, String> kapacitaStlpec,
                                                    TableColumn<Miestnost, String> sportStlpec) {

        ArrayList<Miestnost> miestnosts = DbConnector.getInstance().getMiestnosts();
        ObservableList<Miestnost> lanes = FXCollections.observableArrayList();
        lanes.addAll(miestnosts);

        menoStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getNazov()));
        kapacitaStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getKapacita()));
        sportStlpec.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tabulka.setItems(lanes);
        Main.logger.log(Level.INFO, "Table miestnosts initialized");
    }

    public static void nastavIzbu(TextField izbaField, Miestnost miestnost) {
        izbaField.setText(miestnost.getNazov());
        Main.logger.log(Level.INFO, "Miestnost name set");
    }

    public static void inicializujAbsolvovaneSkupTreningy(TableView<SkupinovyPlan> tabulka,
                                                          TableColumn<SkupinovyPlan,String> miestnost,
                                                          TableColumn<SkupinovyPlan,String> trener,
                                                          TableColumn<SkupinovyPlan,String> sport) {
        int id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        ArrayList<SkupinovyPlan> sp = DbConnector.getInstance().getMySkupPlans(id,true);
        ObservableList<SkupinovyPlan> lanes = FXCollections.observableArrayList();
        lanes.addAll(sp);

        miestnost.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMiestnost()));
        trener.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTrener()));
        sport.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSport()));
        tabulka.setItems(lanes);
        Main.logger.log(Level.INFO, "Done skupinove plans cvicenec initialized");


    }

    public static void nastavHodnotyRPlan(TextField izbaField, TextField sportField, TextField datumField,
                                          TextField popisField, TextField trenerField, SkupinovyPlan sp){

        izbaField.setText(sp.getMiestnost());
        sportField.setText(sp.getSport());
        datumField.setText(Jazyk.getInstance().naformatujDatumACas(sp.getCas().toString()));
        popisField.setText(sp.getPopis());
        trenerField.setText(sp.getTrener());

    }
}


