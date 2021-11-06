import javax.swing.*;
import java.sql.*;

public class checkremainclass {
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";
    int times = -1;

    public void checkremain() throws Exception{
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT count(no) FROM car")) {
                    while (rs.next()) {
                        times = rs.getInt(1);
                    }
                }
            }
        }
        times = 200 - times;
        JOptionPane.showMessageDialog(null,"剩余 "+times+" 个车位","停车场管理系统-剩余车位",JOptionPane.INFORMATION_MESSAGE);
    }
}
