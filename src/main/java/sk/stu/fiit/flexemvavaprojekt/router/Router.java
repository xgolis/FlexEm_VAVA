package sk.stu.fiit.flexemvavaprojekt.router;

import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.view.CvinecView;
import sk.stu.fiit.flexemvavaprojekt.view.LoginView;
import sk.stu.fiit.flexemvavaprojekt.view.View;


import java.io.IOException;

public class Router {

    public static Stage window;

    public static void goTo(RouterEnum routerEnum) throws IOException {
        View view = null;
        if (routerEnum == RouterEnum.LOGINVIEW ) {
            view = new LoginView();
        }
        else if (routerEnum == RouterEnum.CVICENECVIEW ) {
            view = new CvinecView();
        }
        if (view != null)
            view.render();

    }
}