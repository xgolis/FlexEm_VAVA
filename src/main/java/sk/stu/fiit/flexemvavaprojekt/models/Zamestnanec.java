package sk.stu.fiit.flexemvavaprojekt.models;

public abstract class Zamestnanec extends Pouzivatel{
    public Zamestnanec(int id, String meno, String priezvisko, String email, String telefon, String heslo, String odbor) {
        super(id, meno, priezvisko, email, telefon, heslo, odbor);
    }
}
