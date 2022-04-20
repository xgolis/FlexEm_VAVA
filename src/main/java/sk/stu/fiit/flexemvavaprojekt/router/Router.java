package sk.stu.fiit.flexemvavaprojekt.router;

import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.view.*;


import java.io.IOException;

public class Router {

    public static Stage window;

    public static void goTo(RouterEnum routerEnum) throws IOException {
        View view = null;
        if (routerEnum == RouterEnum.LOGINVIEW ) {
            view = new LoginView();
        }
        else if (routerEnum == RouterEnum.CVICENECPLANVIEW ) {
            view = new CvicenecPlanView();
        }
        else if (routerEnum == RouterEnum.CVICENECRECENZIAVIEW){
            view = new CvicenecRecenziaView();
        }
        else if (routerEnum == RouterEnum.CVICENECPROFILVIEW){
            view = new CvicenecProfilView();
        }
        else if (routerEnum == RouterEnum.RECEPCNAEVIDENCIAVIEW){
            view = new RecepcnaEvidenciaView();
        }
        else if (routerEnum == RouterEnum.RECEPCNAPROFILVIEW){
            view = new RecepcnaProfilView();
        }
        else if (routerEnum == RouterEnum.RECEPCNARECENZIAVIEW){
            view = new RecepcnaRecenziaView();
        }
        else if (routerEnum == RouterEnum.RECEPCNANOVYCLENVIEW){
            view = new RecepcnaNovyClenView();
        }
        else if (routerEnum == RouterEnum.RECEPCNANOVYTRENERVIEW){
            view = new RecepcnaNovyTrenerView();
        }
        else if (routerEnum == RouterEnum.TRENERPROFILVIEW){
            view = new TrenerProfilView();
        }
        else if (routerEnum == RouterEnum.TRENERRECENZIAVIEW){
            view = new TrenerRecenziaView();
        }
        else if (routerEnum == RouterEnum.TRENERROZVRHVIEW){
            view = new TrenerRozvrhView();
        }
        else if (routerEnum == RouterEnum.TRENERINDIVIDUALNYVIEW){
            view = new TrenerIndividualnyView();
        }
        else if (routerEnum == RouterEnum.TRENERSKUPINOVYVIEW){
            view = new TrenerSkupinovyView();
        }
        if (view != null)
            view.render();

    }
}