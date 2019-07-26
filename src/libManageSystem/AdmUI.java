package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import java.awt.color.*;
import javax.swing.JOptionPane;


public class AdmUI extends JFrame implements ActionListener {

    //瀹氫箟缁勪欢
    JButton jb1 = new JButton();//insert book
    JButton jb2 = new JButton();//select book
    JButton jb3 = new JButton();//borrow or return book
    JButton jb5 = new JButton();//credit manage

    JPanel jp1, jp2, jp3 = null;


    public static void main(String[] args) {
        AdmUI ui = new AdmUI();
    }

    public AdmUI() {

        //鍒涘缓缁勪欢
        jb1 = new JButton("INSERT BOOK");
        jb1.setForeground(Color.BLUE);
        jb2 = new JButton("SELECT BOOK");
        jb2.setForeground(Color.BLUE);
        jb3 = new JButton("BORROW/RETURN BOOK");
        jb3.setForeground(Color.BLUE);
        jb5 = new JButton("CREDIT MANAGE");
        jb5.setForeground(Color.BLUE);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();


        jp1.add(jb1);
        jp1.add(jb2);
        jp2.add(jb3);
        jp3.add(jb5);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);


        //璁剧疆甯冨眬绠＄悊鍣�
        this.setLayout(new GridLayout(4, 3, 50, 50));
        this.setTitle("Welcome Administator!");
        this.setSize(400, 300);
        this.setLocation(200, 200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb5.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
//                  	AdmUI.this.dispose();  
            new insertUI();
        } else if (e.getSource() == jb2) {
//                	AdmUI.this.dispose();  
            new selectUI();
        } else if (e.getSource() == jb3) {
//                	AdmUI.this.dispose();  
            new borrowUI();
        } else if (e.getSource() == jb5) {
//                	AdmUI.this.dispose();  
            new creditUI();
        }

    }
} 
