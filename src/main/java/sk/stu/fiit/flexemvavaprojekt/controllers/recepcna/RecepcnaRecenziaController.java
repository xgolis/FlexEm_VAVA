package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.Recenzia;
import sk.stu.fiit.flexemvavaprojekt.models.Recepcna;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class RecepcnaRecenziaController implements Initializable {

    @FXML
    private TextField recenziaRHviezdyField;

    @FXML
    private TextField recenziaRMenoField;

    @FXML
    private TextField recenziaRPriezviskoField;

    @FXML
    private TextArea recenziaRRecenziaArea;

    @FXML
    private TextField recenziaRSportFIeld;

    @FXML
    private Label actionLabel;

    @FXML
    private TableColumn<Recenzia, String> recenziaRSportStlpec;

    @FXML
    private TableView<Recenzia> recenziaRTabulka;

    @FXML
    private TableColumn<Recenzia, String> recenziaRPriezviskoStlpec;

    @FXML
    private TableColumn<Recenzia, String> recenziaRMenoStlpec;

    @FXML
    private TableColumn<Recenzia, String> recenziaRHvStlpec;

    @FXML
    private Label labelExport;


    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to login view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void novyClen() {

        try {
            Router.goTo(RouterEnum.RECEPCNANOVYCLENVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to novy clen view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void clenovia() {

        try {
            Router.goTo(RouterEnum.RECEPCNACLENOVIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna clenovia view", e);
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.RECEPCNAMIESTNOSTIVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna miestnosti view", e);
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected void profil() {

        try {
            Router.goTo(RouterEnum.RECEPCNAPROFILVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna profil view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void evidenciaVstupu() {

        try {
            Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna evidencia view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void sendReview(){
        Recenzia recenziaO = recenziaRTabulka.getSelectionModel().getSelectedItem();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element recenzia = doc.createElement("Recenzia");
            doc.appendChild(recenzia);

            Element meno = doc.createElement("MenoCvičenca");
            meno.appendChild(doc.createTextNode(recenziaO.getMeno()));
            recenzia.appendChild(meno);

            Element sport = doc.createElement("Sport");
            sport.appendChild(doc.createTextNode(recenziaO.getSport()));
            recenzia.appendChild(sport);

            Element pocetHviezd = doc.createElement("Hodnotenie");
            pocetHviezd.appendChild(doc.createTextNode(recenziaRHviezdyField.getText()));
            recenzia.appendChild(pocetHviezd);

            Element popis = doc.createElement("PopisHodnotenia");
            popis.appendChild(doc.createTextNode(recenziaO.getPopis()));
            recenzia.appendChild(popis);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("recenzia.xml"));
            transformer.transform(source, result);

            labelExport.setText(Jazyk.getInstance().prelozeneSlovo("successfulexport.key"));

        } catch (ParserConfigurationException | TransformerException e) {
            labelExport.setText(Jazyk.getInstance().prelozeneSlovo("unsuccessfulexport.key"));
            e.printStackTrace();
        }
    }


    @FXML
    protected void nastavHodnotyZTabulky() {
        Recenzia recenzia = recenziaRTabulka.getSelectionModel().getSelectedItem();
        if (recenzia != null) {
            Inicializator.nastavRRecenzie(recenziaRMenoField,recenziaRPriezviskoField,recenziaRSportFIeld,recenziaRHviezdyField,
                                          recenziaRRecenziaArea, recenzia);
        }
        labelExport.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujTabulkuRecenzii(recenziaRTabulka, recenziaRMenoStlpec, recenziaRPriezviskoStlpec, recenziaRSportStlpec, recenziaRHvStlpec, 0);
    }

}
