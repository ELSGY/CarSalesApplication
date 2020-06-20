package sellermenu;

import exceptions.NotJSONFileException;
import menu.*;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AddCar implements ActionListener {
    private JFrame frame;
    public JTextField year,brand,model,price;
    private JButton back,addcar;
    protected String username;

    public void GUICar(String username){
        this.username=username;
        JPanel panel = new JPanel();
        frame = new JFrame("Add Car");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.lightGray);

        //Add Car title
        JLabel title = new JLabel("Add New Car", SwingConstants.CENTER);
        title.setBounds(80,10,200,40);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Brand
        JLabel label = new JLabel("Brand");
        label.setBounds(60, 60, 80, 25);
        panel.add(label);

        brand = new JTextField(40);
        brand.setBounds(130,60, 165, 25);
        panel.add(brand);

        //Model
        JLabel label2 = new JLabel("Model");
        label2.setBounds(60, 100, 80, 25 );
        panel.add(label2);

        model = new JTextField(40);
        model.setBounds(130, 100, 165, 25);
        panel.add(model);

        //Manufacturing year
        JLabel label6 = new JLabel("Year");
        label6.setBounds(60, 140, 80, 25);
        panel.add(label6);

        year = new JTextField(40) ;
        year.setBounds(130, 140, 165, 25);
        panel.add(year);

        //Price
        JLabel label4 = new JLabel("Price");
        label4.setBounds(60, 180, 80, 25);
        panel.add(label4);

        price = new JTextField(40) ;
        price.setBounds(130, 180, 165, 25);
        panel.add(price);

        //Add car
        addcar = new JButton("Add Car");
        addcar.setBounds(140, 250, 100,25);
        panel.add(addcar);
        addcar.addActionListener(this);

        //Back
        back = new JButton("Back");
        back.setBounds(260, 250, 80,25);
        back.addActionListener((ActionListener) this);
        panel.add(back);

        frame.setVisible(true);

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

    public int addcar(String username,String brand, String model, String year, String price, String file){


        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        list=readFile(file);

        //Adaugare continut nou
        obj.put("Username",username);
        obj.put("Brand",brand);
        obj.put("Model",model);
        obj.put("Year",year);
        obj.put("Price",price);

        list.add(obj);

        writeFile(list,file);

       return list.size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String  branda,modela,yeara,pricea;

        if(e.getSource()==addcar)
        {
            branda=brand.getText();
            modela=model.getText();
            yeara=year.getText();
            pricea=price.getText();

            if(branda.equals("") || modela.equals("") || yeara.equals("") || pricea.equals(""))
            {
                JOptionPane.showMessageDialog(frame, "Not fields are completed");
            }
            else {
                addcar(username, branda, modela, yeara, pricea, "src/main/resources/cars.json");
                JOptionPane.showMessageDialog(frame, "Car Added");
                frame.setVisible(false);
                SellerMenu bfp = new SellerMenu();
                bfp.sellermenu(username);
            }
        }

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu(username);
        }

    }
}

