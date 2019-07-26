package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.color.*;
import java.sql.*;

public class borrowUI extends JFrame implements ActionListener {
    JButton jb1, jb2 = null;
    JPanel jp1, jp2 = null;
    ButtonGroup bg = null;
    JRadioButton jrb1, jrb2 = null;

    public static void main(String[] args) {
        borrowUI ui = new borrowUI();
    }

    public borrowUI() {
        jp1 = new JPanel();
        jp2 = new JPanel();

        jb1 = new JButton("cno");
        jb2 = new JButton("bno");

        jp1.add(jb1);
        jp1.add(jb2);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        jrb1 = new JRadioButton("Borrow");
        jrb2 = new JRadioButton("Return");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb2.setSelected(true);

        jp2.add(jrb1);
        jp2.add(jrb2);

        this.add(jp2);
        this.add(jp1);
        this.setLayout(new GridLayout(4, 1));            //选择GridLayout布局管理器
        this.setTitle("Borrow/Return books");
        this.setSize(200, 150);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "cno") {
            new BRbookCnoUI();
        } else if (e.getActionCommand() == "bno") {
            if (jrb1.isSelected())
                new BbookBnoUI();
            else if (jrb2.isSelected())
                new RbookBnoUI();
        }

    }
} 
