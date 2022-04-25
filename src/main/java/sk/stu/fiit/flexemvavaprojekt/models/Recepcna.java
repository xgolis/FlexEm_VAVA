package sk.stu.fiit.flexemvavaprojekt.models;


import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;

public class Recepcna extends Pouzivatel {

    public Recepcna(int id, String meno, String priezvisko, String email, String telefon, byte[] hash, byte[] salt) {
        super(id, meno, priezvisko, email, telefon, hash, salt);
    }

    @Override
    public void prihlaseniePouzivatela() {
        try {
            Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
