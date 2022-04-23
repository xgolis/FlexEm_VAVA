package sk.stu.fiit.flexemvavaprojekt.db;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.Recepcna;
import sk.stu.fiit.flexemvavaprojekt.models.Trener;

import java.sql.*;

public class DbConnector {

    private static DbConnector single_instance = null;
    Connection con = null;
    String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/pmsvldav";

    public DbConnector() {
        try {
            con = DriverManager.getConnection(url, "pmsvldav", "Q3U0h9PdA4B6wfNa_37PmTonvYVe2bMH");
            if (con != null)
                System.out.println("Connected");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static DbConnector getInstance()
    {
        if (single_instance == null)
            single_instance = new DbConnector();

        return single_instance;
    }

    public Trener getTrener(int id){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM treners where id = "+id);
            Trener trener = null;
            while (rs.next())
            {
                trener = new Trener(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }
            rs.close();
            st.close();
            return trener;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createTrener(Trener trener){
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO treners (meno, priezvisko, email, telefon, heslo, odbor)\n" +
                    "VALUES ('"+
                    trener.getMeno()
                    +"', '"+
                    trener.getPriezvisko()
                    +"', '"+
                    trener.getEmail()
                    +"', '"+
                    trener.getTelefonneCislo()
                    +"', '"+
                    trener.getHeslo()
                    +"', '"+
                    trener.getOdbor()
                    +"');";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                System.out.println("created?");
            }
            System.out.println("creauvidime");
            rs.close();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public Recepcna getRecepcna(int id){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM recepcnas where id = "+id);
            Recepcna recepcna = null;
            while (rs.next())
            {
                recepcna = new Recepcna(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }
            rs.close();
            st.close();
            return recepcna;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createRecepcna(Recepcna recepcna){
        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO recepcnas (meno, prizvisko, email, telefon, heslo)\n" +
                    "VALUES ('"+
                    recepcna.getMeno()
                    +"', '"+
                    recepcna.getPriezvisko()
                    +"', '"+
                    recepcna.getEmail()
                    +"', '"+
                    recepcna.getTelefonneCislo()
                    +"', '"+
                    recepcna.getHeslo()
                    +"');";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                System.out.println("created?");
            }
            System.out.println("creauvidime");
            rs.close();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public Cvicenec getCvicenec(int id){
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cvicenecs where id = "+id);
            Cvicenec cvicenec = null;
            while (rs.next())
            {
                cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
            }
            rs.close();
            st.close();
            return cvicenec;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createCvicenec(Cvicenec cvicenec){
        try {
            Statement st = con.createStatement();
            Integer ind = cvicenec.getIndividualnyPlanId();
            Integer skup = cvicenec.getSkupinovyPlanId();
            if (cvicenec.getIndividualnyPlanId() == 0) ind = null;
            if (cvicenec.getSkupinovyPlanId() == 0) skup = null;
            String sql = "INSERT INTO cvicenecs (meno, priezvisko, email, telefon, heslo, skupinovy_plan_id, individualny_plan_id)\n" +
                    "VALUES ('"+
                    cvicenec.getMeno()
                    +"', '"+
                    cvicenec.getPriezvisko()
                    +"', '"+
                    cvicenec.getEmail()
                    +"', '"+
                    cvicenec.getTelefonneCislo()
                    +"', '"+
                    cvicenec.getHeslo()
                    +"', "+
                    skup
                    +", "+
                    ind
                    +");";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){}
            rs.close();
            st.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}
