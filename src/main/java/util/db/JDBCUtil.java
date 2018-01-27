package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {

    public static Connection getConnection() {

        String DATABASE_URL = "jdbc:mysql://localhost:3306/debt_killer_db?autoReconnect=true&"+
                "useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\n";
        String USER = "kamillj";
        String PASSWORD = "KaloMirA333";
        String DRIVER = "com.mysql.cj.jdbc.Driver";
        Connection connection = null;

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("JDBC: Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection == null) System.out.println("JDBC: Failed to make connection!");

        return connection;
    }
}
