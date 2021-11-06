import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class mainmenu {
    JFrame menujf;
    JPanel menujp;
    JPanel menujp2;
    JButton search;
    JButton addcar;
    JButton checkremain;
    JButton exitcar;
    JButton usermanager;
    JButton passwd;
    JButton switchuser;
    JButton exit;
    JLabel user;
    JLabel typetext;

    public void searchui(){
        JFrame searchuijf = new JFrame("停车场管理系统-车辆查询");
        JPanel searchuijp = new JPanel();
        JPanel searchuijp2 = new JPanel();
        JLabel info = new JLabel("请输入车辆信息");
        JLabel noinfo = new JLabel("车牌: ");
        JTextField no = new JTextField();

        JButton go = new JButton("缴费");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new searchclass().search(no.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        no.setColumns(20);

        searchuijp.add(info);
        searchuijp2.add(noinfo);
        searchuijp2.add(no);
        searchuijp2.add(go);

        searchuijf.setVisible(true);
        searchuijf.setLocationRelativeTo(null);
        searchuijf.setSize(300,170);
        searchuijf.add(searchuijp, BorderLayout.NORTH);
        searchuijf.add(searchuijp2, BorderLayout.CENTER);

    }

    public void exitcarui(){
        JFrame exitcaruijf = new JFrame("停车场管理系统-停车缴费");
        JPanel exitcaruijp = new JPanel();
        JPanel exitcaruijp2 = new JPanel();
        JLabel info = new JLabel("请输入车辆信息");
        JLabel noinfo = new JLabel("车牌: ");
        JTextField no = new JTextField();

        JButton go = new JButton("缴费");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new exitcarclass().exitcar(no.getText());
                    exitcaruijf.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        no.setColumns(20);

        exitcaruijp.add(info);
        exitcaruijp2.add(noinfo);
        exitcaruijp2.add(no);
        exitcaruijp2.add(go);

        exitcaruijf.setVisible(true);
        exitcaruijf.setLocationRelativeTo(null);
        exitcaruijf.setSize(300,170);
        exitcaruijf.add(exitcaruijp, BorderLayout.NORTH);
        exitcaruijf.add(exitcaruijp2, BorderLayout.CENTER);
    }

    public void checkremainui() throws Exception {
        new checkremainclass().checkremain();
    }

    public void addcarui(){
        JFrame addcaruijf = new JFrame("停车场管理系统-添加车辆");
        JPanel addcaruijp = new JPanel();
        JPanel addcaruijp2 = new JPanel();
        JLabel info = new JLabel("请输入车辆信息");
        JLabel noinfo = new JLabel("车牌: ");
        JTextField no = new JTextField();

        JButton go = new JButton("登记");
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NO = no.getText();
                try {
                    new addcarclass().addcar(NO);
                    JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    addcaruijf.setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        no.setColumns(20);

        addcaruijp.add(info);
        addcaruijp2.add(noinfo);
        addcaruijp2.add(no);
        addcaruijp2.add(go);

        addcaruijf.setVisible(true);
        addcaruijf.setLocationRelativeTo(null);
        addcaruijf.setSize(300,170);
        addcaruijf.add(addcaruijp, BorderLayout.NORTH);
        addcaruijf.add(addcaruijp2, BorderLayout.CENTER);
    }

    public void menu(String username, int type){
        String TYPE = "NULL";
        if (type == 1)
            TYPE = "管理员";
        if (type == 2)
            TYPE = "操作员";
        if (type == 3)
            TYPE = "用户";

        menujf = new JFrame("停车场管理系统-主菜单");
        menujp = new JPanel();
        menujp2 = new JPanel();
        user = new JLabel("当前用户: "+username);
        typetext = new JLabel("\n\n权限: "+TYPE);

        passwd = new JButton("修改密码");
        passwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new passwdclass().passwd(username);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        usermanager = new JButton("管理用户");
        usermanager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new usermanagerclass().usermanager(username,type);
            }
        });

        search = new JButton("搜索车辆");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainmenu().searchui();
            }
        });

        checkremain = new JButton("剩余车位");
        checkremain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkremainui();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        addcar = new JButton("添加汽车");
        addcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainmenu().addcarui();
            }
        });

        exitcar = new JButton("停车缴费");
        exitcar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mainmenu().exitcarui();
            }
        });


        switchuser = new JButton("切换用户");
        switchuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menujf.setVisible(false);
                new login().init();
            }
        });

        exit = new JButton("退出");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        if (type == 1){
            menujp.add(user);
            menujp.add(typetext);
            menujp2.add(search);
            menujp2.add(checkremain);
            menujp2.add(addcar);
            menujp2.add(exitcar);
            menujp2.add(usermanager);
            menujp2.add(passwd);
            menujp2.add(switchuser);
            menujp2.add(exit);
        }
        if (type == 2) {
            menujp.add(user);
            menujp.add(typetext);
            menujp2.add(search);
            menujp2.add(checkremain);
            menujp2.add(addcar);
            menujp2.add(exitcar);
            menujp2.add(passwd);
            menujp2.add(switchuser);
            menujp2.add(exit);
        }
        if (type == 3) {
            menujp.add(user);
            menujp.add(typetext);
            menujp2.add(search);
            menujp2.add(checkremain);
            menujp2.add(passwd);
            menujp2.add(switchuser);
            menujp2.add(exit);
        }

        menujf.setVisible(true);
        menujf.setSize(500,200);
        menujf.setLocationRelativeTo(null);
        menujf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menujf.add(menujp, BorderLayout.NORTH);
        menujf.add(menujp2, BorderLayout.CENTER);
    }
}
