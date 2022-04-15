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
        if (view != null)
            view.render();

    }
}