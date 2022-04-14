package sk.stu.fiit.flexemvavaprojekt.view;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class View  {
    protected Stage window;
    protected Scene scene;


    public abstract void render() throws IOException;

}