package sellermenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewRequests {
    private JFrame frame;
    private JButton back;
    public void GUIReq(){
        JPanel panel = new JPanel();
        frame = new JFrame("View Requests");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        //Tabel cereri
        String[] data = new String[5];
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        JTable tabel=new JTable(model);

        //Setari tabel
        tabel.setPreferredScrollableViewportSize(new Dimension(380, 200));
        tabel.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabel);
        scrollPane.setBounds(10, 10, 250, 240);
        panel.add(scrollPane);

        //Accept button
        JButton acc = new JButton("Accept");
        acc.setBounds(40, 40, 180, 280);
        panel.add(acc);

        //Reject Button
        JButton rej = new JButton("Reject");
        rej.setBounds(100, 40, 180, 280);
        panel.add(rej);

        frame.setVisible(true);
    }
}
