package sellermenu;

import exceptions.NotJSONFileException;
import menu.*;
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
    protected String username;
    private JTable table;
    private JFrame frame;
    private JButton rej;
    private JButton acc;
    private JButton back;
    private JSONArray list;

    public void GUIReq(String username) {
        this.username=username;
        JPanel panel = new JPanel();
        frame = new JFrame("View Requests");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        //Tabel requests
        String[] data = new String[5];
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        table = new JTable(model);

        //Citire din fisier
        JSONArray array = readFile("src/main/resources/requests.json");

        if (array.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No requests!");
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu(username);

        } else {

            int index = 1;
            //Transformare din JSONArray in String[] si adaugare in tabel
            for (JSONObject obj : (Iterable<JSONObject>) array) {
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

        //Accept button
        acc = new JButton("Accept");
        acc.setBounds(300, 620, 80, 25);
        acc.addActionListener(this);
        panel.add(acc);

        //Decline Button
        rej = new JButton("Decline");
        rej.setBounds(360, 620, 80, 25);
        rej.addActionListener(this);
        panel.add(rej);

        //Back button
        back = new JButton("Back");
        back.setBounds(420, 620, 80, 25);
        back.addActionListener(this);
        panel.add(back);

       if(array.isEmpty()){
            frame.setVisible(false);
        }
        else {
        frame.setVisible(true);
         }

    }

    public JSONArray readFile(String file) throws NotJSONFileException {

        if(!file.endsWith(".json")){
            throw new NotJSONFileException();
        }

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
        } catch (EOFException e) {
            // handle EOF exception
        } catch (ParseException | IOException parseException) {
            parseException.printStackTrace();

        }
        return array;
    }

    public boolean writeFile(JSONArray list, String JSONFile) throws NotJSONFileException{

        if(!JSONFile.endsWith(".json")){
            throw new NotJSONFileException();
        }

        //Scriere in fisier continut nou
        try{
            File file=new File(JSONFile);
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(list.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public void ActionButtons(){
        org.json.JSONObject obj = new org.json.JSONObject();

        try {
            int position = table.getSelectedRow();
            int index = (Integer) table.getValueAt(position,0);

            if(position >= 0) {
                //Daca linia este selectata
                list = readFile("src/main/resources/requests.json");
                if (acc.isEnabled()) {
                    obj.put("Brand", table.getValueAt(position, 1).toString());
                    obj.put("Model", table.getValueAt(position, 2).toString());
                    obj.put("Year", table.getValueAt(position, 3).toString());
                    obj.put("Price", table.getValueAt(position, 4).toString());

                    JSONArray array = readFile("src/main/resources/cars.json");
                    array.add(obj);
                    writeFile(array, "src/main/resources/cars.json");
                }

                list.remove(index - 1); // Stergere element selectat din fisier
                writeFile(list, "src/main/resources/requests.json");
                if(!list.isEmpty())
                {
                    list.clear();
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog( frame,"You must select a request.");
        }
    }

    public void UpdateTable(){
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

        for (JSONObject obj : (Iterable<JSONObject>) array) { //Transformare din JSONArray in String[] si adaugare in tabel
            data[1] = obj.get("Brand").toString();
            data[2] = obj.get("Model").toString();
            data[3] = obj.get("Year").toString();
            data[4] = obj.get("Price").toString();
            mod.addRow(new Object[]{index,data[1], data[2], data[3],data[4]});
            index++;
        }
        if(table.getRowCount() == 0) {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu(username);
        }

    }

    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Decline
        if(e.getSource()==rej)
        {
           acc.setEnabled(false);
            ActionButtons();
            UpdateTable();
            acc.setEnabled(true);

        }
        if (e.getSource() == acc)
        {
            acc.setEnabled(true);
            ActionButtons();
            UpdateTable();
        }
        if(e.getSource() == back)
        {
            frame.setVisible(false);
            SellerMenu sel = new SellerMenu();
            sel.sellermenu(username);
        }
    }
}
