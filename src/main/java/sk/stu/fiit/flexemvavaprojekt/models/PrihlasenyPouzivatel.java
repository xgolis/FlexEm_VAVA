package sk.stu.fiit.flexemvavaprojekt.models;

import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;

public class PrihlasenyPouzivatel{

    private Pouzivatel pouzivatel;

    public static PrihlasenyPouzivatel single_instance = null;

    private PrihlasenyPouzivatel() {
    }

    public static PrihlasenyPouzivatel getInstance()
    {
        if (single_instance == null)
            single_instance = new PrihlasenyPouzivatel();

        return single_instance;
    }

    public Pouzivatel getPouzivatel() {
        return pouzivatel;
    }

    public void setPouzivatel(Pouzivatel pouzivatel) {
        this.pouzivatel = pouzivatel;
    }
}
