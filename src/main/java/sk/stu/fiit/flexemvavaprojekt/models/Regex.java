package sk.stu.fiit.flexemvavaprojekt.models;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;

import java.util.ArrayList;

/**
 * Trieda slúži na filtrovanie registrovaných členov v RecepcnaClenoviaView pomocou regexu,
 * ktorý sa vytvorí z napísaných Stringoch v TextFieldoch pre Meno, Priezvisko a RegID a následnému dynamickému
 * vypisovaniu v Tabulke.
 *
 * @author Tomáš Golis
 */

public class Regex {

    /**
     * V danej Metóde sa vytvoria možné regexy z vyžadovaného textu
     * Príklad: Použivateľ (recepčná) zadá do TextFieldu pre meno String: "Ju"
     *          metóda z toho vytvorí regexy: "^Ju.*", "^J..*", "^.u.*"
     * @param text
     * @return ArrayList<String> s možnými regexami
     */
    public static ArrayList<String> vytvorMozneRegexy(String text){
        ArrayList<String> list = new ArrayList<>();
        list.add("^" + text + ".*");
        int dlzka = text.length();

        if (text.length() == 1) {
            list.add("^" + text + ".*");
        }
        else {
            for (int i = 1; i <= dlzka; i++) {
                String temp = text;

                if (i == dlzka) {
                    temp = temp.substring(0, dlzka - 1) + '.';
                } else {
                    temp = temp.substring(0, i - 1) + '.' + temp.substring(i);
                }

                list.add("^" + temp + ".*");

            }

        }
        return list;
    }

    /**
     * Metóda aplikuje predošlú metódu na text v TextFieldoch pre meno a priezvisko,
     * následne z nich vytvorí jednu query
     * @return String query
     */
    public static String vytvorQuery(TextField menoField, TextField priezviskoField, TextField regidField){
        if (menoField.getText().isEmpty() & priezviskoField.getText().isEmpty() & regidField.getText().isEmpty()) {
            return null;
        }
        String meno = "";
        if (!menoField.getText().isEmpty()) {
            meno = "meno ~* '" ;
            ArrayList<String> tmp = vytvorMozneRegexy(menoField.getText());
            for (String zapis: tmp) {
                meno = meno + zapis + "|";
            }
            meno = meno.substring(0, meno.length() - 1) + "'";
        }

        String priezvisko = "";
        if (!priezviskoField.getText().isEmpty()) {
            priezvisko = "priezvisko ~* '" ;
            ArrayList<String> tmp = vytvorMozneRegexy(priezviskoField.getText());
            for (String zapis: tmp) {
                priezvisko = priezvisko + zapis + "|";
            }
            priezvisko = priezvisko.substring(0, priezvisko.length() - 1) + "'";
        }

        String regid = "";
        if (!regidField.getText().isEmpty()) {
            regid = "id = " ;
                regid = regid + regidField.getText();
        }

        String query;
        query = meno;
        if (meno.isEmpty()) {
            query = query + priezvisko;
        }
        else if (!priezvisko.isEmpty()) {
            query = query + " AND " + priezvisko;
        }

        if (query.isEmpty()) {
            query = query + regid;
        }
        else if (!regid.isEmpty()) {
            query = query + " AND " + regid;
        }

        query = "SELECT id, meno, priezvisko, email, telefon FROM treners WHERE "
                + query + " union SELECT id, meno, priezvisko, email, telefon FROM cvicenecs WHERE "
                + query + " ORDER BY meno ";

        return query;
    }

    /**
     * Uloží zápisy z databázy ktoré, vytvorená query vráti, a vypíše ich do tabulky
     * @param query , tabulka a jej stlpce
     */
    public static void regexuj(String query, TableView<Pouzivatel> tabulka, TableColumn<Pouzivatel, String> menoCollum,
                               TableColumn<Pouzivatel, String> priezviskoCollum, TableColumn<Pouzivatel, String> emailCollum,
                               TableColumn<Pouzivatel, String> telefonCollum,TableColumn<Pouzivatel, String> regidCollum) {

        query = ocekujQuery(query);

        ObservableList<Pouzivatel> lanes= FXCollections.observableArrayList();

        ArrayList<Pouzivatel> list = DbConnector.getInstance().getRegexedQuery(query);
        lanes.addAll(list);

        menoCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getMeno()));
        priezviskoCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getPriezvisko()));
        emailCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getEmail()));
        telefonCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTelefonneCislo()));
        regidCollum.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().idToString()));
        tabulka.setItems(lanes);
    }

    public static String ocekujQuery(String query) {
        if (query == null) {
            return  "SELECT id, meno, priezvisko, email, telefon FROM treners " +
                    "union SELECT id, meno, priezvisko, email, telefon FROM cvicenecs ORDER BY meno";
        }
        return query;
    }
}
