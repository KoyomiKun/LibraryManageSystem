package libManageSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class MainUI extends JFrame implements ActionListener {

    //瀹氫箟缁勪欢   
    JButton jb1, jb2, jb3 = null;
    JRadioButton jrb1, jrb2 = null;
    JPanel jp1, jp2, jp3, jp4 = null;
    JTextField jtf = null;
    JLabel jlb1, jlb2 = null;
    JPasswordField jpf = null;
    ButtonGroup bg = null;


    public static void main(String[] args) {

        MainUI mUI = new MainUI();
    }

    public MainUI() {
        //鍒涘缓缁勪欢
        jb1 = new JButton("Login");
        jb2 = new JButton("Reset");
        jb3 = new JButton("Exit");

        //璁剧疆鐩戝惉  
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);

        jrb1 = new JRadioButton("Others");
        jrb2 = new JRadioButton("Admin");
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb2.setSelected(true);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        jlb1 = new JLabel("User        ");
        jlb2 = new JLabel("Password");

        jtf = new JTextField(10);
        jpf = new JPasswordField(10);
        //鍔犲叆鍒癑Panel涓�  
        jp1.add(jlb1);
        jp1.add(jtf);

        jp2.add(jlb2);
        jp2.add(jpf);
        //娣诲姞鏍囩
        jp3.add(jrb1);
        jp3.add(jrb2);

        jp4.add(jb1);       //娣诲姞鎸夐挳
        jp4.add(jb2);
        jp4.add(jb3);

        //鍔犲叆JFrame涓�  
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);

        this.setLayout(new GridLayout(4, 1));            //閫夋嫨GridLayout甯冨眬绠＄悊鍣�
        this.setTitle("Library Manage System");
        this.setSize(300, 200);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //璁剧疆褰撳叧闂獥鍙ｆ椂锛屼繚璇丣VM涔熼��鍑� 
        this.setVisible(true);
        this.setResizable(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Login") {

            if (jrb1.isSelected()) {
                login();
            } else if (jrb2.isSelected()) //Admin
            {
                try {
                    Adminlogin();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        } else if (e.getActionCommand() == "Reset") {
            clear();
        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }

    }

    //绠＄悊鍛樼櫥褰曞垽鏂柟娉�
    public void Adminlogin() throws ClassNotFoundException, SQLException {
        Connection c = null;
        boolean status = false;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        Statement stmt = c.createStatement();
        String sql;
        sql = "select \"ID\",\"Password\" from \"Admin\";";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String id = rs.getString("ID");
            String password = rs.getString("Password");
            if (id.trim().equals(jtf.getText().trim()) && password.trim().equals(jpf.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Log in success", "FBI warning", JOptionPane.WARNING_MESSAGE);
                dispose();
                clear();
                status = true;
                AdmUI ui = new AdmUI();       //鍒涘缓鏂扮晫闈�
            }
        }
        if (status == false) {
            JOptionPane.showMessageDialog(null, "Please input the right ID and Password", "FBI warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void login() {
        dispose();
        clear();
        selectUI ui = new selectUI();       //鍒涘缓鏂扮晫闈�
    }

    //娓呯┖鏂囨湰妗嗗拰瀵嗙爜妗�  
    public void clear() {
        jtf.setText("");
        jpf.setText("");
    }

} 