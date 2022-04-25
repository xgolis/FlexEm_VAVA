package sk.stu.fiit.flexemvavaprojekt.db;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnector {

    private static DbConnector single_instance = null;
    Connection con = null;
    String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/pmsvldav";

    Logger logger = Main.logger;

    public DbConnector() {
        try {
            logger.log(Level.INFO, "DBConnector initialized");
            con = DriverManager.getConnection(url, "pmsvldav", "Q3U0h9PdA4B6wfNa_37PmTonvYVe2bMH");
            if (con != null) {
                System.out.println("Connected");
                logger.log(Level.INFO, "Database connected to application");
            }
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.INFO, "Database not connected to application", e);
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
            String sql = "INSERT INTO treners (meno, priezvisko, email, telefon, odbor, hash, salt) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,trener.getMeno());
            st.setString(2 ,trener.getPriezvisko());
            st.setString(3 ,trener.getEmail());
            st.setString(4 ,trener.getTelefonneCislo());
            st.setString(5 ,trener.getOdbor());
            st.setBytes(6 ,trener.getHash());
            st.setBytes(7 ,trener.getSalt());
            st.executeUpdate();
            st.close();
            logger.log(Level.INFO, "Trener created");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Trener not created", e);
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
            String sql = "INSERT INTO recepcnas (meno, prizvisko, email, telefon, hash, salt) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,recepcna.getMeno());
            st.setString(2 ,recepcna.getPriezvisko());
            st.setString(3 ,recepcna.getEmail());
            st.setString(4 ,recepcna.getTelefonneCislo());
            st.setBytes(5 ,recepcna.getHash());
            st.setBytes(6 ,recepcna.getSalt());
            st.executeUpdate();
            st.close();
            logger.log(Level.INFO, "Recepcna created");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Recepcna not created", e);
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
                        rs.getInt(9),
                        rs.getBytes(6),
                        rs.getBytes(7)
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
            String sql = "INSERT INTO cvicenecs (meno, priezvisko, email, telefon, trener_id, hash, salt) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1 ,cvicenec.getMeno());
            st.setString(2 ,cvicenec.getPriezvisko());
            st.setString(3 ,cvicenec.getEmail());
            st.setString(4 ,cvicenec.getTelefonneCislo());
            st.setInt(5, cvicenec.getTrener_id());
            st.setBytes(6 ,cvicenec.getHash());
            st.setBytes(7 ,cvicenec.getSalt());
            st.executeUpdate();
            st.close();
            logger.log(Level.INFO, "Cvicenec created");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Cvicenec not created", e);
            return false;
        }
    }

    public boolean setTrener(int trener_id, int cvicenec_id){
        try {
            String sql = "UPDATE cvicenecs SET trener_id = ? WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, trener_id);
            st.setInt(2, cvicenec_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Trener with id "+trener_id+" was set for cvicenec with id"+cvicenec_id);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Trener with id "+trener_id+" was not set for cvicenec with id"+cvicenec_id, e);
            return false;
        }
    }

    public boolean setInside(int id, boolean inside){
        try {
            String sql = "UPDATE cvicenecs SET inside = ? WHERE id = "+id;
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, inside);
            st.executeUpdate();

            st.close();
            logger.log(Level.INFO, "Cvicenec with id "+id+" was evidated inside");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not evidate cvicenec with id "+id+" inside", e);
            return false;
        }
    }

    public Boolean setNewPassword(int id,Pouzivatel pouzivatel) {
        String typ;
        if (pouzivatel instanceof  Cvicenec){
            typ = "cvicenecs";
        }
        else if (pouzivatel instanceof Recepcna){
            typ = "recepcnas";
        }
        else {
            typ = "treners";
        }


        try {
            String sql = "UPDATE " + typ + " SET hash = ?, salt = ? WHERE id = "+id;
            PreparedStatement st = con.prepareStatement(sql);
            st.setBytes(1,PrihlasenyPouzivatel.getInstance().getPouzivatel().getHash());
            st.setBytes(2,PrihlasenyPouzivatel.getInstance().getPouzivatel().getSalt());
            st.executeUpdate();
            st.close();
            return true;
        } catch (Exception e) {
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
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Cvicenec cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(9),
                        rs.getBytes(6),
                        rs.getBytes(7)
                );
                cvicenec.setInside(rs.getBoolean(8));
                list.add(cvicenec);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Cvicenec users evidated inside got from database");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not get users evidated inside", e);
            return null;
        }
    }

    public ArrayList<Pouzivatel> getRegexedQuery(String query){
        try {
            ArrayList<Pouzivatel> list = new ArrayList<>();
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Pouzivatel pouzivatel = new Recepcna(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        SpravaHesla.salt(),
                        SpravaHesla.salt()
                );
                list.add(pouzivatel);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Regexed query succesfully executed");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Regexed query executed unseccsfullt", e);
            return null;
        }
    }

