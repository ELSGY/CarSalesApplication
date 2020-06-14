package sellermenu;

import menu.*;
import org.json.JSONString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ViewCars implements ActionListener {
    protected String username;
    private JFrame frame;
    private JButton back;

    public void GUIView(String username) {
        this.username=username;
        JPanel panel = new JPanel();
        frame = new JFrame("My cars");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        //Tabel cu masini
        String[] data = new String[5];
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        JTable table=new JTable(model);


        //Parcurgere fisier cars.json pentru preluare detalii masini
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray array = new JSONArray();

        try {
            FileReader readFile = new FileReader("src/main/resources/cars.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                array = (JSONArray) p;
            }
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();
        }

        if(array.isEmpty()){
            JOptionPane.showMessageDialog(frame, "Empty list!" );
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu(username);
        }
        else {

            int index=1;
            //Transformare din JSONArray in String[] si adaugare in tabel
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                if((obj.get("Username").toString()).equals(username)) {
                    data[1] = obj.get("Brand").toString();
                    data[2] = obj.get("Model").toString();
                    data[3] = obj.get("Year").toString();
                    data[4] = obj.get("Price").toString();
                    model.addRow(new Object[]{index, data[1], data[2], data[3], data[4]});
                    index++;
                }
            }

            //Setari tabel
            table.setPreferredScrollableViewportSize(new Dimension(380, 200));
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 10, 250, 240);
            panel.add(scrollPane);

            //Back
            back = new JButton("Back");
            back.setBounds(360, 620, 80, 25);
            back.addActionListener(this);
            panel.add(back);

            if(!array.isEmpty()){
            frame.setVisible(true);
            }
            else{
                frame.setVisible(false);
            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu(username);
        }

    }
}