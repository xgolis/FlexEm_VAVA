package sk.stu.fiit.flexemvavaprojekt.models;


public abstract class Pouzivatel {

    protected final int id;
    protected String heslo;
    protected String email;
    protected final String meno;
    protected final String priezvisko;
    protected final String telefonneCislo;



    public Pouzivatel(int id, String meno, String priezvisko, String email, String telefon, String heslo) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.telefonneCislo = telefon;
        this.heslo = heslo;
    }


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
        return this.id;
    }

    public String idToString(){
        return Integer.toString(this.id);
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

}
