import javax.swing.*;
import java.sql.*;

public class deluserclass {
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";
    boolean SELECT;

    public void deluser() throws SQLException {
        String username = JOptionPane.showInputDialog
                (null,"请输入要删除的用户名","删除用户", JOptionPane.PLAIN_MESSAGE);
        int select = JOptionPane.showConfirmDialog
                (null,"您确定要删除用户 "+username+" 吗？","删除用户",JOptionPane.YES_NO_OPTION);
        if (select == 0)
            SELECT = true;
        else
            SELECT = false;
        if (SELECT){
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE username=?")) {
                    ps.setObject(1, username);
                    int n = ps.executeUpdate();
                }
            }
            JOptionPane.showMessageDialog(null,"操作完成","删除用户",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
