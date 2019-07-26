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

public class BRbookCnoUI extends JFrame implements ActionListener {
    JLabel jlb = null;
    JTextField jtf = null;
    JButton jb1, jb2 = null;
    JPanel jp1, jp2 = null;

    public BRbookCnoUI() {
        jlb = new JLabel("cno:");
        jtf = new JTextField(10);

        jb1 = new JButton("Select");
        jb2 = new JButton("Exit");

        jp1 = new JPanel();
        jp2 = new JPanel();

        jp1.add(jlb);
        jp1.add(jtf);

        jp2.add(jb1);
        jp2.add(jb2);

        jb1.addActionListener(this);
        jb2.addActionListener(this);

        this.add(jp1);
        this.add(jp2);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Select info about Card");
        this.setSize(300, 100);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出
        this.setVisible(true);
        this.setResizable(true);
    }

    public static void main(String[] args) {
        new BRbookCnoUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getActionCommand() == "Select") {
            try {
                selectByCno();
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

    public void selectByCno() throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        String sql;
        sql = "select bno from borrow where cno='" + jtf.getText().trim() + "'";
        Statement st1 = c.createStatement();
        Statement st2 = c.createStatement();
        ResultSet rs = st1.executeQuery(sql);
        Vector rowData = new Vector();
        while (rs.next()) {
            sql = "select * from book where bno='" + rs.getString("bno").trim() + "'";
            ResultSet subrs = st2.executeQuery(sql);
            subrs.next();
            Vector col = new Vector();
            col.add(new String(subrs.getString("bno")));
            col.add(new String(subrs.getString("category")));
            col.add(new String(subrs.getString("title")));
            col.add(new String(subrs.getString("press")));
            col.add(new Integer(subrs.getInt("year")));
            col.add(new String(subrs.getString("author")));
            col.add(new Double(subrs.getDouble("price")));
            col.add(new Integer(subrs.getInt("total")));
            col.add(new Integer(subrs.getInt("stock")));
            rowData.add(col);
        }
        new select(rowData);
    }
}
