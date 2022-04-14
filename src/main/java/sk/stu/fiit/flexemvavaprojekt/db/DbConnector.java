package sk.stu.fiit.flexemvavaprojekt.db;
import java.sql.*;

public class DbConnector {
    public DbConnector() {
        Connection con = null;

        try {
            String url = "jdbc:postgresql://147.175.150.216:5432/dota2";
            con = DriverManager.getConnection(url, "xberezny", "Qsefthuko0Az09");
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
