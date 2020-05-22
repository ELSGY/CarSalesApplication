package sellermenu;

import menu.SellerMenu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.JsonArray;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ViewRequests implements ActionListener{
    private JTable table;
    private JFrame frame;
    private JButton rej;
    private JButton acc;
    DefaultTableModel model;
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
        model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        table=new JTable(model);

        //Parcurgere fisier cars.json pentru preluare detalii masini
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray array = new JSONArray();

        try {
            FileReader readFile = new FileReader("src/main/resources/requests.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                array = (JSONArray) p;
            }
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();
        }

        if(array.isEmpty()){
            JOptionPane.showMessageDialog(frame, "No requests!" );
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }
        else {

            int index=1;
            //Transformare din JSONArray in String[] si adaugare in tabel
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                data[1] = obj.get("Brand").toString();
                data[2] = obj.get("Model").toString();
                data[3] = obj.get("Year").toString();
                data[4] = obj.get("Price").toString();
                model.addRow(new Object[]{index,data[1], data[2], data[3],data[4]});
                index++;

            }}

        //Setari tabel
        table.setPreferredScrollableViewportSize(new Dimension(380, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 250, 240);
        panel.add(scrollPane);

        //Accept button
        acc = new JButton("Accept");
        acc.setBounds(40, 40, 180, 280);
        acc.addActionListener(this);
        panel.add(acc);

        //Reject Button
        rej = new JButton("Decline");
        rej.setBounds(100, 40, 180, 280);
        rej.addActionListener(this);
        panel.add(rej);

        frame.setVisible(true);
    }
    JSONArray list;
    private void UpdateTable(){
        JSONArray array = new JSONArray();
        JSONParser parser = new JSONParser();
        Object p;
        list = new JSONArray();
        org.json.JSONObject obje = new org.json.JSONObject();
        int index = (Integer) table.getValueAt(table.getSelectedRow(),0);//Preluare index

        if(table.getSelectedRow() >= 0) {//Daca linia este selectata

        //Copiere continut deja existent cu Parser
        try{
            FileReader readFile = new FileReader("src/main/resources/requests.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if(p instanceof JSONArray)
            {
                list =(JSONArray)p;
            }
        } catch (ParseException | IOException ex) {
            ex.printStackTrace();
        }
        obje.put("Brand", table.getValueAt(table.getSelectedRow(), 1).toString());
        obje.put("Model", table.getValueAt(table.getSelectedRow(), 2).toString());
        obje.put("Price", table.getValueAt(table.getSelectedRow(), 3).toString());
        obje.put("Year", table.getValueAt(table.getSelectedRow(), 4).toString());

        if(!acc.isSelected()){

                //Copiere continut deja existent cu Parser
                try{
                    FileReader readFile = new FileReader("src/main/resources/cars.json");
                    BufferedReader read = new BufferedReader(readFile);
                    p = parser.parse(read);
                    if(p instanceof JSONArray)
                    {
                        array =(JSONArray)p;
                    }
                } catch (ParseException | IOException ex) {
                    ex.printStackTrace();
                }

                array.add(obje);

                try{
                    File file=new File("src/main/resources/cars.json");
                    FileWriter fw=new FileWriter(file.getAbsoluteFile());
                    fw.write(array.toJSONString());
                    fw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }}

            //Stergere element selectat din fisier
            list.remove(index-1);

            //Rescriere elemente in fisier
            try{
                File file=new File("src/main/resources/requests.json");
                FileWriter fw=new FileWriter(file.getAbsoluteFile());
                fw.write(list.toJSONString());
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
          


        }
        else {
            JOptionPane.showMessageDialog(acc, "You must select a requests");
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();

        }

    }

    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Decline
        if(e.getSource()==rej)
        {
            UpdateTable();
            if(table == null){ frame.setVisible(false);
                SellerMenu bfp = new SellerMenu();
                bfp.sellermenu();}


        }
        if (e.getSource() == acc)
        {
            UpdateTable();
            if(list.isEmpty())
            { frame.setVisible(false);
                SellerMenu bfp = new SellerMenu();
                bfp.sellermenu();}
            list.clear();

        }


    }


}
