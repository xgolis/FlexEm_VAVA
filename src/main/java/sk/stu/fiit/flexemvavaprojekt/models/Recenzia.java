package sk.stu.fiit.flexemvavaprojekt.models;

public class Recenzia {

    private int id;
    private final int skupinovy_plan_id;
    private final String sport;
    private final Integer pocetHviezd;
    private final String popis;
    private final String meno;
    private final String priezvisko;
    private final int cvicenec_id;
    private final int trener_id;

    public Recenzia(int skupinovy_plan_id, String sport, Integer pocetHviezd, String popis, String meno, String priezvisko, int cvicenec_id, int trener_id) {
        this.skupinovy_plan_id = skupinovy_plan_id;
        this.sport = sport;
        this.pocetHviezd = pocetHviezd;
        this.popis = popis;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.cvicenec_id = cvicenec_id;
        this.trener_id = trener_id;
    }

    public String getPopis() {
        return popis;
    }


    public Integer getPocetHviezd() {
        return pocetHviezd;
    }


    public String getSport() {
        return sport;
    }


    public int getSkupinovy_plan_id() {
        return skupinovy_plan_id;
    }

    public String pocetHviezdToString() {
        return Integer.toString(this.id);
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

    public String getPriezvisko() {
        return priezvisko;
    }

    public int getCvicenec_id() {
        return cvicenec_id;
    }

    public int getTrener_id() {
        return trener_id;
    }
}
