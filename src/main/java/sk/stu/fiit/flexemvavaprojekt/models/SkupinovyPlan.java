package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Timestamp;

public class SkupinovyPlan {
    private int id;
    private String miestnost;
    private int trenerId;
    private String sport;
    private String popis;
    private Timestamp cas;
    private boolean done = false;

    public SkupinovyPlan(int id, String miestnost, int trenerId, String sport, String popis, Timestamp cas) {
        this.id = id;
        this.miestnost = miestnost;
        this.trenerId = trenerId;
        this.sport = sport;
        this.popis = popis;
        this.cas = cas;
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

    public void setMiestnostId(String miestnostId) {
        this.miestnost = miestnostId;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Timestamp getCas() {
        return cas;
    }

    public void setCas(Timestamp cas) {
        this.cas = cas;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
