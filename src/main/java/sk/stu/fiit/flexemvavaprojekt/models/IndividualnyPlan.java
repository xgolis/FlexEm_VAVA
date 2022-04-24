package sk.stu.fiit.flexemvavaprojekt.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class IndividualnyPlan {
    private int id;
    private int cvicenecId;
    private int trenerId;
    private Timestamp datumCas;
    private String popis;
    private String cvik1;
    private String cvik2;
    private String cvik3;
    private String cvik4;

    public IndividualnyPlan(int id, int cvicenecId, int trenerId, Timestamp datumCas, String popis, String cvik1, String cvik2, String cvik3, String cvik4) {
        this.id = id;
        this.cvicenecId = cvicenecId;
        this.trenerId = trenerId;
        this.datumCas = datumCas;
        this.popis = popis;
        this.cvik1 = cvik1;
        this.cvik2 = cvik2;
        this.cvik3 = cvik3;
        this.cvik4 = cvik4;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCvicenecId() {
        return cvicenecId;
    }

    public void setCvicenecId(int cvicenecId) {
        this.cvicenecId = cvicenecId;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public Timestamp getDatumCas() {
        return datumCas;
    }

    public void setDatumCas(Timestamp datumCas) {
        this.datumCas = datumCas;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getCvik1() {
        return cvik1;
    }

    public void setCvik1(String cvik1) {
        this.cvik1 = cvik1;
    }

    public String getCvik2() {
        return cvik2;
    }

    public void setCvik2(String cvik2) {
        this.cvik2 = cvik2;
    }

    public String getCvik3() {
        return cvik3;
    }

    public void setCvik3(String cvik3) {
        this.cvik3 = cvik3;
    }

    public String getCvik4() {
        return cvik4;
    }

    public void setCvik4(String cvik4) {
        this.cvik4 = cvik4;
    }
}
