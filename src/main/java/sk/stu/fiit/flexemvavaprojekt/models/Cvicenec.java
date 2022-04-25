package sk.stu.fiit.flexemvavaprojekt.models;


import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class Cvicenec extends Pouzivatel{

    private Integer trener_id;

    public Cvicenec(int id, String meno, String priezvisko, String email, String telefon, Integer trener_id, byte[] hash, byte[] salt) {
        super(id, meno, priezvisko, email, telefon, hash, salt);

        this.trener_id = trener_id;
    }

    public Integer getTrener_id() {
        return trener_id;
    }

    public void setTrener_id(Integer trener_id) {
        this.trener_id = trener_id;
    }

    @Override
    public void prihlaseniePouzivatela() {
        try {
            Router.goTo(RouterEnum.CVICENECPLANVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

