package clientmenu;

import menu.SellerMenu;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AvailableCars implements ActionListener {

    private JFrame frame;
    private JButton back;
    public void GUIAv(){
        JPanel panel = new JPanel();
        frame = new JFrame("Available");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        //tabel cu masini
        String[] cars = new String[4];

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");

        JTable table = new JTable(model);

        JSONArray array = readFile("src/main/resources/cars.json");

        System.out.println(array.toJSONString());

        //Back Button
        back = new JButton("Back");
        back.setBounds(360, 620, 80, 25);
        back.addActionListener(this);
        panel.add(back);

        frame.setVisible(true);
    }

    private JSONArray readFile(String file){
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray array = new JSONArray();

        try {
            FileReader readFile = new FileReader(file);
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                array = (JSONArray) p;
            }
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();
        }
        return array;
    }

    public void actionPerformed(ActionEvent e){
        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }
    }
}
