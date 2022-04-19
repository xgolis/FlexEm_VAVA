package sk.stu.fiit.flexemvavaprojekt.models;

public class Recenzia {

    private final String miestnost;
    private final String sport;
    private final Integer pocetHviezd;
    private final String popis;

    public Recenzia(String miestnost, String sport, Integer pocetHviezd, String popis) {
        this.miestnost = miestnost;
        this.sport = sport;
        this.pocetHviezd = pocetHviezd;
        this.popis = popis;
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


    public String getMiestnost() {
        return miestnost;
    }


}
