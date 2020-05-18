package clientmenu;

import menu.ClientMenu;
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

public class SearchForm implements ActionListener {
    private JFrame frame;
    private JButton back;

    public void search(String brand,String year) {

        if(brand.isEmpty() && year.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "No data was introduced" );
            Application ap = new Application();
            ap.start();
        }
        else
        {
        JPanel panel = new JPanel();
        frame = new JFrame("Cars");
        frame.setSize(450, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.lightGray);


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Index");
        model.addColumn("Brand");
        model.addColumn("Model");
        model.addColumn("Year");
        model.addColumn("Price");
        JTable table = new JTable(model);

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


            int index = 1, ok = 0;
            //Verificare masina cautata
            String[] data = new String[5];
            for (JSONObject obj : (Iterable<JSONObject>) array) {

                String obkBrand = obj.get("Brand").toString();
                String obkYear = obj.get("Year").toString();
                if ((obkBrand.toLowerCase().equals(brand) && !brand.isEmpty() && obkYear.equals(year) && !year.isEmpty()) || (obkBrand.toLowerCase().equals(brand) && !brand.isEmpty() && year.isEmpty()) || (brand.isEmpty() && obkYear.equals(year) && !year.isEmpty())) {
                    data[1] = obj.get("Brand").toString();
                    data[2] = obj.get("Model").toString();
                    data[3] = obj.get("Year").toString();
                    data[4] = obj.get("Price").toString();
                    model.addRow(new Object[]{index, data[1], data[2], data[3], data[4]});
                    index++;
                    ok = 1;
                }
            }

            if (ok == 0) {
                JOptionPane.showMessageDialog(frame, "We didn't find any car...");
                frame.setVisible(false);
                Application ap = new Application();
                ap.start();
            } else {

                frame.setVisible(true);
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

            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            Application ap=new Application();
            ap.start();
        }

    }


}