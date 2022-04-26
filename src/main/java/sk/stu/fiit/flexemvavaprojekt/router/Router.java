package sk.stu.fiit.flexemvavaprojekt.router;

import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.view.*;


import java.io.IOException;
import java.util.logging.Level;

public class Router {

    public static Stage window;

    public static void goTo(RouterEnum routerEnum) throws IOException {
        View view = null;
        if (routerEnum == RouterEnum.LOGINVIEW ) {
            Main.logger.log(Level.INFO, "Routing to login view");
            view = new LoginView();
        }
        else if (routerEnum == RouterEnum.CVICENECPLANVIEW ) {
            Main.logger.log(Level.INFO, "Routing to cvicenec plan view");
            view = new CvicenecPlanView();
        }
        else if (routerEnum == RouterEnum.CVICENECRECENZIAVIEW){
            Main.logger.log(Level.INFO, "Routing to cvicenec recenzia view");
            view = new CvicenecRecenziaView();
        }
        else if (routerEnum == RouterEnum.CVICENECPROFILVIEW){
            Main.logger.log(Level.INFO, "Routing to cvicenec profil view");
            view = new CvicenecProfilView();
        }
        else if (routerEnum == RouterEnum.RECEPCNAEVIDENCIAVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna evidencia view");
            view = new RecepcnaEvidenciaView();
        }
        else if (routerEnum == RouterEnum.RECEPCNAPROFILVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna profil view");
            view = new RecepcnaProfilView();
        }
        else if (routerEnum == RouterEnum.RECEPCNARECENZIAVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna recenzia view");
            view = new RecepcnaRecenziaView();
        }
        else if (routerEnum == RouterEnum.RECEPCNANOVYCLENVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna novy clen view");
            view = new RecepcnaNovyClenView();
        }
        else if (routerEnum == RouterEnum.RECEPCNANOVYTRENERVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna novy trener view");
            view = new RecepcnaNovyTrenerView();
        }
        else if (routerEnum == RouterEnum.TRENERPROFILVIEW){
            Main.logger.log(Level.INFO, "Routing to trener profil view");
            view = new TrenerProfilView();
        }
        else if (routerEnum == RouterEnum.TRENERRECENZIAVIEW){
            Main.logger.log(Level.INFO, "Routing to trener recenzia view");
            view = new TrenerRecenziaView();
        }
        else if (routerEnum == RouterEnum.TRENERROZVRHVIEW){
            Main.logger.log(Level.INFO, "Routing to trener rozvrh view");
            view = new TrenerRozvrhView();
        }
        else if (routerEnum == RouterEnum.TRENERINDIVIDUALNYVIEW){
            Main.logger.log(Level.INFO, "Routing to trener individualny view");
            view = new TrenerIndividualnyView();
        }
        else if (routerEnum == RouterEnum.TRENERSKUPINOVYVIEW){
            Main.logger.log(Level.INFO, "Routing to trener skupinovy view");
            view = new TrenerSkupinovyView();
        }
        else if (routerEnum == RouterEnum.RECEPCNAMIESTNOSTIVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna miestnost view");
            view = new RecepcnaMiestnostiView();
        }
        else if (routerEnum == RouterEnum.RECEPCNACLENOVIAVIEW){
            Main.logger.log(Level.INFO, "Routing to recepcna clenovia view");
            view = new RecepcnaClenoviaView();
        }
        else if (routerEnum == RouterEnum.CVICENECMIESTNOSTIVIEW){
            Main.logger.log(Level.INFO, "Routing to cvicenec miestnosti view");
            view = new CvicenecMiestnostiView();
        }
        if (view != null)
            Main.logger.log(Level.INFO, "Rendering routed view");
            view.render();

    }
}