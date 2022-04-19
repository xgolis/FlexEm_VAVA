package sk.stu.fiit.flexemvavaprojekt.models;

public abstract class Pouzivatel {
    private String email;
    private final String meno;
    private final String priezvisko;
    private String telefonneCislo;


    public Pouzivatel(String email, String meno, String priezvisko, String telefonneCislo) {
        this.email = email;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.telefonneCislo = telefonneCislo;
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

    public void setTelefonneCislo(String telefonneCislo) {
        this.telefonneCislo = telefonneCislo;
    }
}
