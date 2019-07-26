package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;

import org.postgresql.util.PSQLException;

public class RbookBnoUI extends JFrame implements ActionListener {
    JLabel jlb1, jlb2 = null;
    JTextField jtf1, jtf2 = null;
    JButton jb1, jb2 = null;
    JPanel jp1, jp2, jp3 = null;

    public RbookBnoUI() {
        jlb1 = new JLabel("bno:");
        jtf1 = new JTextField(10);
        jlb2 = new JLabel("cno:");
        jtf2 = new JTextField(10);

        jb1 = new JButton("Return");
        jb2 = new JButton("Exit");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jtf2);


        jp3.add(jb1);
        jp3.add(jb2);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Return book with bno");
        this.setSize(300, 200);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public static void main(String[] args) {
        new RbookBnoUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand() == "Return") {
            try {
                returnBook();
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }
    }

    public void returnBook() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        Statement st = c.createStatement();
        String cno, bno;
        cno = jtf2.getText().trim().length() == 0 ? "" : " cno='" + jtf2.getText().trim() + "'";
        bno = jtf1.getText().trim().length() == 0 ? "" : cno == "" ? " bno='" + jtf1.getText().trim() + "'" : " AND bno='" + jtf1.getText().trim() + "'";
        sql = "select * from borrow where" + cno + bno;
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if (!rs.next()) JOptionPane.showMessageDialog(null, "NO RECORD", "FBI warning", JOptionPane.WARNING_MESSAGE);
        else {
            sql = "with t as (select * from borrow where" + cno + bno + " limit 1) update borrow set return_date='" + BbookBnoUI.getFormatDate() + "' where \"ID\" in (select \"ID\" from t)";
            st.execute(sql);
            sql = "select stock from book where bno='" + jtf1.getText().trim() + "'";
            rs = st.executeQuery(sql);
            rs.next();
            int stock = rs.getInt("stock");
            stock++;
            sql = "update book set stock=" + stock + " where bno='" + jtf1.getText().trim() + "'";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Return Success", "FBI warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}