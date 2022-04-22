package sk.stu.fiit.flexemvavaprojekt.models;


public class Recepcna extends Pouzivatel {

    public Recepcna(int id, String meno, String priezvisko, String email, String telefon, byte[] salt, byte[] hash) {
        super(id, meno, priezvisko, email, telefon, salt, hash);
    }

}
