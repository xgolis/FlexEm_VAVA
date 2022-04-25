package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Timestamp;

public class SkupinovyPlan extends Plan {
    private String trener;

    public SkupinovyPlan(int id, String miestnost, int trenerId, String trener , String sport, String popis, Timestamp cas, Boolean done) {
        super(id, trenerId, popis, cas, done, miestnost, sport);
        this.trener = trener;

    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

}
