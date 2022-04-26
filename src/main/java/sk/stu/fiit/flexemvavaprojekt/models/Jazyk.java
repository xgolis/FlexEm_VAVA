package sk.stu.fiit.flexemvavaprojekt.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Trieda Jazyk, ktorá je singleton sa využíva na lokalizáciu,preklady,formátovanie času
 *
 * @author jozefvlcek
 */
public class Jazyk {

    private static Jazyk single_instance = null;
    private final HashMap<String,Locale> lokal;
    private Locale aktualnyJazyk;


    private Jazyk()
    {
       lokal = new HashMap<>();
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

    /**
     * Metoda slúži na vrátenie slova na základe kľúču
     * @param kluc slúží na získanie príslušného slova vo vyžiadanom jazyku
     * @return hodnota predstavuje slovo v či už slovenskom alebo anglickom jazyku
     */
    public String prelozeneSlovo(String kluc) {
        ResourceBundle  resourceBundle = ResourceBundle.getBundle("bundle",aktualnyJazyk);
        return (resourceBundle.getString(kluc));
    }

    public String naformatujCas(String cas){

        LocalTime casCas = LocalTime.parse(cas);

        if (aktualnyJazyk  == lokal.get("EN")) {
            return (casCas.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }
        else {
            return (cas);
        }

    }

    /**
     * Metoda formatuje telefonne cislo
     * @param telCislo predstavuje telefonne cislo, ktore je potrebne sformatovat
     * @return zformatovane cislo
     */
    public String naformatujTelefon(String telCislo) {
        String cisloBezMedzier;
        cisloBezMedzier = telCislo.replaceAll(" ", "");
        return cisloBezMedzier.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d+)", "$1 $2 $3 $4");

    }


}
