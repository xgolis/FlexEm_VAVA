package sk.stu.fiit.flexemvavaprojekt.db;
import sk.stu.fiit.flexemvavaprojekt.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                        rs.getBytes(7),
                        rs.getBytes(8)
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
            String sql = "INSERT INTO treners (meno, priezvisko, email, telefon, odbor, hash, salt)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,trener.getMeno());
            st.setString(2 ,trener.getPriezvisko());
            st.setString(3 ,trener.getEmail());
            st.setString(4 ,trener.getTelefonneCislo());
            st.setString(5 ,trener.getOdbor());
            st.setBytes(6 ,trener.getHash());
            st.setBytes(7 ,trener.getSalt());
            ResultSet rs = st.executeQuery();
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
                        rs.getBytes(6),
                        rs.getBytes(7)

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
            String sql = "INSERT INTO treners (meno, prizvisko, email, telefon, hash, salt)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,recepcna.getMeno());
            st.setString(2 ,recepcna.getPriezvisko());
            st.setString(3 ,recepcna.getEmail());
            st.setString(4 ,recepcna.getTelefonneCislo());
            st.setBytes(5 ,recepcna.getHash());
            st.setBytes(6 ,recepcna.getSalt());
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
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getBytes(8),
                        rs.getBytes(9)
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
            Integer ind = cvicenec.getIndividualnyPlanId();
            Integer skup = cvicenec.getSkupinovyPlanId();
            if (cvicenec.getIndividualnyPlanId() == 0) ind = null;
            if (cvicenec.getSkupinovyPlanId() == 0) skup = null;
            String sql = "INSERT INTO treners (meno, priezvisko, email, telefon, individualny_plan_id, skupinovy_plan_id, hash, salt)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,cvicenec.getMeno());
            st.setString(2 ,cvicenec.getPriezvisko());
            st.setString(3 ,cvicenec.getEmail());
            st.setString(4 ,cvicenec.getTelefonneCislo());
            st.setInt(5 ,cvicenec.getIndividualnyPlanId());
            st.setInt(6 ,cvicenec.getSkupinovyPlanId());
            st.setBytes(7 ,cvicenec.getHash());
            st.setBytes(7 ,cvicenec.getSalt());
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

    public boolean setInside(int name){
        try {
            String sql = "UPDATE cvicenecs SET inside = ? WHERE name = "+name;
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, true);
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

    public ArrayList<Cvicenec> getInside(){
        try {
            ArrayList<Cvicenec> list = new ArrayList<>();
            String sql = "select * from cvicenecs where inside = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, true);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cvicenec cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getBytes(8),
                        rs.getBytes(9)
                );
                list.add(cvicenec);
            }
            rs.close();
            st.close();
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Pouzivatel> getClenovia(){
        try {
            ArrayList<Pouzivatel> list = new ArrayList<>();
            String sql = "select * from treners";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Trener trener = new Trener(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBytes(7),
                        rs.getBytes(8)
                );
                list.add(trener);
            }

            String sql2 = "select * from cvicenecs";
            st = con.prepareStatement(sql2);
            rs = st.executeQuery(sql);
            while (rs.next()){
                Cvicenec cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getBytes(8),
                        rs.getBytes(9)
                );
                list.add(cvicenec);
            }
            rs.close();
            st.close();
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean createSkupinovyPlan(SkupinovyPlan skupinovyPlan){
        try {
            String sql = "INSERT INTO skupinovy_plans (miestnost_id, trener_id, sport, popis, datum_cas)\n" +
                    "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1 ,skupinovyPlan.getMiestnostId());
            st.setInt(2 , skupinovyPlan.getTrenerId());
            st.setString(3 ,skupinovyPlan.getSport());
            st.setString(4 ,skupinovyPlan.getPopis());
            st.setTimestamp(5 , skupinovyPlan.getCas());
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

    public ArrayList<Recenzia> getAllRecenzias(){
        try {
            ArrayList<Recenzia> list = new ArrayList<>();
            String sql = "select * from recenzias";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()){
                Recenzia recenzia = new Recenzia(
                        null,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                );
                recenzia.setId(rs.getInt(1));
                list.add(recenzia);
            }
            rs.close();
            st.close();
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
