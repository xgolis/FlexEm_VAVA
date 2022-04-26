package sk.stu.fiit.flexemvavaprojekt.models;

import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;

import java.security.NoSuchAlgorithmException;

/**
 *
 * Singleton trieda, ktorá prislucha práve prihlásenému použivateľovi
 */
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

    public void zmenHeslo(byte[] salt, String heslo) throws NoSuchAlgorithmException {
        PrihlasenyPouzivatel.getInstance().getPouzivatel().setSalt(salt);
        PrihlasenyPouzivatel.getInstance().getPouzivatel().setHash(SpravaHesla.hash(heslo,salt));
        DbConnector.getInstance().setNewPassword(PrihlasenyPouzivatel.getInstance().getPouzivatel().getId(),
                                                    PrihlasenyPouzivatel.getInstance().getPouzivatel());
    }

    public void setPouzivatel(Pouzivatel pouzivatel) {
        this.pouzivatel = pouzivatel;
    }
}
