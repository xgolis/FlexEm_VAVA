package sk.stu.fiit.flexemvavaprojekt.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;
import java.util.Locale;


public class MainController {


    ObservableList<String> jazykyList = FXCollections.observableArrayList("SK","EN");
    @FXML
    private TextField menoField;

    @FXML
    private ChoiceBox<String> jazykChoiceBox;


    @FXML
    protected void zmenaJazyka(){
        if (jazykChoiceBox.getValue().equals("EN")){
            System.out.println("anglictina");
        }
        else {
            System.out.println("slovencina");
        }

    }
    @FXML
    protected void login() {

        String meno = menoField.getText();

        if (meno.equals("recepcna")){
            try {
                Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if (meno.equals("trener")){
            try {
                Router.goTo(RouterEnum.TRENERPLANVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                Router.goTo(RouterEnum.CVICENECPLANVIEW);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    private void initialize(){
        jazykChoiceBox.setValue("SK");
        jazykChoiceBox.setItems(jazykyList);
    }


    public void nastavEN(ActionEvent actionEvent) throws IOException {
        Locale locale = new Locale("EN");
//        nacitajView(locale);
    }

    public void nastavSK(ActionEvent actionEvent){
        Locale locale = new Locale("SK");
    }

//    public View nacitajView(Locale locale) throws IOException {
//
//
//    }
}