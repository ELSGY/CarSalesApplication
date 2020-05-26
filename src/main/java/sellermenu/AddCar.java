package sellermenu;

import menu.SellerMenu;
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
    private JTextField year,brand,model,price;
    private JButton back,addcar;

    public void GUICar(){
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

    public void addcar(){

        JSONObject obj = new JSONObject();
        Object p;
        JSONParser parser = new JSONParser();
        JSONArray list = new JSONArray();

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

        //Adaugare continut nou
        obj.put("Brand",brand.getText());
        obj.put("Model",model.getText());
        obj.put("Year",year.getText());
        obj.put("Price",price.getText());

        list.add(obj);

        //Scriere in fisier continut nou
        try{
            File file=new File("src/main/resources/cars.json");
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(list.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addcar)
        {
            addcar();
            JOptionPane.showMessageDialog(frame, "Car Added");
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            SellerMenu bfp = new SellerMenu();
            bfp.sellermenu();
        }

    }
}

