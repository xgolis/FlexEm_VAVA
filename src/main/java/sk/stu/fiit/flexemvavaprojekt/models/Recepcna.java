package sk.stu.fiit.flexemvavaprojekt.models;


public class Recepcna extends Pouzivatel {

    public Recepcna(int id, String meno, String priezvisko, String email, String telefon, byte[] hash, byte[] salt) {
        super(id, meno, priezvisko, email, telefon, hash, salt);
    }

}
