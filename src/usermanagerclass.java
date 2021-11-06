import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class usermanagerclass {
    JFrame usermanagerjf;
    JPanel usermanagerjp;
    JPanel usermanagerjp2;
    JLabel banner;
    JLabel banner2;
    JButton adduser;
    JButton permission;
    JButton deluser;

    public void usermanager(String username, int type){
        String TYPE = "NULL";
        if (type == 1)
            TYPE = "管理员";
        if (type == 2)
            TYPE = "操作员";
        if (type == 3)
            TYPE = "用户";

        usermanagerjf = new JFrame("停车场管理系统-用户管理");
        usermanagerjp = new JPanel();
        usermanagerjp2 = new JPanel();
        banner = new JLabel("当前用户: "+username);
        banner2 = new JLabel("\n\n权限: "+TYPE);
        adduser = new JButton("添加用户");
        adduser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new adduserclass().adduser();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        permission = new JButton("修改权限");
        permission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new permissionclass().permission();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        deluser = new JButton("删除用户");
        deluser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new deluserclass().deluser();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        usermanagerjp.add(banner);
        usermanagerjp.add(banner2);
        usermanagerjp2.add(adduser);
        usermanagerjp2.add(permission);
        usermanagerjp2.add(deluser);

        usermanagerjf.setVisible(true);
        usermanagerjf.setSize(500,200);
        usermanagerjf.setLocationRelativeTo(null);
        usermanagerjf.add(usermanagerjp, BorderLayout.NORTH);
        usermanagerjf.add(usermanagerjp2, BorderLayout.CENTER);
    }
}
