package sk.stu.fiit.flexemvavaprojekt.models;

public class Jazyk {

    private static Jazyk single_instance = null;

    private String jazykMoznost;


    private Jazyk()
    {
        setJazykMoznost("SK");
    }


    public String getJazykMoznost() {
        return jazykMoznost;
    }

    public void setJazykMoznost(String jazykMoznost) {
        this.jazykMoznost = jazykMoznost;
    }


    public static Jazyk getInstance()
    {
        if (single_instance == null)
            single_instance = new Jazyk();

        return single_instance;
    }
}
