package sk.stu.fiit.flexemvavaprojekt.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Jazyk {

    private static Jazyk single_instance = null;
    private final HashMap<String,Locale> lokal;
    private Locale aktualnyJazyk;


    private Jazyk()
    {
       lokal = new HashMap<String,Locale>();
       lokal.put("EN",new Locale("EN"));
       lokal.put("SK",new Locale("SK"));
       this.aktualnyJazyk = lokal.get("SK");
    }


    public Locale getAktualnyJazyk() {
        return this.aktualnyJazyk;
    }

    public void setAktualnyJazyk(String kluc) {
        this.aktualnyJazyk = lokal.get(kluc);
    }

    public static Jazyk getInstance()
    {
        if (single_instance == null)
            single_instance = new Jazyk();

        return single_instance;
    }
}
