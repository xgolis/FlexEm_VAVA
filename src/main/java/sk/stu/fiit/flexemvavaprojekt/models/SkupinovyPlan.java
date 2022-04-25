package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Timestamp;

public class SkupinovyPlan extends Plan {
    private String trener;
    private int miestnost_id;

    public SkupinovyPlan(int id, int miestnost_id, String miestnost, int trenerId, String trener , String sport, String popis, Timestamp cas, Boolean done, String nazov) {
        super(id, trenerId, popis, cas, done, miestnost, sport, nazov);
        this.trener = trener;
        this.miestnost_id = miestnost_id;
    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

    public int getMiestnost_id() {
        return miestnost_id;
    }

    public void setMiestnost_id(int miestnost_id) {
        this.miestnost_id = miestnost_id;
    }
}
