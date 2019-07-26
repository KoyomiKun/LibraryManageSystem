package libManageSystem;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import java.awt.color.*;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class insertUI extends JFrame implements ActionListener {
    JButton jb1, jb2, jb3 = null;
    JPanel jp1, jp2 = null;
    JTextField jtf = null;
    JLabel jlb = null;

    public static void main(String[] args) {
        insertUI ui = new insertUI();
    }

    public insertUI() {
        jlb = new JLabel("Please input the file path:");
        jtf = new JTextField(20);

        jb1 = new JButton("Insert");
        jb2 = new JButton("Reset");
        jb3 = new JButton("Exit");

        jp1 = new JPanel();
        jp2 = new JPanel();

        jp1.add(jlb);
        jp1.add(jtf);
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.add(jb3);

        this.add(jp1);
        this.add(jp2);
        this.setLayout(new GridLayout(2, 1));            //閫夋嫨GridLayout甯冨眬绠＄悊鍣�
        this.setTitle("Insert Books into Library");    //
        this.setSize(300, 150);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //璁剧疆褰撳叧闂獥鍙ｆ椂锛屼繚璇丣VM涔熼��鍑�
        this.setVisible(true);
        this.setResizable(true);
        //璁剧疆鐩戝惉
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Insert") {
            String fileName = jtf.getText().trim();
            File file = new File(fileName);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                boolean status = true;
                int lineNum = 0;

                while ((line = reader.readLine()) != null) {
                    lineNum++;
                    status = status && addBook(line);
                    if (!status) {
                        JOptionPane.showMessageDialog(null, "Fail to insert\n Line:" + lineNum, "FBI warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
                //閿欒杈撳嚭閲嶅畾鍚�
                reader.close();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Fail finding the file!", "FBI warning", JOptionPane.WARNING_MESSAGE);
                e1.printStackTrace();
            }


        } else if (e.getActionCommand() == "Reset") {
            clear();
        } else if (e.getActionCommand() == "Exit") {
            System.exit(0);
        }


    }

    public void clear() {
        jtf.setText("");
    }

    public boolean addBook(String line) throws ClassNotFoundException, SQLException {
        Connection c = null;
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library",
                "postgres", "132432543zz");
        Statement stmt = c.createStatement();
        String sql;
        sql = "insert into book values(" + line + ")";
        System.out.println(line);

        stmt.execute(sql);

        return true;
    }
} 
