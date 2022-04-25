package sk.stu.fiit.flexemvavaprojekt.models;


import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class Trener extends Pouzivatel {

    private String odbor;

    public Trener(int id, String meno, String priezvisko, String email, String telefon, String odbor, byte[] hash, byte[] salt) {
        super(id, meno, priezvisko, email, telefon, hash, salt);
        this.odbor = odbor;
    }

    public String getOdbor() {
        return odbor;
    }

    public void setOdbor(String odbor) {
        this.odbor = odbor;
    }

    @Override
    public void prihlaseniePouzivatela() {
        try {
            Router.goTo(RouterEnum.TRENERROZVRHVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
