package sk.stu.fiit.flexemvavaprojekt.db.DTO;

public class Cvicenec {
    private int id;
    private String meno;
    private String priezvisko;
    private String email;
    private String telefon;
    private String heslo;
    private int skupinovyPlanId;

    public Cvicenec(int id, String meno, String priezvisko, String email, String telefon, String heslo, int skupinovyPlanId) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.email = email;
        this.telefon = telefon;
        this.heslo = heslo;
        this.skupinovyPlanId = skupinovyPlanId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public void setSkupinovyPlanId(int skupinovyPlanId) {
        this.skupinovyPlanId = skupinovyPlanId;
    }
}
