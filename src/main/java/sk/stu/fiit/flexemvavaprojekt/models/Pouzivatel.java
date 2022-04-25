package sk.stu.fiit.flexemvavaprojekt.models;


public abstract class Pouzivatel {

    protected final int id;

    protected byte[] salt;

    protected byte[] hash;

    protected String email;

    protected final String meno;
    protected final String priezvisko;
    protected final String telefonneCislo;
    protected boolean inside;



    public Pouzivatel(int id, String meno, String priezvisko, String email, String telefon, byte[] hash, byte[] salt) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.telefonneCislo = telefon;
        this.salt = salt;
        this.hash = hash;
    }

    public abstract void prihlaseniePouzivatela();

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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getHash() {
        return hash;
    }

    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }
}
