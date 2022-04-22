package sk.stu.fiit.flexemvavaprojekt.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Cvicenec extends Pouzivatel{

    private int skupinovyPlanId;
    private int individualnyPlanId;

    public Cvicenec(int id, String meno, String priezvisko, String email, String telefon, String heslo, int skupinovyPlanId, int individualnyPlanId) {
        super(id, meno, priezvisko, email, telefon, heslo);
        this.skupinovyPlanId = skupinovyPlanId;
        this.individualnyPlanId = individualnyPlanId;
    }

    public int getSkupinovyPlanId() {
        return skupinovyPlanId;
    }

    public int getIndividualnyPlanId() {
        return individualnyPlanId;
    }

    public void setSkupinovyPlanId(int skupinovyPlanId) {
        this.skupinovyPlanId = skupinovyPlanId;
    }

    public void setIndividualnyPlanId(int individualnyPlanId) {
        this.individualnyPlanId = individualnyPlanId;
    }
}

