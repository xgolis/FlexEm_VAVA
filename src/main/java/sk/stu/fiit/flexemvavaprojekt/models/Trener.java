package sk.stu.fiit.flexemvavaprojekt.models;

public class Trener extends Pouzivatel {

    private String odbor;

    public Trener(int id, String meno, String priezvisko, String email, String telefon, String heslo, String odbor) {
        super(id, meno, priezvisko, email, telefon, heslo);
        this.odbor = odbor;
    }

    public String getOdbor() {
        return odbor;
    }

    public void setOdbor(String odbor) {
        this.odbor = odbor;
    }
}

