package bases;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Ado {

    public static Connection init() {
        Connection cnxDirect=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnxDirect= DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque?user=root");
            System.out.println("connexion valide ? : " + cnxDirect.isValid(0));
            }
            catch (ClassNotFoundException | SQLException e)
            {
            System.out.println("Erreur : " + e);
            }
            return cnxDirect;

    }

}
