package libManageSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.postgresql.util.PSQLException;

import java.awt.color.*;
import java.sql.*;
import java.util.Vector;


public class select extends JFrame {
    public select(Vector rowData) throws SQLException {
        // 创建表格中的横标题
        Vector<String> Names = new Vector<String>();
        Names.add("bno");
        Names.add("category");
        Names.add("title");
        Names.add("press");
        Names.add("year");
        Names.add("author");
        Names.add("price");
        Names.add("total");
        Names.add("stock");
        // 以Names和rowData为参数，创建一个表格
        JTable table = new JTable(rowData, Names);
        // 设置此表视图的首选大小
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
        // 将表格加入到滚动条组件中
        JScrollPane scrollPane = new JScrollPane(table);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        // 再将滚动条组件添加到中间容器中
        this.setTitle("Select Result");
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

}

