package sk.stu.fiit.flexemvavaprojekt.models;

/**
 * Abstraktna trieda,ktora zovšeobecnuje všetkých používatelov akými sú recepčná,tréner a cvičenec
 */
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

    /**
     * abstraktna metoda, ktorú implementujú triedy cvičenec,tréner a recepčná
     * kedže sa každý jeden z nich ocitne na inej scéne na základe ich role
     */
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
