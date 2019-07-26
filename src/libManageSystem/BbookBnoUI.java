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

public class BbookBnoUI extends JFrame implements ActionListener {
    JLabel jlb1, jlb2 = null;
    JTextField jtf1, jtf2 = null;
    JButton jb1, jb2 = null;
    JPanel jp1, jp2, jp3 = null;

    public BbookBnoUI() {
        jlb1 = new JLabel("bno:");
        jtf1 = new JTextField(10);
        jlb2 = new JLabel("cno:");
        jtf2 = new JTextField(10);

        jb1 = new JButton("Borrow");
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
        this.setTitle("Borrow book with bno");
        this.setSize(300, 200);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public static void main(String[] args) {
        new BbookBnoUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand() == "Borrow") {
            try {
                borrowBook();
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

    public void borrowBook() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        Statement st = c.createStatement();
        String cno, bno;
        cno = jtf2.getText().trim();
        bno = jtf1.getText().trim();
        sql = "select stock from book where bno='" + bno + "'";
        ResultSet rs = st.executeQuery(sql);
        int stock;
        if (!rs.next()) {
            stock = -1;
        } else {
            stock = rs.getInt("stock");
        }
        sql = "select name from card where cno='" + cno + "'";
        rs = st.executeQuery(sql);
        boolean isRightCno = rs.next();
        if (stock > 0 && isRightCno) {
            stock--;
            sql = "update \"book\" set stock=" + stock + " where bno='" + jtf1.getText().trim() + "'";
            st.execute(sql);
            sql = "insert into borrow values(" + cno + "," + bno + ",'" + getFormatDate() + "',NULL)";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Borrow Success", "FBI warning", JOptionPane.WARNING_MESSAGE);
        } else if (stock <= 0 && isRightCno) {
            JOptionPane.showMessageDialog(null, "Stock is short\nThe last return time:" + selectLastReturn(bno), "FBI warning", JOptionPane.WARNING_MESSAGE);

        } else if (!isRightCno) {
            JOptionPane.showMessageDialog(null, "Please check your cno", "FBI warning", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static String getFormatDate() {
        Date date = new Date();
        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        System.out.println(dateString);
        return dateString;
    }

    public String selectLastReturn(String bno) throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        Statement stmt = c.createStatement();
        sql = "select * from borrow a where a.return_date in (select max(b.return_date) from borrow b where b.bno='" + bno + "')";
        ResultSet rs = stmt.executeQuery(sql);
        if (!rs.next()) return "NO RETURN RECORD";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(rs.getDate("return_date"));
        return dateString;
    }
}
