import javax.swing.*;
import java.sql.*;

public class permissionclass {
    public void permission() throws Exception {
        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "windows7";
        Object[] TYPE = {"管理员","操作员","用户"};
        String username = JOptionPane.showInputDialog
                (null,"请输入要更改权限的用户名","更改权限", JOptionPane.PLAIN_MESSAGE);
        String type =
                (String) JOptionPane.showInputDialog(null,"请选择权限类型","更改权限",JOptionPane.QUESTION_MESSAGE,null,TYPE,TYPE[2]);
        if (type.equals("管理员"))
            type = "1";
        if (type.equals("操作员"))
            type = "2";
        if (type.equals("用户"))
            type = "3";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE users SET type=? WHERE username=?")) {
                ps.setObject(2, username);
                ps.setObject(1, Integer.valueOf(type).intValue());
                int n = ps.executeUpdate();
            }
        }
        JOptionPane.showMessageDialog(null,"操作完成","修改权限",JOptionPane.INFORMATION_MESSAGE);
    }
}
