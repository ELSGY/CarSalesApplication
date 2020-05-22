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


        JSONArray array = readFile("src/main/resources/requests.json");
        System.out.println(array.toJSONString());

        if(array.isEmpty()){
            JOptionPane.showMessageDialog(frame, "No requests!" );
            frame.setVisible(false);
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
        acc.setBounds(40, 40, 50, 30);
        acc.addActionListener(this);
        panel.add(acc);

        //Decline Button
        rej = new JButton("Decline");
        rej.setBounds(100, 40, 50, 30);
        rej.addActionListener(this);
        panel.add(rej);

        frame.setVisible(true);

    }

    private JSONArray readFile(String filePath){
        JSONArray array = new JSONArray();
        JSONParser parser = new JSONParser();
        Object p;

        //Copiere continut deja existent cu Parser
        try{
            FileReader readFile = new FileReader(filePath);
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if(p instanceof JSONArray)
            {
                array =(JSONArray)p;
            }
        } catch (ParseException | IOException ex) {
            ex.printStackTrace();
        }
        return array;

    }

    private void writeFile (JSONArray arr, String filepath){
        //Rescriere elemente in fisier
        try{
            File file=new File(filepath);
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(arr.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void ActionButtons(){

        JSONArray list = new JSONArray();
        org.json.JSONObject obj = new org.json.JSONObject();
        int index = (Integer) table.getValueAt(table.getSelectedRow(),0);//Preluare index

        if(table.getSelectedRow() >= 0) {//Daca linia este selectata

        list = readFile("src/main/resources/requests.json");

        obj.put("Brand", table.getValueAt(table.getSelectedRow(), 1).toString());
        obj.put("Model", table.getValueAt(table.getSelectedRow(), 2).toString());
        obj.put("Price", table.getValueAt(table.getSelectedRow(), 3).toString());
        obj.put("Year", table.getValueAt(table.getSelectedRow(), 4).toString());

        if(!acc.isSelected()){

            JSONArray array = readFile("src/main/resources/cars.json");
                array.add(obj);

                writeFile(array,"src/main/resources/cars.json");
        }

            //Stergere element selectat din fisier
            list.remove(index-1);

          writeFile(list, "src/main/resources/requests.json");

        }
        else {
            JOptionPane.showMessageDialog(acc, "You must select a requests");
        }



    }
    private void UpdateTable(){
        DefaultTableModel mod =new DefaultTableModel();
        mod.addColumn("Index");
        mod.addColumn("Brand");
        mod.addColumn("Model");
        mod.addColumn("Year");
        mod.addColumn("Price");
        table.setModel(mod);

        String[] data = new String[5];

      JSONArray array = readFile("src/main/resources/requests.json");

        int index=1;
        //Transformare din JSONArray in String[] si adaugare in tabel
        for (JSONObject obj : (Iterable<JSONObject>) array) {
            data[1] = obj.get("Brand").toString();
            data[2] = obj.get("Model").toString();
            data[3] = obj.get("Year").toString();
            data[4] = obj.get("Price").toString();
            mod.addRow(new Object[]{index,data[1], data[2], data[3],data[4]});
            index++;
        }
        if(table.getRowCount() == 0){ frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();}

        }



    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Decline
        if(e.getSource()==rej)
        {
            ActionButtons();
            UpdateTable();
        }
        if (e.getSource() == acc)
        {
            ActionButtons();
            UpdateTable();
        }


    }


}
