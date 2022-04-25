package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Timestamp;

public abstract class Plan{

    private String popis;

    private int trenerId;

    private Timestamp cas;

    private Boolean done;

    private int id;

    private final String miestnost;

    private final String sport;

    private String nazov;

    public Plan(int id, int trenerId, String popis, Timestamp cas, Boolean done, String miestnost, String sport, String nazov) {
        this.id = id;
        this.trenerId = trenerId;
        this.popis = popis;
        this.cas = cas;
        this.done = done;
        this.sport = sport;
        this.miestnost = miestnost;
        this.nazov = nazov;
    }


    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public Timestamp getCas() {
        return cas;
    }

    public void setCas(Timestamp cas) {
        this.cas = cas;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMiestnost() {
        return miestnost;
    }

    public String getSport() {
        return sport;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }
}
