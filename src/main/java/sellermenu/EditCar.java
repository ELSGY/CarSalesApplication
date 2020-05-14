package sellermenu;

import menu.SellerMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCar implements ActionListener {
    private JFrame frame;
    private JButton back;
    private JButton edit;
    public void GUIEdit() {

        JPanel panel = new JPanel();
        frame = new JFrame("Edit Car");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        String[] data = new String[4];
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        JTable table = new JTable(model);

        //Setari tabel
        table.setPreferredScrollableViewportSize(new Dimension(380, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 250, 240);
        panel.add(scrollPane);

        //Edit Car
        edit = new JButton("Edit");
        edit.setBounds(300, 620, 80, 25);
        panel.add(edit);

        //Back
        back = new JButton("Back");
        back.setBounds(360, 620, 80, 25);
        back.addActionListener(this);
        panel.add(back);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }

    }


}
