package clientmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.*;

public class RequestForm implements ActionListener {

    private JButton back,send;
    private JFrame frame;
    private JTextField brand,model,year,price;

    public void start() {
        JPanel panel = new JPanel();
        frame = new JFrame("Your form");
        frame.setSize(340, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        panel.setBackground(Color.lightGray);

        //Form
        JLabel title = new JLabel("Form", SwingConstants.CENTER);
        title.setBounds(80,10,200,40);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 18));
        panel.add(title);

        //Brand
        JLabel label = new JLabel("Brand");
        label.setBounds(40, 60, 80, 25);
        panel.add(label);

        brand = new JTextField(40);
        brand.setBounds(110,60, 165, 25);
        panel.add(brand);

        //Model
        JLabel label2 = new JLabel("Model");
        label2.setBounds(40, 100, 80, 25 );
        panel.add(label2);

        model = new JTextField(40);
        model.setBounds(110, 100, 165, 25);
        panel.add(model);

        //Year
        JLabel label3 = new JLabel("Year");
        label3.setBounds(40, 140, 80, 25);
        panel.add(label3);

        year = new JTextField(40) ;
        year.setBounds(110, 140, 165, 25);
        panel.add(year);

        //Price
        JLabel label4 = new JLabel("Price");
        label4.setBounds(40, 180, 80, 25);
        panel.add(label4);

        price = new JTextField(40) ;
        price.setBounds(110, 180, 165, 25);
        panel.add(price);

        //Request
        send = new JButton("Send");
        send.setBounds(110, 230, 80,25);
        panel.add(send);
        send.addActionListener(this);

        //Back
        back = new JButton("Back");
        back.setBounds(220, 230, 80,25);
        back.addActionListener(this);
        panel.add(back);


        frame.setVisible(true);
    }

    private void sendbutton(){

        JSONObject obj = new JSONObject();
        Object p;
        JSONParser parser = new JSONParser();
        JSONArray list = new JSONArray();

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

        //Adaugare continut nou
        obj.put("Brand",brand.getText());
        obj.put("Model",model.getText());
        obj.put("Year",year.getText());
        obj.put("Price",price.getText());

        list.add(obj);

        //Scriere in fisier continut nou
        try{
            File file=new File("src/main/resources/requests.json");
            FileWriter fw=new FileWriter(file.getAbsoluteFile());
            fw.write(list.toJSONString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    @Override
    public void actionPerformed(ActionEvent e){

        //Actiuni pentru butonul Register
        if(e.getSource()==send)
        {
            sendbutton();
            JOptionPane.showMessageDialog(frame, "Request sent!" );
            frame.setVisible(false);
            Application ap=new Application();
            ap.start();
        }

        //Actiuni pentru butonul Back
        if(e.getSource()==back)
        {
            frame.setVisible(false);
            Application ap=new Application();
            ap.start();
        }

    }

}
