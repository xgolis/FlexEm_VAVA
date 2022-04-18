package sk.stu.fiit.flexemvavaprojekt.db;
import java.sql.*;

public class DbConnector {
    public DbConnector() {
        Connection con = null;

        try {
            String url = "jdbc:postgresql://manny.db.elephantsql.com:5432/pmsvldav";
            con = DriverManager.getConnection(url, "pmsvldav", "Q3U0h9PdA4B6wfNa_37PmTonvYVe2bMH");
            if (con != null)
                System.out.println("Connected");

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patches");
            while (rs.next())
            {
                System.out.print("Column 1 returned ");
                System.out.println(rs.getString(1));
            }
            rs.close();
            st.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
