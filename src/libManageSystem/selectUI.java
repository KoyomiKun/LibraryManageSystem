package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;

import org.postgresql.util.PSQLException;

import java.awt.color.*;

public class selectUI extends JFrame implements ActionListener {

    JButton jb1, jb2, jb3 = null;
    JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp0, jp = null;
    JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf9, jtf5_2, jtf7_2 = null;
    JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb0 = null;
    BoxLayout layout = null;
    private Box box1 = Box.createVerticalBox();
    private Box box2 = Box.createVerticalBox();
    private Box box3 = Box.createVerticalBox();
    private Box box4 = Box.createVerticalBox();
    private Box box5 = Box.createVerticalBox();

    public static void main(String[] args) {
        selectUI ui = new selectUI();
    }

    public selectUI() {

        jlb1 = new JLabel("Please input info(if don't know,keep empty):");
        jlb2 = new JLabel("bno:");
        jlb3 = new JLabel("category:");
        jlb4 = new JLabel("title:");
        jlb5 = new JLabel("press:");
        jlb6 = new JLabel("year:(A-B)");
        jlb7 = new JLabel("author:");
        jlb8 = new JLabel("price:(A-B)");
        jlb9 = new JLabel("total:");
        jlb0 = new JLabel("stock:");


        jtf1 = new JTextField(10);
        jtf2 = new JTextField(10);
        jtf3 = new JTextField(10);
        jtf4 = new JTextField(10);
        jtf5 = new JTextField(5);
        jtf6 = new JTextField(10);
        jtf7 = new JTextField(5);
        jtf8 = new JTextField(10);
        jtf9 = new JTextField(10);
        jtf5_2 = new JTextField(5);
        jtf7_2 = new JTextField(5);

        jb1 = new JButton("Select");
        jb2 = new JButton("Reset");
        jb3 = new JButton("Exit");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        jp9 = new JPanel();
        jp0 = new JPanel();
        jp = new JPanel();


        jp1.add(jlb1);
        jp2.add(jlb2);
        jp2.add(jtf1);
        jp3.add(jlb3);
        jp3.add(jtf2);
        jp4.add(jlb4);
        jp4.add(jtf3);
        jp5.add(jlb5);
        jp5.add(jtf4);
        jp6.add(jlb6);
        jp6.add(jtf5);
        jp6.add(jtf5_2);
        jp7.add(jlb7);
        jp7.add(jtf6);
        jp8.add(jlb8);
        jp8.add(jtf7);
        jp8.add(jtf7_2);
        jp9.add(jlb9);
        jp9.add(jtf8);
        jp0.add(jlb0);
        jp0.add(jtf9);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);

//        setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
        this.add(box1);
        this.add(box2);
        this.add(box3);
        this.add(box4);
        this.add(box5);
        box1.add(jp1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jp2);
        box1.add(Box.createHorizontalStrut(20));
        box2.add(jp3);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jp4);
        box2.add(Box.createHorizontalStrut(20));
        box3.add(jp5);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(jp6);
        box3.add(Box.createHorizontalStrut(20));
        box4.add(jp7);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(jp8);
        box4.add(Box.createHorizontalStrut(20));
        box5.add(jp9);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(jp0);
        box5.add(Box.createHorizontalStrut(20));
        this.add(jp);

        this.setLayout(new GridLayout(4, 1));            //选择GridLayout布局管理器
        this.setTitle("Select Books from Library");
        this.setSize(600, 400);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);
        this.setResizable(true);
        //设置监听  
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Select") {
            try {
                select();
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getActionCommand() == "Reset") {
            clear();
        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }

    }

    public void select() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        Statement stmt = c.createStatement();
        String sql;
        boolean status = false;//前面有没有条目

        String text1 = null;
        if (jtf1.getText().trim().length() != 0) {
            text1 = " bno='" + jtf1.getText().trim() + "'";
            status = true;
        } else text1 = "";
        String text2 = null;
        if (jtf2.getText().trim().length() != 0) {
            if (status == true) {
                text2 = " AND category='" + jtf2.getText().trim() + "'";
            } else text2 = " category='" + jtf2.getText().trim() + "'";
            status = true;
        } else text2 = "";
        String text3 = null;
        if (jtf3.getText().trim().length() != 0) {
            if (status == true) {
                text3 = " AND  title='" + jtf3.getText().trim() + "'";
            } else text3 = "  title='" + jtf3.getText().trim() + "'";
            status = true;
        } else text3 = "";
        String text4 = null;
        if (jtf4.getText().trim().length() != 0) {
            if (status == true) {
                text4 = " AND press='" + jtf4.getText().trim() + "'";
            } else text4 = " press='" + jtf4.getText().trim() + "'";
            status = true;
        } else text4 = "";
        String text5 = null;
        if (jtf5.getText().trim().length() != 0) {
            if (status == true) {
                text5 = " AND year>='" + jtf5.getText().trim() + "' AND year<='" + jtf5_2.getText().trim() + "'";
            } else
                text5 = " year>='" + jtf5.getText().trim() + "' AND year<='" + jtf5_2.getText().trim() + "'";

            status = true;
        } else text5 = "";
        String text6 = null;
        if (jtf6.getText().trim().length() != 0) {
            if (status == true) {
                text6 = " AND author='" + jtf6.getText().trim() + "'";
            } else text6 = " author='" + jtf6.getText().trim() + "'";
            status = true;
        } else text6 = "";
        String text7 = null;
        if (jtf7.getText().trim().length() != 0) {
            if (status == true) {
                text7 = " AND price>='" + jtf7.getText().trim() + "' AND price<='" + jtf7_2.getText().trim() + "'";
                ;
            } else text7 = " price>='" + jtf7.getText().trim() + "' AND price<='" + jtf7_2.getText().trim() + "'";
            ;
            status = true;
        } else text7 = "";
        String text8 = null;
        if (jtf8.getText().trim().length() != 0) {
            if (status == true) {
                text8 = " AND total='" + jtf8.getText().trim() + "'";
            } else text8 = " total='" + jtf8.getText().trim() + "'";
            status = true;
        } else text8 = "";
        String text9 = null;
        if (jtf9.getText().trim().length() != 0) {
            if (status == true) {
                text9 = " AND stock='" + jtf9.getText().trim() + "'";
            } else text9 = " stock='" + jtf9.getText().trim() + "'";
            status = true;
        } else text9 = "";

        sql = "select * from book where"
                + text1
                + text2
                + text3
                + text4
                + text5
                + text6
                + text7
                + text8
                + text9
                + ";"
        ;
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        Vector rowData = new Vector();
        while (rs.next()) {
            Vector col = new Vector();
            col.add(new String(rs.getString("bno")));
            col.add(new String(rs.getString("category")));
            col.add(new String(rs.getString("title")));
            col.add(new String(rs.getString("press")));
            col.add(new Integer(rs.getInt("year")));
            col.add(new String(rs.getString("author")));
            col.add(new Double(rs.getDouble("price")));
            col.add(new Integer(rs.getInt("total")));
            col.add(new Integer(rs.getInt("stock")));
            rowData.add(col);
        }
        select ui = new select(rowData);       //创建新界面
    }

    //清空文本框和密码框
    public void clear() {
        jtf1.setText("");
        jtf2.setText("");
        jtf3.setText("");
        jtf4.setText("");
        jtf5.setText("");
        jtf6.setText("");
        jtf7.setText("");
        jtf8.setText("");
        jtf9.setText("");
    }
} 
