package basedatos;

import java.sql.*;

public class Conexion {

    public static Connection dbConnector() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String host = "localhost";
        String dbname = "alumnos";
        String url = "jdbc:mysql://" + host + "/" + dbname +"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
