package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SkupinovyPlan {
    private int id;
    private int miestnostId;
    private int trenerId;
    private String sport;
    private String popis;
    private Timestamp cas;

    public SkupinovyPlan(int id, int miestnostId, int trenerId, String sport, String popis, Timestamp cas) {
        this.id = id;
        this.miestnostId = miestnostId;
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

    public int getMiestnostId() {
        return miestnostId;
    }

    public void setMiestnostId(int miestnostId) {
        this.miestnostId = miestnostId;
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
}
