import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login {
    JFrame loginjf;
    JPanel loginjp;
    JPanel loginjp2;
    JLabel usernametext;
    JLabel passwordtext;
    JTextField username;
    JPasswordField password;
    JButton loginbt;
    JButton exitbt;
    JLabel nextline;

    public String USERNAME = "NULL";
    public String PASSWORD = "NULL";

    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/parking?useSSL=false&characterEncoding=utf8";
    String JDBC_USER = "root";
    String JDBC_PASSWORD = "windows7";

    public void loginverify() throws Exception{
        String sqlusername = "null";
        String sqlpassword = "null";
        int sqltype = 0;
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT username,password,type FROM users WHERE username='"+USERNAME+"'")) {
                    while (rs.next()) {
                        sqlusername = rs.getString(1);
                        sqlpassword = rs.getString(2);
                        sqltype = rs.getInt(3);
                    }
                }
            }
        }
        if (PASSWORD.equals(sqlpassword)){
            loginjf.setVisible(false);
            new mainmenu().menu(USERNAME,sqltype);
        }
        else {
            JOptionPane.showMessageDialog(null,"密码错误！","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void init(){
        loginjf = new JFrame("停车场管理系统-登录");
        loginjp = new JPanel();
        loginjp2 = new JPanel();
        usernametext = new JLabel("              用户名：");
        passwordtext = new JLabel("                 密码：");
        username = new JTextField();
        password = new JPasswordField();
        loginbt = new JButton("登录");
        exitbt = new JButton("退出");
        nextline = new JLabel("<br>");

        username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                USERNAME = username.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                USERNAME = username.getText();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                USERNAME = username.getText();
            }
        });

        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PASSWORD = MD5Util.getMD5Str(String.valueOf(password.getPassword()));
            }

            @Override
            public void keyPressed(KeyEvent e) {
                PASSWORD = MD5Util.getMD5Str(String.valueOf(password.getPassword()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                PASSWORD = MD5Util.getMD5Str(String.valueOf(password.getPassword()));
            }
        });

        loginbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginverify();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        exitbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        username.setColumns(10);
        password.setColumns(10);

        loginjp.setLayout(new FlowLayout());
        loginjp.add(usernametext);
        loginjp.add(username);
        loginjp.add(passwordtext);
        loginjp.add(password);
        loginjp2.add(loginbt);
        loginjp2.add(exitbt);

        loginjf.setVisible(true);
        loginjf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginjf.setLocationRelativeTo(null);
        loginjf.setSize(300,170);
        loginjf.add(loginjp, BorderLayout.CENTER);
        loginjf.add(loginjp2, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new login().init();
    }
}
