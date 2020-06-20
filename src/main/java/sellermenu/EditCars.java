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

public class EditCars implements ActionListener {
    protected String username;
    private JFrame frame;
    private JButton back;
    private JTable table;
    private JButton edit;

    public void GUIEdit(String username) {
        this.username=username;

        JPanel panel = new JPanel();
        frame = new JFrame("Edit Car");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nr.");
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
            bfp.sellermenu(username);
        }
        else {

            int index=1;
            //Transformare din JSONArray in String[] si adaugare in tabel
            String[] data = new String[5];
            for (JSONObject obj : (Iterable<JSONObject>) array) {
                if(obj.get("Username").toString().equals(username)) {
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

    public boolean erase(JSONArray list,String username,String brand,String model,String year,String price){

        boolean okay=false;
        for (int i=0;i<list.size();i++) {
            JSONObject obj=(JSONObject) list.get(i);
            if(obj.get("Username").toString().equals(username)&&obj.get("Brand").toString().equals(brand)&&obj.get("Model").toString().equals(model)&&obj.get("Year").toString().equals(year)&&obj.get("Price").toString().equals(price))
            {
                int org=list.indexOf(obj);
                list.remove(org);
                okay=true;
            }
        }
       return okay;
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

    public void EdButton(){
        JSONArray list = new JSONArray();
        org.json.JSONObject object = new org.json.JSONObject();
        int index = (Integer) table.getValueAt(table.getSelectedRow(),0);//Preluare index

        if(table.getSelectedRow() >= 0) {//Daca row-ul este selectat

            String brand = JOptionPane.showInputDialog("Brand");
            String model = JOptionPane.showInputDialog("Model");
            String year = JOptionPane.showInputDialog("Year");
            String price = JOptionPane.showInputDialog("Price");

            //Citire din fisier
            list=readFile("src/main/resources/cars.json");

            //Stergere element "vechi editat"
            erase(list,username,(String)table.getValueAt(index - 1, 1),(String)table.getValueAt(index - 1, 2),(String)table.getValueAt(index - 1, 3),(String)table.getValueAt(index - 1, 4));

            //Edit button
            if(brand.isEmpty()) {

                table.getValueAt(index - 1, 1);
                object.put("Brand", table.getValueAt(index - 1, 1));
            }
            else{
                table.getModel().setValueAt(brand, table.getSelectedRow(), 1);
                object.put("Brand", brand);
            }

            if(model.isEmpty()) {
                table.getValueAt(index - 1, 2);
                object.put("Model", table.getValueAt(index - 1, 2));
            }
            else {
                table.getModel().setValueAt(model, table.getSelectedRow(), 2);
                object.put("Model", model);
            }

            if(year.isEmpty())
            {
                table.getValueAt(index - 1, 3);
                object.put("Year",table.getValueAt(index - 1, 3));
            }
            else
            {
                table.getModel().setValueAt(year, table.getSelectedRow(), 3);
                object.put("Year", year);

            }
            if(price.isEmpty())
            {
                table.getValueAt(index - 1, 4);
                object.put("Price",table.getValueAt(index - 1, 4));
            }
            else
            {
                table.getModel().setValueAt(price, table.getSelectedRow(), 4);
                object.put("Price", price);
            }
            object.put("Username",username);

            //Adaugare element nou
            list.add(object);

            //Rescriere elemente in fisier
            writeFile(list,"src/main/resources/cars.json");

        }
        else {
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
            bfp.sellermenu(username);
        }

        if(e.getSource() == edit)
        {
            EdButton();
        }
    }
}
