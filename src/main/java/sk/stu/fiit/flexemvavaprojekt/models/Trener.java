package sk.stu.fiit.flexemvavaprojekt.models;


public class Trener extends Pouzivatel {

    private String odbor;

    public Trener(int id, String meno, String priezvisko, String email, String telefon, String odbor, byte[] salt, byte[] hash) {
        super(id, meno, priezvisko, email, telefon, salt, hash);
        this.odbor = odbor;
    }

    public String getOdbor() {
        return odbor;
    }

    public void setOdbor(String odbor) {
        this.odbor = odbor;
    }
}

