package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.color.*;
import java.sql.*;

public class creditUI extends JFrame implements ActionListener {
    JLabel jlb1, jlb2, jlb3, jlb4 = null;
    JTextField jtf1, jtf2, jtf3 = null;
    JButton jb1, jb2, jb3;
    JPanel jp1, jp2, jp3, jp4, jp5 = null;
    JRadioButton jrb1, jrb2, jrb3, jrb4 = null;
    ButtonGroup bg = null;

    public static void main(String[] args) {
        creditUI ui = new creditUI();
    }

    public creditUI() {
        jlb1 = new JLabel("cno:");
        jtf1 = new JTextField(10);
        jlb2 = new JLabel("name:");
        jtf2 = new JTextField(10);
        jlb3 = new JLabel("department:");
        jtf3 = new JTextField(10);
        jlb4 = new JLabel("type:");

        jrb1 = new JRadioButton("T");
        jrb2 = new JRadioButton("G");
        jrb3 = new JRadioButton("U");
        jrb4 = new JRadioButton("O");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        bg.add(jrb3);
        bg.add(jrb4);
        jrb1.setSelected(true);


        jb1 = new JButton("Insert");
        jb2 = new JButton("Delete");
        jb3 = new JButton("Exit");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();

        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);
        jp3.add(jlb2);
        jp3.add(jtf2);
        jp4.add(jlb3);
        jp4.add(jtf3);
        jp5.add(jlb4);
        jp5.add(jrb1);
        jp5.add(jrb2);
        jp5.add(jrb3);
        jp5.add(jrb4);


        this.add(jp1);
        this.add(jp3);
        this.add(jp4);
        this.add(jp5);
        this.add(jp2);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);

        jp2.setLayout(new BoxLayout(jp2, BoxLayout.X_AXIS));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Manage Card");
        this.setSize(300, 200);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Insert") {
            try {
                insertCard();
                JOptionPane.showMessageDialog(null, "Insert Success!", "FBI warning", JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Can't Insert!", "FBI warning", JOptionPane.WARNING_MESSAGE);
                e1.printStackTrace();
            }

        } else if (e.getActionCommand() == "Delete") {
            try {
                deleteCard();
                JOptionPane.showMessageDialog(null, "Delete Success!", "FBI warning", JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, "Can't Delete!", "FBI warning", JOptionPane.WARNING_MESSAGE);
                e1.printStackTrace();
            }
        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }
    }

    public void insertCard() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        Statement st = c.createStatement();
        String cno, name, department, type = null;
        cno = jtf1.getText().trim().length() == 0 ? "NULL" : "'" + jtf1.getText().trim() + "'";
        name = jtf2.getText().trim().length() == 0 ? "NULL" : ",'" + jtf2.getText().trim() + "'";
        department = jtf3.getText().trim().length() == 0 ? "NULL" : ",'" + jtf3.getText().trim() + "'";
        if (jrb1.isSelected()) type = ",'T'";
        if (jrb2.isSelected()) type = ",'G'";
        if (jrb3.isSelected()) type = ",'U'";
        if (jrb4.isSelected()) type = ",'O'";
        sql = "insert into card values(" + cno + name + department + type + ")";
        st.execute(sql);
    }

    public void deleteCard() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        Statement st = c.createStatement();
        String cno, name, department, type = null;
        boolean status = false;
        if (jtf1.getText().trim().length() != 0) {
            cno = " cno='" + jtf1.getText().trim() + "'";
            status = true;
        } else cno = "";
        if (jtf2.getText().trim().length() != 0) {
            name = " name='" + jtf2.getText().trim() + "'";
            status = true;
        } else name = "";
        if (jtf3.getText().trim().length() != 0) {
            department = " department='" + jtf3.getText().trim() + "'";
            status = true;
        } else department = "";
        type = "";
        if (status == true) type += " AND type='";
        else type += " type='";

        if (jrb1.isSelected()) type += "T'";
        if (jrb2.isSelected()) type += "G'";
        if (jrb3.isSelected()) type += "U'";
        if (jrb4.isSelected()) type += "O'";
        sql = "delete from card where" + cno + name + department + type;
        st.execute(sql);

    }
} 

