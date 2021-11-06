import javax.swing.*;
import java.sql.*;

public class addcarclass {
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";

    public void addcar(String NO) throws Exception{
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO car (no, intime) VALUES (?,NOW())")) {
                ps.setObject(1, NO);
                int n = ps.executeUpdate();
            }
        }
    }
}
