package sellermenu;

import menu.SellerMenu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;



public class EditCars implements ActionListener {
    private JFrame frame;
    private JButton back;
    private JTable table;
    private JButton edit;

    public void GUIEdit() {

        JPanel panel = new JPanel();
        frame = new JFrame("Edit Car");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        table = new JTable(model);

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
            JOptionPane.showMessageDialog(frame, "There is no car to edit!" );
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }
        else {


            //Transformare din JSONArray in String[] si adaugare in tabel
            String[] data = new String[4];
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                data[0] = obj.get("Brand").toString();
                data[1] = obj.get("Model").toString();
                data[2] = obj.get("Year").toString();
                data[3] = obj.get("Price").toString();
                model.addRow(new String[]{data[0], data[1], data[2], data[3]});

            }


            //Setari tabel
            table.setPreferredScrollableViewportSize(new Dimension(380, 200));
            table.setFillsViewportHeight(true);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(10, 10, 250, 240);
            panel.add(scrollPane);



            //Edit Car
            edit = new JButton("Edit");
            edit.setBounds(300, 620, 80, 25);
            edit.addActionListener(this);
            panel.add(edit);

            //Back
            back = new JButton("Back");
            back.setBounds(360, 620, 80, 25);
            back.addActionListener(this);
            panel.add(back);

            frame.setVisible(true);
        }
    }

    public void EdButton(){
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray list = new JSONArray();
        org.json.JSONObject obje = new org.json.JSONObject();

        if(table.getSelectedRow() >= 0) {

            String brand = JOptionPane.showInputDialog("Brand");
            String model = JOptionPane.showInputDialog("Model");
            String year = JOptionPane.showInputDialog("Year");
            String price = JOptionPane.showInputDialog("Price");
            //Edit button
            table.getModel().setValueAt(brand, table.getSelectedRow(), 0);
            table.getModel().setValueAt(model, table.getSelectedRow(), 1);
            table.getModel().setValueAt(year, table.getSelectedRow(), 2);
            table.getModel().setValueAt(price, table.getSelectedRow(), 3);

            //Copiere continut deja existent cu Parser
            try{
                FileReader readFile = new FileReader("src/main/resources/cars.json");
                BufferedReader read = new BufferedReader(readFile);
                p = parser.parse(read);
                if(p instanceof JSONArray)
                {
                    list =(JSONArray)p;
                }
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }

            obje.put("Brand", brand);
            obje.put("Model", model);
            obje.put("Year", year);
            obje.put("Price", price);

            Iterator<JSONObject> itr = list.iterator();

            while(itr.hasNext()) {
                JSONObject obj = itr.next();

              // if (obj.get("Brand").equals(obje.get(String.valueOf(table.getSelectedRow())) && obj.get("Model").equals(obje.get("Model")) && obj.get("Year").equals(obje.get("Year")) && obj.get("Price").equals(obje.get("Price"))) {

            }

                }else {
            JOptionPane.showMessageDialog(edit, "You must select a car");
        }
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
        if(e.getSource() == edit){
            EdButton();
        }

    }


}
