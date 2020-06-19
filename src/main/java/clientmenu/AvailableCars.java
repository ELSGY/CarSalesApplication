package clientmenu;

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
        String[] cars = new String[5];

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Owner");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");

        JTable table = new JTable(model);

        JSONArray array = readFile("src/main/resources/cars.json");

        if(array.isEmpty()){
            JOptionPane.showMessageDialog(frame, "Empty list!" );
            ClientMenu client = new ClientMenu();
            client.menu();
        }
        else{

        //Transformare din JSONArray in String[] si adaugare in tabel
       for(JSONObject obj : (Iterable<JSONObject>)array) {
           cars[0] = obj.get("Username").toString();
           cars[1] = obj.get("Brand").toString();
           cars[2] = obj.get("Model").toString();
           cars[3] = obj.get("Year").toString();
           cars[4] = obj.get("Price").toString();
           model.addRow(new Object[]{cars[0], cars[1], cars[2], cars[3],cars[4]});
         }
       }

        //Setari tabel
        table.setPreferredScrollableViewportSize(new Dimension(380, 200));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 250, 240);
        panel.add(scrollPane);

        //Back Button
        back = new JButton("Back");
        back.setBounds(360, 620, 80, 25);
        back.addActionListener(this);
        panel.add(back);

        if(array.isEmpty()){
            frame.setVisible(false);
        }else{
            frame.setVisible(true);
        }

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
            ClientMenu client = new ClientMenu();
            client.menu();
        }
    }
}
