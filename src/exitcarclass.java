import javax.swing.*;
import java.sql.*;

public class exitcarclass {
    public void exitcar(String NO) throws Exception{
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "windows7";
        String TIME = "NULL";
        String TIME2 = "NULL";
        boolean RETURN = false;
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT no, intime FROM car WHERE NO='"+NO+"'")) {
                    while (rs.next()) {
                        NO = rs.getString(1);
                        TIME = rs.getString(2);
                        RETURN = true;
                    }
                }
            }
        }
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT TIMESTAMPDIFF(MINUTE, '"+TIME+"', NOW())")) {
                    while (rs.next()) {
                        TIME2 = rs.getString(1);
                        RETURN = true;
                    }
                }
            }
        }
        if (!(TIME2 == null)) {
            JOptionPane.showMessageDialog(null, NO + " 的车主您好\n您停车 " + TIME2 + " 分钟，需要缴费 " + Integer.valueOf(TIME2).intValue() / 5 + " 元。", "费用信息", JOptionPane.INFORMATION_MESSAGE);
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM car WHERE NO=?")) {
                    ps.setObject(1, NO); // 注意：索引从1开始
                    int n = ps.executeUpdate(); // 删除的行数
                }
            }
        }
        else
            JOptionPane.showMessageDialog(null,"无此汽车信息","出错了",JOptionPane.ERROR_MESSAGE);
    }
}
