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
import java.io.*;

public class ViewRequests {
    private JTable table;
    private JFrame frame;

    private JButton acc;
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
            JOptionPane.showMessageDialog(frame, "Empty list!" );
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
        panel.add(acc);

        //Reject Button
        JButton rej = new JButton("Reject");
        rej.setBounds(100, 40, 180, 280);
        panel.add(rej);

        frame.setVisible(true);
    }
    private void UpdateTable(){
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray list = new JSONArray();
        org.json.JSONObject obje = new org.json.JSONObject();
        int index = (Integer) table.getValueAt(table.getSelectedRow(),0);//Preluare index

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

    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Back
        if(e.getSource()==acc)
        {
            UpdateTable();
        }


    }


}