//    public ArrayList<Pouzivatel> getClenovia(){
//        try {
//            ArrayList<Pouzivatel> list = new ArrayList<>();
//            String sql = "select * from treners";
//            PreparedStatement st = con.prepareStatement(sql);
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()){
//                Trener trener = new Trener(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getBytes(7),
//                        rs.getBytes(8)
//                );
//                list.add(trener);
//            }
//
//            String sql2 = "select * from cvicenecs";
//            st = con.prepareStatement(sql2);
//            rs = st.executeQuery(sql);
//            while (rs.next()){
//                Cvicenec cvicenec = new Cvicenec(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getInt(6),
//                        rs.getBytes(7),
//                        rs.getBytes(8)
//                );
//                list.add(cvicenec);
//            }
//            rs.close();
//            st.close();
//            return list;
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return null;
//        }
//    }
//
    public ArrayList<Plan> getMySchedule(int id){
        try {
            ArrayList<Plan> list = new ArrayList<>();
            String sql = "select sk.id, miestnosts.miestnost, sk.trener_id, sk.sport, sk.popis, sk.datum_cas, sk.done, sk.nazov, treners.meno " +
                    "from skupinovy_plans as sk " +
                    "join treners on sk.trener_id = treners.id " +
                    "join miestnosts on sk.miestnost_id = miestnosts.id " +
                    "where sk.trener_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Plan plan = new SkupinovyPlan(
                        rs.getInt(1),
                        0,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(9),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6),
                        rs.getBoolean(7),
                        rs.getString(8)
                );
                list.add(plan);
            }

            sql = "select * from individualny_plans where trener_id = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()){
                Plan plan = new IndividualnyPlan(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11)
                );
                list.add(plan);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Plans associated with trener with id "+id+" got from database");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not get plans for trener with id "+id, e);
            return null;
        }
    }

    public boolean createSkupinovyPlan(SkupinovyPlan skupinovyPlan){
        try {
            String sql = "INSERT INTO skupinovy_plans (miestnost_id, trener_id, sport, popis, datum_cas, done, nazov)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1 ,skupinovyPlan.getMiestnost_id());
            st.setInt(2 , skupinovyPlan.getTrenerId());
            st.setString(3 ,skupinovyPlan.getSport());
            st.setString(4 ,skupinovyPlan.getPopis());
            st.setTimestamp(5 , skupinovyPlan.getCas());
            st.setBoolean(6, false);
            st.setString(7, skupinovyPlan.getNazov());
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Skupinovy plan created");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not create skupinovy plan", e);
            return false;
        }
    }

    public ArrayList<Recenzia> getAllRecenzias(String dodatocneQuery){
        try {
            ArrayList<Recenzia> list = new ArrayList<>();
            String sql = "select recenzias.id, recenzias.skupinovy_plan_id, skupinovy_plans.sport, hodnotenie, recenzias.popis, cvicenec_id, cvicenecs.meno, cvicenecs.priezvisko, treners.id from recenzias \n" +
                    "join cvicenecs on recenzias.cvicenec_id = cvicenecs.id\n" +
                    "join skupinovy_plans on recenzias.skupinovy_plan_id = skupinovy_plans.id\n" +
                    "join treners on skupinovy_plans.trener_id = treners.id " + dodatocneQuery;
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Recenzia recenzia = new Recenzia(
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(6),
                        rs.getInt(9)
                        );
                recenzia.setId(rs.getInt(1));
                list.add(recenzia);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "All recenzias successfuly returned");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not get recenzias", e);
            return null;
        }
    }

    public ArrayList<SkupinovyPlan> getUpcomingSkupPlans(){
        try {
            ArrayList<SkupinovyPlan> list = new ArrayList<>();
            String sql = "SELECT sp.id, m.miestnost, t.id, t.meno, sp.sport, sp.popis, sp.datum_cas, sp.done, sp.nazov\n" +
                    "FROM skupinovy_plans sp\n" +
                    "JOIN miestnosts m on m.id = sp.miestnost_id\n" +
                    "JOIN treners t on t.id = sp.trener_id\n" +
                    "WHERE sp.done is not true";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                SkupinovyPlan skupinovyPlan = new SkupinovyPlan(
                        rs.getInt(1),
                        0,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTimestamp(7),
                        rs.getBoolean(8),
                        rs.getString(9)
                );
                list.add(skupinovyPlan);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "All upcoming plans successfuly returned");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not return upcoming plans", e);
            return null;
        }

    }

    public ArrayList<SkupinovyPlan> getMySkupPlans(int cvicenecId, boolean done){
        try {
            String not_query = "";
            if(done)
                not_query = "";
            else
                not_query = "not";
            ArrayList<SkupinovyPlan> list = new ArrayList<>();
            String sql = "SELECT sp.id, m.miestnost, t.id, t.meno, sp.sport, sp.popis, sp.datum_cas, sp.done, sp.nazov\n" +
                    "FROM skupinovy_plans sp\n" +
                    "JOIN miestnosts m on m.id = sp.miestnost_id\n" +
                    "JOIN treners t on t.id = sp.trener_id\n" +
                    "JOIN cvicenec_skup_plan csp on sp.id = csp.skup_plan_id\n" +
                    "JOIN cvicenecs c on c.id = csp.cvicenec_id\n" +
                    "WHERE sp.done is "+not_query+" true AND c.id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1 , cvicenecId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                SkupinovyPlan skupinovyPlan = new SkupinovyPlan(
                        rs.getInt(1),
                        0,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTimestamp(7),
                        rs.getBoolean(8),
                        rs.getString(9)
                );
                list.add(skupinovyPlan);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Skupinove plans of user with id "+cvicenecId+" were successfuly returned");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not get skupinove plans of user with id "+cvicenecId, e);

            return null;
        }

    }

    public ArrayList<Pouzivatel> getAllCvicenecs(){
        try {
            ArrayList<Pouzivatel> list = new ArrayList<>();
            String sql = "select * from cvicenecs";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Pouzivatel cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(9),
                        rs.getBytes(7),
                        rs.getBytes(8)
                );
                list.add(cvicenec);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "All cvicenecs successfuly returned");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not all cvicenecs", e);
            return null;
        }
    }

    public ArrayList<Pouzivatel> getAllTreners(){
        try {
            ArrayList<Pouzivatel> list = new ArrayList<>();
            String sql = "select * from treners";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Pouzivatel trener = new Trener(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBytes(8),
                        rs.getBytes(9)
                );
                list.add(trener);
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

    public ArrayList<IndividualnyPlan> getMyPlanCvicenec(int cvicenecId, boolean done) {
        try {
            String not_query = "";
            if(done)
                not_query = "";
            else
                not_query = "not";
            ArrayList<IndividualnyPlan> list = new ArrayList<>();
            String sql = "SELECT ip.id, ip.cvicenec_id, ip.trener_id, ip.datum_cas, ip.popis, ip.cvik1, ip.cvik2, ip.cvik3, ip.cvik4, ip.nazov\n" +
                    "FROM individualny_plans ip\n" +
                    "JOIN treners t on t.id = ip.trener_id\n" +
                    "JOIN cvicenecs c on c.id = ip.cvicenec_id\n" +
                    "WHERE ip.done is "+not_query+" true AND c.id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1 , cvicenecId);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                IndividualnyPlan individualnyPlan = new IndividualnyPlan(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        done,
                        rs.getString(10)
                );
                list.add(individualnyPlan);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Individual plans for user with id" +cvicenecId+" successfuly returned");
            return list;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Could not get individual plans for user with id "+cvicenecId);

            return null;
        }

    }



    public Pouzivatel loginOverenie(String email, String heslo){
        try {
            Pouzivatel pouzivatel;
            pouzivatel = findCvicenec(email);
            if(pouzivatel != null){
                byte[] hash_gen = SpravaHesla.hash(heslo, pouzivatel.getSalt());
                if(Arrays.equals(hash_gen, pouzivatel.getHash())) {
                    logger.log(Level.INFO, "User with id "+pouzivatel.getId()+" logged in");
                    return pouzivatel;
                }
                else{
                    logger.log(Level.INFO, "Could not authenticate user");
                    return null;
                }
            }
            else{
                pouzivatel = findTrener(email);
                if(pouzivatel != null){
                    byte[] hash_gen = SpravaHesla.hash(heslo, pouzivatel.getSalt());
                    if(Arrays.equals(hash_gen, pouzivatel.getHash())) {
                        logger.log(Level.INFO, "User with id "+pouzivatel.getId()+" logged in");
                        return pouzivatel;
                    }
                    else{
                        logger.log(Level.INFO, "Could not authenticate user");
                        return null;
                    }
                }
                else{
                    pouzivatel = findRecepcna(email);
                    if(pouzivatel != null){
                        byte[] hash_gen = SpravaHesla.hash(heslo, pouzivatel.getSalt());
                        if(Arrays.equals(hash_gen, pouzivatel.getHash())) {
                            logger.log(Level.INFO, "User with id "+pouzivatel.getId()+" logged in");
                            return pouzivatel;
                        }
                        else{
                            logger.log(Level.INFO, "Could not authenticate user");
                            return null;
                        }
                    }
                }
            }
            logger.log(Level.INFO, "Could not authenticate user");
            return null;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Could not log in user", e);
            System.out.println(e);
            return null;
        }
    }

    private Cvicenec findCvicenec(String email){
        try {
            String sql = "select * from cvicenecs where email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            Cvicenec cvicenec = null;
//            dorobit ak nič nenajde
            if (rs.next()){
                cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(9),
                        rs.getBytes(6),
                        rs.getBytes(7)
                );
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Uset with email "+ email+" found as type of cvicenec");
            return cvicenec;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while getting cvicenec wit email "+email);
            System.out.println(e);
            return null;
        }
    }

    private Trener findTrener(String email){
        try {
            String sql = "select * from treners where email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,email);
            ResultSet rs = st.executeQuery();
            Trener trener = null;
            if (rs.next()){
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
            logger.log(Level.INFO, "Uset with email "+ email+" found as type of trener");
            return trener;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while getting trener wit email "+email);
            System.out.println(e);
            return null;
        }
    }

    private Recepcna findRecepcna(String email){
        try {
            String sql = "select * from recepcnas where email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            Recepcna recepcna = null;
            if (rs.next()){
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
            logger.log(Level.INFO, "Uset with email "+ email+" found as type of recepcna");
            return recepcna;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while getting recepcna wit email "+email);
            System.out.println(e);
            return null;
        }
    }


    public ArrayList<Cvicenec> getTrenersCvicenec(int trener_id){
        try {
            ArrayList<Cvicenec> list = new ArrayList<>();
            String sql = "select * from cvicenecs where trener_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, trener_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Cvicenec cvicenec = new Cvicenec(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(9),
                        rs.getBytes(6),
                        rs.getBytes(7)
                );
                list.add(cvicenec);
            }
            rs.close();
            st.close();
            logger.log(Level.INFO, "Cvicenecs of trener with id "+trener_id+" successffuly returned");
            return list;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while getting cvicenecs of trener with id "+trener_id, e);
            System.out.println(e);
            return null;
        }
    }

//    public boolean createSkupPlan(SkupinovyPlan skupinovyPlan){
//        try {
//            String sql = "INSERT INTO skupinovy_plans (miestnost_id, trener_id, sport, popis, datum_cas)\n" +
//                    "VALUES (?, ?, ?, ?, ?);\n";
//            PreparedStatement st = con.prepareStatement(sql);
//            st.setString(1, skupinovyPlan.getMiestnost());
//            st.setInt(2, skupinovyPlan.getTrenerId());
//            st.setString(3, skupinovyPlan.getSport());
//            st.setString(4, skupinovyPlan.getPopis());
//            st.setTimestamp(5, skupinovyPlan.getCas());
//            ResultSet rs = st.executeQuery();
//            if (rs.next()){}
//            rs.close();
//            st.close();
//            return true;
//        }
//        catch (Exception e){
//            System.out.println(e);
//            return false;
//        }
//    }

    public boolean createRecenzia(Recenzia recenzia){
        try {
            String sql = "INSERT INTO recenzias (skupinovy_plan_id, hodnotenie, popis, cvicenec_id)\n" +
                    "VALUES (?, ?, ?, ?);\n";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, recenzia.getSkupinovy_plan_id());
            st.setInt(2, recenzia.getPocetHviezd());
            st.setString(3, recenzia.getPopis());
            st.setInt(4, recenzia.getCvicenec_id());
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Recenzia created successfuly");
            return true;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while creating recenzia",e);
            System.out.println(e);
            return false;
        }
    }

    public boolean addUserToSkupPlan(int cvicenec_id, int skup_plan){
        try {
            String sql = "INSERT INTO cvicenec_skup_plan (cvicenec_id, skup_plan_id)\n" +
                    "VALUES (?, ?);\n";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, cvicenec_id);
            st.setInt(2, skup_plan);
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Cvicenec with id "+cvicenec_id+" added to skupinovy plan with id"+ skup_plan);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Error while adding cvicenec with id"+ cvicenec_id+" to skupinovy plan with id "+skup_plan);
            return false;
        }
    }

    public boolean createIndivPlan(IndividualnyPlan individualnyPlan){
        try {
            String sql = "INSERT INTO individualny_plans (cvicenec_id, trener_id, datum_cas, popis, cvik1, cvik2, cvik3, cvik4, done, nazov)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, individualnyPlan.getCvicenecId());
            st.setInt(2, individualnyPlan.getTrenerId());
            st.setTimestamp(3, individualnyPlan.getCas());
            st.setString(4, individualnyPlan.getPopis());
            st.setString(5, individualnyPlan.getCvik1());
            st.setString(6, individualnyPlan.getCvik2());
            st.setString(7, individualnyPlan.getCvik3());
            st.setString(8, individualnyPlan.getCvik4());
            st.setBoolean(9, false);
            st.setString(10, individualnyPlan.getNazov());
            st.executeQuery();
            st.close();
            logger.log(Level.INFO, "Individualny plan for cvicenec with id "+individualnyPlan.getCvicenecId()+" created");
            return true;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while creating individualny plan for cvicenec with id "+individualnyPlan.getCvicenecId() );
            System.out.println(e);
            return false;
        }
    }

    public boolean updateCvicenec(Cvicenec cvicenec){
        try {
            String sql = "UPDATE cvicenecs\n" +
                    "SET meno = ?, priezvisko = ?, email = ?, telefon = ?, hash = ?, salt = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cvicenec.getMeno());
            st.setString(2, cvicenec.getPriezvisko());
            st.setString(3, cvicenec.getEmail());
            st.setString(4, cvicenec.getTelefonneCislo());
            st.setBytes(5, cvicenec.getHash());
            st.setBytes(6, cvicenec.getSalt());
            ResultSet rs = st.executeQuery();
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

    public boolean updateTrener(Trener trener){
        try {
            String sql = "UPDATE treners\n" +
                    "SET meno = ?, priezvisko = ?, email = ?, telefon = ?, hash = ?, salt = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, trener.getMeno());
            st.setString(2, trener.getPriezvisko());
            st.setString(3, trener.getEmail());
            st.setString(4, trener.getTelefonneCislo());
            st.setBytes(5, trener.getHash());
            st.setBytes(6, trener.getSalt());
            ResultSet rs = st.executeQuery();
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

    public boolean updateRecepcna(Recepcna recepcna){
        try {
            String sql = "UPDATE recepcnas\n" +
                    "SET meno = ?, prizvisko = ?, email = ?, telefon = ?, hash = ?, salt = ?\n" +
                    "WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, recepcna.getMeno());
            st.setString(2, recepcna.getPriezvisko());
            st.setString(3, recepcna.getEmail());
            st.setString(4, recepcna.getTelefonneCislo());
            st.setBytes(5, recepcna.getHash());
            st.setBytes(6, recepcna.getSalt());
            ResultSet rs = st.executeQuery();
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

    public boolean setIndPlanDone(int plan_id){
        try {
            String sql = "UPDATE individualny_plans\n" +
                    "SET done = ? WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, true);
            st.setInt(2, plan_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Individualny plan with id "+plan_id+" successfuly set to done");
            return true;
        }
        catch (Exception e){
            logger.log(Level.SEVERE, "Error while setting individualny plan with id "+plan_id+" to done");
            System.out.println(e);
            return false;
        }
    }

    public boolean setSkupPlanDone(int plan_id){
        try {
            String sql = "UPDATE skupinovy_plans\n" +
                    "SET done = ? WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setBoolean(1, true);
            st.setInt(2, plan_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){}
            rs.close();
            st.close();
            logger.log(Level.INFO, "Skupinovy plan with id "+plan_id+" successfuly set to done");
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            logger.log(Level.SEVERE, "Error while setting skupinovy plan with id "+plan_id+" to done");
            return false;
        }
    }

    public ArrayList<Miestnost> getMiestnosts(){
        try {
            ArrayList<Miestnost> list = new ArrayList<>();
            String sql = "select * from miestnosts;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Miestnost miestnost = new Miestnost(
                        rs.getInt(1),
                        rs.getString(3),
                        rs.getString(2),
                        rs.getString(4)
                );
                list.add(miestnost);
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
