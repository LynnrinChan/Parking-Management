import javax.swing.*;
import java.sql.*;

public class adduserclass {
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";

    public void adduser() throws Exception{
        Object[] TYPE = {"管理员","操作员","用户"};
        String username = JOptionPane.showInputDialog
                (null,"请输入用户名","添加用户", JOptionPane.PLAIN_MESSAGE);
        String password = JOptionPane.showInputDialog
                (null,"请输入密码","添加用户", JOptionPane.PLAIN_MESSAGE);
        String type =
                (String) JOptionPane.showInputDialog(null,"请选择权限类型","添加用户",JOptionPane.QUESTION_MESSAGE,null,TYPE,TYPE[2]);
        if (type.equals("管理员"))
            type = "1";
        if (type.equals("操作员"))
            type = "2";
        if (type.equals("用户"))
            type = "3";
        password = MD5Util.getMD5Str(String.valueOf(password));
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (username, password, type) VALUES (?,?,?)")) {
                ps.setObject(1, username);
                ps.setObject(2, password);
                ps.setObject(3, Integer.valueOf(type).intValue());
                int n = ps.executeUpdate();
            }
        }
        JOptionPane.showMessageDialog(null,"操作完成","添加用户",JOptionPane.INFORMATION_MESSAGE);
    }
}
