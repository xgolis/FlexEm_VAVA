package sk.stu.fiit.flexemvavaprojekt.models;


public class Cvicenec extends Pouzivatel{

    private int skupinovyPlanId;

    public Cvicenec(int id, String meno, String priezvisko, String email, String telefon, int skupinovyPlanId, byte[] hash, byte[] salt) {
        super(id, meno, priezvisko, email, telefon, hash, salt);
        this.skupinovyPlanId = skupinovyPlanId;
    }

    public int getSkupinovyPlanId() {
        return skupinovyPlanId;
    }

    public void setSkupinovyPlanId(int skupinovyPlanId) {
        this.skupinovyPlanId = skupinovyPlanId;
    }

}

