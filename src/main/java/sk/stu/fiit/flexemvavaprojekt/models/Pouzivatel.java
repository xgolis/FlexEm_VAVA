package sk.stu.fiit.flexemvavaprojekt.models;

public abstract class Pouzivatel {

    private final int id;
    private String odbor;
    private String heslo;
    private String email;
    private final String meno;
    private final String priezvisko;
    private final String telefonneCislo;
    private int skupinovyPlanId;


    public Pouzivatel(int id, String meno, String priezvisko, String email, String telefon, String heslo, String odbor) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.telefonneCislo = telefon;
        this.heslo = heslo;
        this.odbor = odbor;
    }

    public Pouzivatel(int id, String meno, String priezvisko, String email, String telefon, String heslo, int skupinovyPlanId) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.telefonneCislo = telefon;
        this.heslo = heslo;
        this.skupinovyPlanId = skupinovyPlanId;
    }

    //heslo dat ako char[] a nie String

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public String getTelefonneCislo() {
        return telefonneCislo;
    }

    public int getId() {
        return id;
    }

    public String getOdbor() {
        return odbor;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public int getSkupinovyPlanId() {
        return skupinovyPlanId;
    }
}
