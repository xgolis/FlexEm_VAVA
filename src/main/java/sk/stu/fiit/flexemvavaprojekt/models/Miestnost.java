package sk.stu.fiit.flexemvavaprojekt.models;

public class Miestnost {

    int id;
    private final String kapacita;

    private final String nazov;

    private final String sport;

    public Miestnost(int id, String kapacita, String nazov, String sport) {
        this.id = id;
        this.kapacita = kapacita;
        this.nazov = nazov;
        this.sport = sport;
    }

    public int getId() {
        return id;
    }

    public String getKapacita() {
        return kapacita;
    }
    public String getNazov() {
        return nazov;
    }

    public String getSport() {
        return sport;
    }

}
