package sellermenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditCar {
    private JFrame frame;
    private JButton back;
    public void GUIEdit() {

        JPanel panel = new JPanel();
        frame = new JFrame("View Cars");
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
        JTable table=new JTable(model);

        //Back
        back = new JButton("Back");
        back.setBounds(360, 620, 80, 25);
        panel.add(back);


        frame.setVisible(true);
    }

}
