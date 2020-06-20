package clientmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;

import exceptions.NotAllFieldsCompleted;
import exceptions.NotJSONFileException;
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
        }catch (ParseException | IOException parseException) {
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

    public int sendbutton(String source, String destination, String brand, String model, String year, String price) throws NotAllFieldsCompleted {
       if(brand.equals("") || model.equals("") || year.equals("") || price.equals("")){
           throw new NotAllFieldsCompleted();
       }

        JSONObject obj = new JSONObject();
        JSONArray list;

        list = readFile(source);

        //Adaugare continut nou
        obj.put("Brand",brand);
        obj.put("Model",model);
        obj.put("Year",year);
        obj.put("Price",price);

        list.add(obj);
        writeFile(list,destination);
        return list.size();

    }
    @Override
    public void actionPerformed(ActionEvent e){

        //Actiuni pentru butonul Register
        if(e.getSource()==send)
        {
            sendbutton("src/main/resources/requests.json","src/main/resources/requests.json", brand.getText(),model.getText(), year.getText(), price.getText());
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
