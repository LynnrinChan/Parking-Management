import javax.swing.*;
import java.sql.*;

public class passwdclass {
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";
    String sqlpassword = "null";

    public void passwd(String username) throws SQLException {
        String orpassword = MD5Util.getMD5Str((JOptionPane.showInputDialog
                (null,"当前修改的是: "+username+" 的密码\n请输入原密码","修改密码",JOptionPane.INFORMATION_MESSAGE)));
        String newpassword = MD5Util.getMD5Str((JOptionPane.showInputDialog
                (null,"当前修改的是: "+username+" 的密码\n请输入新密码","修改密码",JOptionPane.INFORMATION_MESSAGE)));
        String newpassword2 = MD5Util.getMD5Str((JOptionPane.showInputDialog
                (null,"当前修改的是: "+username+" 的密码\n请再输入一次新密码","修改密码",JOptionPane.INFORMATION_MESSAGE)));
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE username='"+username+"'")) {
                    while (rs.next()) {
                        sqlpassword = rs.getString(1);
                    }
                }
            }
        }
        if (sqlpassword.equals(orpassword)){
            if (newpassword.equals(newpassword2)){
                try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE users SET password=? WHERE username=?")) {
                        ps.setObject(1, newpassword); // 注意：索引从1开始
                        ps.setObject(2, username);
                        int n = ps.executeUpdate(); // 返回更新的行数
                    }
                }
                JOptionPane.showMessageDialog(null,"操作成功","修改密码",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog
                        (null,"第二次密码检验不正确，请重试","修改密码",JOptionPane.ERROR_MESSAGE);
                new passwdclass().passwd(username);
            }
        }
        else {
            JOptionPane.showMessageDialog
                    (null,"原密码不正确，请重试","修改密码",JOptionPane.ERROR_MESSAGE);
            new passwdclass().passwd(username);
        }
    }
}
